package org.xing.parser;

import java.util.HashMap;
import java.util.Map;

public class FieldParser  extends Parser{
	
	private NumberParser numberParser;
	
	private class Tupple {
		public String eval;
		public String read;
		public Tupple() {
			eval = null;
			read = null;
		}
		public Tupple(String eval, String read) {
			this.eval = eval;
			this.read = read;
		}
	}
	
	public FieldParser(){
		numberParser = new NumberParser();
	}
	
	private String trimBrackets(String str) {
		if(str.startsWith("(") && str.endsWith("")) {
			return str.substring(1, str.length()-1);
		}
		return str;
	}

	//从右往左解析，嵌套操作的优先级有所不同
	private Tupple innerParse(String field) {
		StringBuilder strEval = new StringBuilder();
		StringBuilder strRead = new StringBuilder();
		
		String childField = null;
		Tupple childTupple = null;
		
		//指数函数
		if(field.endsWith("开方") || field.endsWith("平方根")){
			if(field.endsWith("根")) {
				childField = field.substring(0, field.length()-3);
			} else {
				childField = field.substring(0, field.length()-2);
			}
			childTupple = innerParse(childField);
			strEval.append("pow("+childTupple.eval+", 0.5)");
			strRead.append(childTupple.read+"^0.5)");
		} else if (field.endsWith("立方根")) {
			childField = field.substring(0, field.length()-3);
			childTupple = innerParse(childField);
			strEval.append("pow("+childTupple.eval+", 1/3)");
			strRead.append(childTupple.read+"^(1/3))");
		}else if (field.endsWith("平方")) {
			childField = field.substring(0, field.length()-2);
			childTupple = innerParse(childField);
			strEval.append("pow("+childTupple.eval+", 2)");
			strRead.append(childTupple.read+"^2)");
		}else if (field.endsWith("立方")) {
			childField = field.substring(0, field.length()-2);
			childTupple = innerParse(childField);
			strEval.append("pow("+childTupple.eval+", 3)");
			strRead.append(childTupple.read+"^3)");
		}else if (field.endsWith("次方")) {
			String[] tempFields = field.split("的");
				
			childField = tempFields[0];
			childTupple = innerParse(childField);
			
			String powField = tempFields[1].substring(0, tempFields[1].length()-2);
			Tupple powTupple = innerParse(powField);
			
			strEval.append("pow("+childTupple.eval+", "+powTupple.eval+")");
			strRead.append(childTupple.read+"^"+powTupple.read);
		}else if (field.startsWith("根号")) {
			childField = field.substring(2);
			childTupple = innerParse(childField);
			strEval.append("pow("+childTupple.eval+", 0.5)");
			strRead.append(childTupple.read+"^0.5)");
		}
		//三角函数
		else if (field.startsWith("sin")) {
			childField = field.substring(3);
			childTupple = innerParse(childField);
			strEval.append("sin("+childTupple.eval+")");
			strRead.append("sin("+trimBrackets(childTupple.read)+")");
		}else if (field.startsWith("cos")) {
			childField = field.substring(3);
			childTupple = innerParse(childField);
			strEval.append("cos("+childTupple.eval+")");
			strRead.append("cos("+trimBrackets(childTupple.read)+")");
		}else if (field.startsWith("tan")) {
			childField = field.substring(3);
			childTupple = innerParse(childField);
			strEval.append("tan("+childTupple.eval+")");
			strRead.append("tan("+trimBrackets(childTupple.read)+")");
		}else if (field.endsWith("正弦")) {
			childField = field.substring(0, field.length()-2);
			childTupple = innerParse(childField);
			strEval.append("sin("+childTupple.eval+")");
			strRead.append("sin("+trimBrackets(childTupple.read)+")");
		}else if (field.endsWith("余弦")) {
			childField = field.substring(0, field.length()-2);
			childTupple = innerParse(childField);
			strEval.append("cos("+childTupple.eval+")");
			strRead.append("cos("+trimBrackets(childTupple.read)+")");
		}else if (field.endsWith("正切")) {
			childField = field.substring(0, field.length()-2);
			childTupple = innerParse(childField);
			strEval.append("tan("+childTupple.eval+")");
			strRead.append("tan("+trimBrackets(childTupple.read)+")");
		}
		//对数函数
		else if (field.startsWith("lg")) {
			//log10
			childField = field.substring(2);
			childTupple = innerParse(childField);
			strEval.append("log("+childTupple.eval+")/log(10)");
			strRead.append("lg("+trimBrackets(childTupple.read)+")");
		}else if (field.startsWith("ln")) {
			//loge
			childField = field.substring(2);
			childTupple = innerParse(childField);
			strEval.append("log("+childTupple.eval+")");
			strRead.append("ln("+trimBrackets(childTupple.read)+")");
		}else if (field.startsWith("log")) {
			//log2
			childField = field.substring(3);
			childTupple = innerParse(childField);
			strEval.append("log("+childTupple.eval+")/log(2)");
			strRead.append("log("+trimBrackets(childTupple.read)+")");
		}else if (field.endsWith("对数")) {
			String[] tempFields = field.split("的");
			
			numberParser.parse(tempFields[1]);
			String logNum = numberParser.evalExpr;
			//log10
			childField = tempFields[0];
			childTupple = innerParse(childField);
			if(logNum.equals("1")) {		//其实是自然底数e
				strEval.append("log("+childTupple.eval+")");
			}else {
				strEval.append("log("+childTupple.eval+")/log("+logNum+")");
			}
			
			childTupple.read = trimBrackets(childTupple.read);
			if(logNum.equals("2")) {
				strRead.append("log("+childTupple.read+")");
			}else if(logNum.equals("1")){		//其实是自然底数e
				strRead.append("ln("+childTupple.read+")");
			}else if(logNum.equals("10")){
				strRead.append("lg("+childTupple.read+")");
			}else{
				strRead.append("log("+logNum+", "+childTupple.read+")");
			}
		}
		//分数
		else if(field.contains("分之")) {
			String[] tempFields = field.split("分之");
			
			childField = tempFields[0];
			childTupple = innerParse(childField);
			
			String secondField = tempFields[1];
			Tupple secondTupple = innerParse(secondField);
			
			strEval.append("("+secondTupple.eval+"/"+childTupple.eval+")");
			strRead.append("("+secondTupple.read+"/"+childTupple.read+")");
		}
		//纯数字
		else {
			numberParser.parse(field);
			strEval.append(numberParser.getEvalExpr());
			strRead.append(numberParser.getReadExpr());
		}
		
		return new Tupple(strEval.toString(), strRead.toString());
	}
	
	public void parse(String field) {
		Tupple tupple = innerParse(field);
		this.evalExpr = tupple.eval;
		this.readExpr = tupple.read;
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
        chnMap.put("二点五三的平方", "pow(2.53, 2)");
        chnMap.put("二点五三的开方", "pow(2.53, 0.5)");
        chnMap.put("二点五三的五次方", "pow(2.53, 5)");
        
        int correctNum = 0;
        int wrongNum = 0;
        FieldParser parser = new FieldParser();
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
	
	public static void  main(String[] argv) {
		testParse(argv);
	}
}
