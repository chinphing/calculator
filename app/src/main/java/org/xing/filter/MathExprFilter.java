package org.xing.filter;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author Administrator
 *将中文转化为数字，如一百转化为100等
 *转化类型：
 *	1、直接转化：点、零、一、二、三、四等
 *	2、单位转化：亿、万、千、百、十等，如三百五十，一万零二十等。
 *	3、指数转化：一元操作，根号，开方，平方；二元操作，n次方等，分数；
 */
public class MathExprFilter implements ExprFilter {
	private Pattern splitPattern = Pattern.compile("[\\+\\-\\*\\/\\^×加减乘除]");
	
	private Map<Character, String> chineseToMathMap; 
	
	private Map<Character, Integer> numberDigitsMap;
	
	private Map<Character, String> operatorMap;
	
	public MathExprFilter() {
		chineseToMathMap = new HashMap<Character, String>();
		chineseToMathMap.put('零', "0");
		chineseToMathMap.put('一', "1");
		chineseToMathMap.put('二', "2");
		chineseToMathMap.put('三', "3");
		chineseToMathMap.put('四', "4");
		chineseToMathMap.put('五', "5");
		chineseToMathMap.put('六', "6");
		chineseToMathMap.put('七', "7");
		chineseToMathMap.put('八', "8");
		chineseToMathMap.put('九', "9");
		
		numberDigitsMap = new HashMap<Character, Integer>();
		numberDigitsMap.put('个', 0);
		numberDigitsMap.put('十', 1);
		numberDigitsMap.put('百', 2);
		numberDigitsMap.put('千', 3);
		numberDigitsMap.put('万', 4);
		numberDigitsMap.put('亿', 8);
		
		operatorMap = new HashMap<Character, String>();
		operatorMap.put('加', "+");
		operatorMap.put('减', "-");
		operatorMap.put('乘', "*");
		operatorMap.put('×', "*");
		operatorMap.put('除', "/");
	}
	
	private boolean isNumber(char c) {
		if(c >= '0' && c <= '9') return true;
		if(chineseToMathMap.containsKey(c)) return true;
		return false;
	}
	
	private String handleNumber(String inputNumber) {
		StringBuilder str = new StringBuilder();
		
		StringBuilder reversedInputNumber = new StringBuilder(inputNumber).reverse();
		
		int numDigits = 0;		//整数位的位数
		int lastnumZero = 0;	//专门处理类似于三百万这种连续两个单位的数字
		int index = 0;		
		while(index < reversedInputNumber.length()) {
			char c = reversedInputNumber.charAt(index);
			if(isNumber(c)) {
				if(chineseToMathMap.containsKey(c)) {
					str.append(chineseToMathMap.get(c));
				} else {
					str.append(c);
				}
				numDigits ++;
			} else if (c == '-' || c == '负'){
				str.append('-');
			} else if(c == '.' || c == '点') {
				str.append('.');
				numDigits = 0;
			} else if(numberDigitsMap.containsKey(c)){
				int numZero = numberDigitsMap.get(c);
				if(numZero < lastnumZero) {
					numZero += lastnumZero;
				} else {
					lastnumZero= numZero;
				}
				
				int filledNumZero = numZero - numDigits;
				for(int i=0;i<filledNumZero;i++) {
					str.append('0');
					numDigits ++;
				}
				
				//处理'十四'，'一千零十四'这种特殊情况
				if(numberDigitsMap.get(c) == 1) {
					if(index == reversedInputNumber.length() - 1 ||
							reversedInputNumber.charAt(index+1) == '零' ||
							reversedInputNumber.charAt(index+1) == '负') {
						str.append('1');
						numDigits ++;
					}
				}
			} else {
				//System.err.println("错误的符号：'"+c+"'");
			}
				
			index ++;
		}
		
		return str.reverse().toString();
	}
	
	/**支持：根号，开方，平方，n次方等，分数
	 * 
	 */
	private String handleField(String field) {
		StringBuilder str = new StringBuilder();
		
		String defaultNumber = null;
		String operator =  "^";
		String extraNumber = null;
		
		if(field.contains("根号") || field.contains("开方")) {
			extraNumber = "0.5";
			defaultNumber = this.handleNumber(field);
		} else if(field.contains("平方")) {
			extraNumber = "2";
			defaultNumber = this.handleNumber(field);
		} else if(field.contains("立方")) {
			extraNumber = "3";
			defaultNumber = this.handleNumber(field);
		} else if(field.contains("次方")){
			String[] tempFields = field.split("的");
			if(tempFields.length >= 2) {
				defaultNumber = this.handleNumber(tempFields[0]);
				extraNumber = this.handleNumber(tempFields[1]);
			} else {
				System.err.println("错误输入：'"+field+"'");
			}
		} else if(field.contains("分之")) {
			String[] tempFields = field.split("分之");
			defaultNumber = this.handleNumber(tempFields[1]);
			extraNumber = this.handleNumber(tempFields[0]);
			operator = "/";
		}else {
			operator =  null;
			defaultNumber = this.handleNumber(field);
		}
		
		//分数表达式的优先级提高
		if(operator!= null && operator.equals("/")) {
			str.append("(");
		}

		if(operator != null) {
			if(operator.equals("^")) {
				str.append("pow(");
				str.append(defaultNumber);
				str.append(", ");
				str.append(extraNumber);
				str.append(")");
			} else {
				str.append(defaultNumber);
				str.append(operator);
				str.append(extraNumber);
			}
		} else {
			str.append(defaultNumber);
		}
		
		if(operator!= null && operator.equals("/")) {
			str.append(")");
		}
		return str.toString();
	}
	
	@Override
	public String call(String expr) {
		StringBuilder str = new StringBuilder();
		if(expr != null) {
			String[] fields = splitPattern.split(expr);
			int splitCharIndex = 0;
			for(int i=0;i<fields.length;i++) {
				String field = this.handleField(fields[i]);
				str.append(field);

				splitCharIndex += fields[i].length();
				if(i< fields.length-1) {
					char c = expr.charAt(splitCharIndex++);
					if(operatorMap.containsKey(c)) {
						str.append(operatorMap.get(c));
					} else {
						str.append(c);
					}
				}
				
			}
		}
		return str.toString();
	}
}
