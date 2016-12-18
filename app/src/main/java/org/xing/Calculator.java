package org.xing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;

import org.xing.filter.CorrectionExprFilter;
import org.xing.filter.ExprFilter;
import org.xing.filter.MathExprFilter;
import org.xing.filter.RedundantExprFilter;

/**
 * @author Administrator
 * 计算语音输入的表达式语句的结果
 */
public class Calculator {
	/**
	 * 上次计算结果
	 */
	private double lastResult;
	
	/**
	 * 表达式计算对象
	 */
	private Evaluator evaluator;
	
	/**
	 * 表达式过滤器列表，包括纠错、多余字符过滤以及数字转换等
	 */
	private List<ExprFilter> filters;
	
	public Calculator() {
		setLastResult(0.0);
		evaluator = new Evaluator();
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

	public String getMathExpr(String expr) {
		String mathExpr = expr;
		for(ExprFilter filter : filters) {
			mathExpr = filter.call(mathExpr);
		}

		//为了提高可读性，将计算机表示的除号改成人容易识别的除号
		mathExpr =mathExpr.replace('/', '÷');
		mathExpr =mathExpr.replace('*', '×');
		return mathExpr;
	}

	private double _eval(String expr) {
		double result = Double.NaN;
		for(ExprFilter filter : filters) {
			expr = filter.call(expr);
		}

		try{
			String resStr = evaluator.evaluate(expr);
			result = Double.parseDouble(resStr);
			lastResult = result;
		}catch(Exception ex) {

		}

		return result;
	}
	
	public double eval(String expr)  {

		double result = Double.NaN;
		for(ExprFilter filter : filters) {
			expr = filter.call(expr);
		}

		try{
			String resStr = evaluator.evaluate(expr);
			result = Double.parseDouble(resStr);
			lastResult = result;
		}catch(Exception ex) {
			
		}
		
		return result;
	}
	
	public static void testEval() {
		String allowedChars = 
				"0123456789.零一二三四五六七八九点+-*/加减乘除根号开方的平次负个十百千万亿立分之";
		
		Calculator calc = new Calculator();
		calc.addFilter(new CorrectionExprFilter());
		calc.addFilter(new RedundantExprFilter(allowedChars));
		calc.addFilter(new MathExprFilter());
		
		Map<String, String> chnMap = new HashMap<String, String>();
        chnMap.put("一加三十五", "36");
        chnMap.put("一减十", "-9");
        chnMap.put("十五除以二", "7.5");
        chnMap.put("十五乘以三", "45");
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
        

        int correctNum = 0;
        int wrongNum = 0;
        for(String expr : chnMap.keySet()) {
        	double value = Double.parseDouble(chnMap.get(expr));
        	double result = calc.eval(expr);
        	System.out.printf("%s\n\t%.6f\n", calc.getMathExpr(expr), result);
        	if(Math.abs(result - value) > 1e-6) {
        		System.err.println("错误："+expr+", "+value+", 错误结果："+result);
        		wrongNum ++;
        	} else {
        		correctNum ++;
        	}
        }
        
        System.out.println("总计：\n\t正确 "+correctNum+", 错误 "+wrongNum);
	}

	public static Calculator createDefault() {
		String allowedChars =
				"0123456789.零一二三四五六七八九点+-*/加减乘除×根号开方的平次负个十百千万亿立分之";

		Calculator calc = new Calculator();
		calc.addFilter(new CorrectionExprFilter());
		calc.addFilter(new RedundantExprFilter(allowedChars));
		calc.addFilter(new MathExprFilter());

		return calc;
	}
	
	public static void main(String[] argv) {
		testEval();
		
	}
}
