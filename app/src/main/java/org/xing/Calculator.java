package org.xing;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.xing.filter.CorrectionExprFilter;
import org.xing.filter.ExprFilter;
import org.xing.filter.RedundantExprFilter;
import org.xing.parser.CalculatorEvalVisitor;
import org.xing.parser.CalculatorExprVisitor;
import org.xing.parser.grammer.calculatorLexer;
import org.xing.parser.grammer.calculatorParser;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * 计算语音输入的表达式语句的结果
 */
public class Calculator {
	/**
	 * 上次计算结果
	 */
	private double lastResult;
	
	/*
	 * 上次计算的表达式
	 */
	private String lastReadExpr;
	
	/**
	 * 表达式过滤器列表，包括纠错、多余字符过滤以及数字转换等
	 */
	private List<ExprFilter> filters;
	
	public Calculator() {
		setLastResult(0.0);
		
		filters = new LinkedList<ExprFilter>();
	}
	
	public void addFilter(ExprFilter filter) {
		filters.add(filter);
	}

	public double getLastResult() {
		return lastResult;
	}

	public void setLastResult(double lastResult) {
		this.lastResult = lastResult;
	}

	public String getReadExpr() {
		return lastReadExpr;
	}
	
	public double eval(String expr)  {
		Double result = Double.NaN;
		String readExpr = null;

		try {
			for (ExprFilter filter : filters) {
				expr = filter.call(expr);
			}

			ANTLRInputStream input = new ANTLRInputStream(expr);
			calculatorLexer lexer = new calculatorLexer(input);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			calculatorParser parser = new calculatorParser(tokens);
			ParseTree tree = parser.expression();

			CalculatorEvalVisitor evalVisitor = new CalculatorEvalVisitor();
			result = evalVisitor.visit(tree);

			CalculatorExprVisitor exprVisitor = new CalculatorExprVisitor();
			readExpr = exprVisitor.visit(tree);

			if(result != null && !result.isNaN()) {
				lastResult = result;
				lastReadExpr = readExpr;
			}
		}catch (Exception ex){
			ex.printStackTrace();
		}

		return result != null? result:Double.NaN;
	}

