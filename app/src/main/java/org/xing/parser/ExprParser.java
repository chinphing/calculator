package org.xing.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class ExprParser extends Parser{
	private Pattern splitPattern = Pattern.compile("[\\+\\-\\*\\/\\^×加减乘除]");
	
	private FieldParser fieldParser;
	
	private Map<Character, String> operatorMap;

	public ExprParser() {
		fieldParser = new FieldParser();
		
		operatorMap = new HashMap<Character, String>();
		operatorMap.put('加', "+");
		operatorMap.put('减', "-");
		operatorMap.put('乘', "*");
		operatorMap.put('×', "*");
		operatorMap.put('除', "/");
	}
	
	public void parse(String expr) {
		StringBuilder strEval = new StringBuilder();
		StringBuilder strRead = new StringBuilder();
		if(expr != null) {
			String[] fields = splitPattern.split(expr);
			int splitCharIndex = 0;
			for(int i=0;i<fields.length;i++) {
				fieldParser.parse(fields[i]);
				strEval.append(fieldParser.getEvalExpr());
				strRead.append(fieldParser.getReadExpr());
				
				splitCharIndex += fields[i].length();
				if(i< fields.length-1) {
					String evalChar = null;
					char c = expr.charAt(splitCharIndex++);
					if(operatorMap.containsKey(c)) {
						evalChar = operatorMap.get(c);
						strEval.append(evalChar);
					} else {
						evalChar = String.valueOf(c);
						strEval.append(c);
					}
					
					if(evalChar.equals("*")) {
						strRead.append("×");
					}else if(evalChar.equals("/")) {
						strRead.append("÷");
					}else {
						strRead.append(evalChar);
					}
				}
				
			}
		}
		
		this.evalExpr = strEval.toString();
		this.readExpr = strRead.toString();
	}
	

	public static void testParse(String[] argv) {
		Map<String, String> chnMap = new HashMap<String, String>();
        chnMap.put("一", "1");
        chnMap.put("一十五", "15");
        chnMap.put("sin零点三五", "sin(0.35)");
        chnMap.put("负十五", "-15");
        chnMap.put("十五", "15");
        chnMap.put("二十", "20");
        chnMap.put("二十三", "23");
        chnMap.put("一百", "100");
        chnMap.put("一百一", "110");
        chnMap.put("一百零一", "101");
        chnMap.put("十分之一", "(1/10)");
        chnMap.put("百分之一", "(1/100)");
        chnMap.put("千分之一", "(1/1000)");
        chnMap.put("lg十分之一", "log((1/10))/log(10)");
        chnMap.put("log百分之一", "log((1/100))/log(2)");
        chnMap.put("ln千分之一", "log((1/1000))");
        chnMap.put("sin一百一十", "sin(110)");
        chnMap.put("一百一十一", "111");
        chnMap.put("一千", "1000");
        chnMap.put("sin一千一", "sin(1100)");
        chnMap.put("一千零一", "1001");
        chnMap.put("一千零一十", "1010");
        chnMap.put("tan一千零三十一", "tan(1031)");
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
        chnMap.put("sin负三千零七十八亿三千零十五万零三百一十二", "sin(-307830150312)");
        chnMap.put("一千二百五十八亿", "125800000000");
        chnMap.put("lg一千二百五十8万亿零三千三百二十一点124", "log(1258000000003321.124)/log(10)");
       
        chnMap.put("一加三十五", "1+35");
        chnMap.put("一减十", "1-10");
        chnMap.put("十五除以二", "15/2");
        chnMap.put("十五乘以三", "15*3");
        chnMap.put("15×3", "15*3");
        chnMap.put("十五分之三", "(3/15)");
        chnMap.put("一点三五加三十五点二零", "1.35+35.20");
        chnMap.put("零点二四减去二点五三", "0.24-2.53");
        chnMap.put("零点二四乘以二点五三", "0.24*2.53");
        chnMap.put("零点二四除以二点五三", "0.24/2.53");
        chnMap.put("零点二四除以五分之三点二", "0.24/(3.2/5)");
        chnMap.put("一百一加一万零一百", "110+10100");
        chnMap.put("一百一加一万一", "110+11000");
        chnMap.put("一百零一减去一万零一百", "101-10100");
        chnMap.put("一百零一乘一万零一百", "101*10100");
        chnMap.put("一百零一除以一万零一百点五六", "101/10100.56");
        chnMap.put("零点二四减去二点五三的平方", "0.24-pow(2.53, 2)");
        chnMap.put("零点二四乘以二点五三的开方", "0.24*pow(2.53, 0.5)");
        chnMap.put("零点二四除以二点五三的五次方", "0.24/pow(2.53, 5)");
        chnMap.put("零点二四除以五的立方", "0.24/pow(5, 3)");
        chnMap.put("零点二四加五分之三点二", "0.24+(3.2/5)");
        chnMap.put("零点二四减五分之三点二", "0.24-(3.2/5)");
        chnMap.put("零点二四乘五分之三点二", "0.24*(3.2/5)");
        chnMap.put("三千零十五万点二零加上一百五十点三五乘以一百零二", "30150000.20+150.35*102");
        chnMap.put("三千零十五万点二零减去一百五十点三五除以一百零二", "30150000.20-150.35/102");
        chnMap.put("零点二四加sin五分之三点二", "0.24+sin((3.2/5))");
        chnMap.put("零点二四减cos五分之三点二", "0.24-cos((3.2/5))");
        chnMap.put("零点二四乘tan五分之三点二", "0.24*tan((3.2/5))");
        chnMap.put("零点二四加lg五分之三点二", "0.24+log((3.2/5))/log(10)");
        chnMap.put("零点二四减ln五分之三点二", "0.24-log((3.2/5))");
        chnMap.put("零点二四乘log五分之三点二", "0.24*log((3.2/5))/log(2)");
        chnMap.put("零点二四加sin五分之三点二的三点二次方", "0.24+pow(sin((3.2/5)), 3.2)");
        chnMap.put("零点二四减cos五分之三点二的三点二次方", "0.24-pow(cos((3.2/5)), 3.2)");
        chnMap.put("零点二四乘tan五分之三点二的三点二次方", "0.24*pow(tan((3.2/5)), 3.2)");
        chnMap.put("零点二四加lg五分之三点二的三点二次方", "0.24+pow(log((3.2/5))/log(10), 3.2)");
        chnMap.put("零点二四减ln五分之三点二的三点二次方", "0.24-pow(log((3.2/5)), 3.2)");
        chnMap.put("零点二四乘log五分之三点二的三点二次方", "0.24*pow(log((3.2/5))/log(2), 3.2)");
        
        int correctNum = 0;
        int wrongNum = 0;
        ExprParser parser = new ExprParser();
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
		testParse(argv);
	}
}
