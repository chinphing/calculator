package org.xing.calc.parser;

import java.util.HashMap;
import java.util.Map;

public class NumberParser extends Parser {
	private Map<Character, String> chineseToMathMap; 
	private Map<Character, Integer> numberDigitsMap;
	
	public NumberParser() {
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
	}

	private boolean isNumber(char c) {
		if(c >= '0' && c <= '9') return true;
		if(chineseToMathMap.containsKey(c)) return true;
		return false;
	}

	public void parse(String inputNumber) {
		StringBuilder str = new StringBuilder();
		
		StringBuilder reversedInputNumber = new StringBuilder(inputNumber).reverse();
		
		int numDigits = 0;		//整数位的位数
		int maxNumZero = 0;					//专门处理类似于三百万这种连续两个单位的数字
		int secondaryMaxNumZero = 0;		//二级最大值
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
				if(numZero <= maxNumZero) {
					if(numZero <= secondaryMaxNumZero) {
						numZero += secondaryMaxNumZero;
					} else {
						secondaryMaxNumZero = numZero;
					}
					numZero += maxNumZero;
				} else {
					maxNumZero = numZero;
					secondaryMaxNumZero = 0;
				}
				
				if(index == 1
						&& (!numberDigitsMap.containsKey(reversedInputNumber.charAt(0)))
						)  {
					//处理‘一万二’、‘三千四’这种简略说法
					int headFilledNumZero = numberDigitsMap.get(c) - numDigits;
					for(int i=0;i<headFilledNumZero;i++) {
						str.insert(0, '0');
						numDigits ++;
					}
				} else {
					//处理通用说法
					int filledNumZero = numZero - numDigits;
					for(int i=0;i<filledNumZero;i++) {
						str.append('0');
						numDigits ++;
					}
				}
				
				//处理'十四'，‘十’，'百'，'千'，'万'，'一千零十四'这种简略说法
				if(numberDigitsMap.get(c) <= 4) {
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
		
		this.evalExpr = this.readExpr = str.reverse().toString();
	}
	

	//测试代码
	public static void testParseNumber(String[] argv) {
		Map<String, String> chnMap = new HashMap<String, String>();
        chnMap.put("一", "1");
        chnMap.put("一十五", "15");
        chnMap.put("零点三五", "0.35");
        chnMap.put("负十五", "-15");
        chnMap.put("十五", "15");
        chnMap.put("二十", "20");
        chnMap.put("二十三", "23");
        chnMap.put("一百", "100");
        chnMap.put("一百一", "110");
        chnMap.put("一百零一", "101");
        chnMap.put("一百一十", "110");
        chnMap.put("一百一十一", "111");
        chnMap.put("一千", "1000");
        chnMap.put("一千一", "1100");
        chnMap.put("一千零一", "1001");
        chnMap.put("一千零一十", "1010");
        chnMap.put("一千零三十一", "1031");
        chnMap.put("一万零一", "10001");
        chnMap.put("一万一", "11000");
        chnMap.put("一万零一百", "10100");
        chnMap.put("一万零二十一", "10021");
        chnMap.put("一万零三百二十一.三五", "10321.35");
        chnMap.put("一万一千三百二十一", "11321");
        chnMap.put("三千零十五万点二零", "30150000.20");
        chnMap.put("三千零一十五万", "30150000");
        chnMap.put("三千五百六十八万零一百零一", "35680101");
        chnMap.put("五十亿三千零七十五万零六百二十二", "5030750622");
        chnMap.put("十三亿三千零十五万零三百一十二", "1330150312");
        chnMap.put("负三千零七十八亿三千零十五万零三百一十二", "-307830150312");
        chnMap.put("一千二百五十八亿", "125800000000");
        chnMap.put("一千二百五十8万亿零三千三百二十一点124", "1258000000003321.124");
        
        int correctNum = 0;
        int wrongNum = 0;
        NumberParser parser = new NumberParser();
        for(String num : chnMap.keySet()) {
        	String value = chnMap.get(num);
        	parser.parse(num);
        	String result = parser.getEvalExpr();
        	if(!result.equals(value)) {
        		System.err.println("错误："+num+", "+value+", 错误结果："+result);
        		wrongNum ++;
        	} else {
        		correctNum ++;
        	}
        }
        
        System.out.println("总计：\n\t正确 "+correctNum+", 错误 "+wrongNum);
	}
	

	public static void main(String[] argv) {
		testParseNumber(argv);
	}
}