	public static Calculator createDefault() {
		String allowedChars =
				"0123456789.零一二三四五六七八九点负个十百千万亿+-*/括号加上减去乘除÷×根号开方的平方次方立方分之sincostanlglogln反正弦反余弦反正切对数()|^";

		Calculator calc = new Calculator();
		calc.addFilter(new CorrectionExprFilter());
		calc.addFilter(new RedundantExprFilter(allowedChars));

		return calc;
	}
	
	
	public static void testEval() {
		/*
         * sin(0.64) = 0.59719544136239205188354623920793
         * cos(0.64) = 0.80209575788429261358611077926032
         * tan(0.64) = 0.74454382222096388598945981115854
         * lg(0.64) = -0.19382002601611282871756663165304
         * ln(0.64) = -0.44628710262841951153259018061967
         * log(0.64) = 0.30102999566398119521373889472449
         */

		Calculator calc = createDefault();
		
		Map<String, String> chnMap = new HashMap<String, String>();
		
        chnMap.put("sin12", "-0.536573");
		chnMap.put("1加上三十五点二", "36.2");
        chnMap.put("1减10", "-9");
        chnMap.put("log(3点2加15)^2除2乘以3", "26.282218");
        chnMap.put("15*3", "45");
        chnMap.put("(12)", "12");
        chnMap.put("-12", "-12");
        chnMap.put("一加三十五", "36");
        chnMap.put("一减十", "-9");
        chnMap.put("百分之一", "0.01");
        chnMap.put("十五除以二", "7.5");
        chnMap.put("十五乘以三", "45");
        chnMap.put("15×3", "45");
        chnMap.put("十五分之三", "0.2");
        chnMap.put("一点三五加三十五点二零", "36.55");
        chnMap.put("零点二四减去二点五三", "-2.29");
        chnMap.put("零点二四乘以二点五三", "0.6072");
        chnMap.put("零点二四除以二点五三", "0.094861");
        chnMap.put("零点二四除以五分之三点二", "0.375");
        chnMap.put("一百零一点三加一万零一百", "10201.3");
        chnMap.put("一百零一减去一万零一百", "-9999.0");
        chnMap.put("一百零一乘一万零一百", "1020100.0");
        chnMap.put("一百零一除以一万零一百点五六", "0.009999");
        chnMap.put("零点二四减去二点五三的平方", "-6.160899");
        chnMap.put("零点二四乘以二点五三的开方", "0.381743");
        chnMap.put("零点二四除以二点五三的五次方", "0.002315");
        chnMap.put("零点二四除以五的立方", "0.001919");
        chnMap.put("零点二四加五分之三点二", "0.88");
        chnMap.put("零点二四减五分之三点二", "-0.4");
        chnMap.put("零点二四乘五分之三点二", "0.1536");
        chnMap.put("三千零十五万点二零加上一百五十点三五乘以一百零二", "30165335.9");
        chnMap.put("三千零十五万点二零减去一百五十点三五除以一百零二", "30149998.725980");
 
        chnMap.put("一百一加一万零一百", "10210");
        chnMap.put("一百一加一万一", "11110");
        chnMap.put("一百零一减去一万零一百", "-9999");
        chnMap.put("二^2", "4");
        
        //小数，函数，分数 测试
        chnMap.put("零点二四加sin五分之三点二", "0.837195");
        chnMap.put("零点二四减cos五分之三点二", "-0.562096");
        chnMap.put("零点二四乘tan五分之三点二", "0.178691");
        chnMap.put("零点二四加lg五分之三点二", "0.0461800");
        chnMap.put("零点二四减ln五分之三点二", "0.686287");
        chnMap.put("零点二四乘log五分之三点二", "-0.154525");
        
		//函数，小数，分数，幂运算 混合测试
        chnMap.put("零点二四加sin五分之三点二的三点二次方", "0.432120");
        chnMap.put("零点二四减cos五分之三点二的三点二次方", "-0.253769");
        chnMap.put("零点二四乘tan五分之三点二的三点二次方", "0.093381");
        
        chnMap.put("零点二四加lg五分之一百三点二的三点二次方", "2.640244");
        chnMap.put("零点二四减ln五分之一百三点二的三点二次方", "-34.381491");
        chnMap.put("零点二四乘log五分之一百三点二的三点二次方", "26.848222");
        
        chnMap.put("零点二四加lg(五分之一百三点二的三点二次方)", "4.447071");
        chnMap.put("零点二四减ln(五分之一百三点二的三点二次方)", "-9.447139");
        chnMap.put("零点二四乘log(五分之一百三点二的三点二次方)", "3.354140");
        
        //括号测试
        chnMap.put("括号1+3乘以括号1+2括号括号", "10.0");
        chnMap.put("|1+3乘以括号1+2|括号", "10.0");
        chnMap.put("log|1+3乘以括号1+2|括号", "3.321928");
        
        int correctNum = 0;
        int wrongNum = 0;
        for(String expr : chnMap.keySet()) {
        	double value = Double.parseDouble(chnMap.get(expr));
        	
        	double result = Double.NaN;
        	try{
        		result = calc.eval(expr);
        	}catch(Exception ex) {
        		System.err.println(expr);
        		ex.printStackTrace();
        	}
        	
        	System.out.printf("%s\n%s\n\t%.6f\n", expr, calc.getReadExpr(), result);
        	if(Double.isNaN(result) || Math.abs(result - value) > 1e-6) {
        		System.err.println("错误："+expr+", "+value+", 错误结果："+result+"\n");
        		wrongNum ++;
        	} else {
        		correctNum ++;
        	}
        }
        
        System.out.println("总计：\n\t正确 "+correctNum+", 错误 "+wrongNum);
	}
	
	public static void main(String[] argv) {
		testEval();
	}
}
