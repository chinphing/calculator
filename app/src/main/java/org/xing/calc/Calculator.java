package org.xing.calc;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.DoubleKeyMap;
import org.antlr.v4.runtime.tree.ParseTree;
import org.xing.calc.filter.CorrectionExprFilter;
import org.xing.calc.filter.ExprFilter;
import org.xing.calc.filter.RedundantExprFilter;
import org.xing.calc.parser.CalculatorEvalVisitor;
import org.xing.calc.parser.CalculatorLatexExprVisitor;
import org.xing.calc.parser.grammer.calculatorLexer;
import org.xing.calc.parser.grammer.calculatorParser;
import org.xing.utils.NumberUtil;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.BitSet;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author Administrator
 * 计算语音输入的表达式语句的结果
 */
public class Calculator implements ANTLRErrorListener{
	/**
	 * 上次计算结果
	 */
	private double lastResult;
	
	/*
	 * 上次计算的表达式
	 */
	private String lastReadExpr;

	/*
	当前输入表达式
 	*/
	private String currentExpr;

	/*
	解析错误
	 */
	private int errPos;
	private String errMsg;

	/**
	 * 表达式过滤器列表，包括纠错、多余字符过滤以及数字转换等
	 */
	private List<ExprFilter> filters;
	
	/*
	 * 连续输入表达式的开始字符
	 */
	private Set<Character> continuousInputTag;
	
	/*
	 * 前置函数的连续计算
	 */
	private Set<String> continuousFuncTag;

	/*
 	* 后置函数的连续计算
 	*/
	private Set<String> continuousPostFuncTag;

	/*
	幂运算命令
	 */
	private Set<String> continuousPowTag;

	public Calculator() {
		setLastResult(0.0);

		filters = new LinkedList<ExprFilter>();

		String inputTags = "加+减-乘*×除/÷";
		continuousInputTag = new HashSet<>();
		for (int i = 0; i < inputTags.length(); i++) {
			continuousInputTag.add(inputTags.charAt(i));
		}

		String[] funcTags = new String[]{"cos", "余弦", "sin",
				"正弦", "tan", "正切", "acos", "反余弦", "asin", "反正弦",
				"atan", "反正切", "ln", "log", "lg", "对数", "根号"};
		continuousFuncTag = new HashSet<>();
		for (int i = 0; i < funcTags.length; i++) {
			continuousFuncTag.add(funcTags[i]);
		}

		String[] postFuncTags = new String[]{"开方", "开平方", "开立方", "平方根", "立方根"};
		continuousPostFuncTag = new HashSet<>();
		for (int i = 0; i < postFuncTags.length; i++) {
			continuousPostFuncTag.add(postFuncTags[i]);
		}

		String[] powTags = new String[]{"平方", "立方"};
		continuousPowTag = new HashSet<>();
		for (int i = 0; i < powTags.length; i++) {
			continuousPowTag.add(powTags[i]);
		}
	}
	
	public void addFilter(ExprFilter filter) {
		filters.add(filter);
	}

	public String execFilter(String expr) {
		if(expr == null) return null;

		for (ExprFilter filter : filters) {
			expr = filter.call(expr);
		}
		return expr;
	}

	public double getLastResult() {
		return lastResult;
	}

	public void setLastResult(double lastResult) {
		this.lastResult = lastResult;
	}
	public String getErrMsg() {
		return errMsg;
	}

	public String getReadExpr() {
		return lastReadExpr;
	}

	public void setErrorMsg(int pos) {
		int offset = 5;
		if(errPos != -1) return;
		if(currentExpr == null || pos < 0
				|| pos >= currentExpr.length()) errMsg = null;
		errPos = pos;
		int start = pos > offset ? pos-offset:0;
		int end = pos + offset < currentExpr.length() ? pos+offset:currentExpr.length();
		errMsg = currentExpr.substring(start, end);
	}


	public Double innerEval(String expr) {
		Double result = Double.NaN;
		String readExpr = null;
		
		ANTLRInputStream input = new ANTLRInputStream(expr);
		calculatorLexer lexer = new calculatorLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		calculatorParser parser = new calculatorParser(tokens);
		parser.addErrorListener(this);
		ParseTree tree = parser.expression();

		CalculatorEvalVisitor evalVisitor = new CalculatorEvalVisitor();
		result = evalVisitor.visit(tree);

		CalculatorLatexExprVisitor exprVisitor = new CalculatorLatexExprVisitor();
		readExpr = exprVisitor.visit(tree);

		if (result != null && !result.isNaN()) {
			lastResult = result;
			lastReadExpr = readExpr;
		}
		
		return result != null? result:Double.NaN;
	}

	public double eval(String expr)  {
		errMsg = null;
		Double result = Double.NaN;
		String orgExpr = expr;

		if(expr == null || expr.length() == 0) return result;

		try {
			for (ExprFilter filter : filters) {
				expr = filter.call(expr);
			}


			//过滤的文字太多，视为无效表达式
            if(orgExpr.length() - expr.length() > 4) {
				return Double.NaN;
			}



			while(true) {
				errPos = -1;
				currentExpr = expr;

				if(continuousInputTag.contains(expr.charAt(0))) {
					expr = NumberUtil.format(this.lastResult, 8)+expr;
				}else if(continuousFuncTag.contains(expr)) {
					expr = expr + NumberUtil.format(this.lastResult, 8);
				}else if(continuousPostFuncTag.contains(expr)) {
					expr = NumberUtil.format(this.lastResult, 8)+ expr;
				}else if(continuousPowTag.contains(expr)) {
					expr = NumberUtil.format(this.lastResult, 8) + "的" + expr;
				}

				result = innerEval(expr);
				if(!result.isNaN()) break;
				if(errPos >= 0 && errPos < currentExpr.length()
						&& currentExpr.charAt(errPos) == '点') {
					char[] seq = currentExpr.toCharArray();
					seq[errPos] = '减';
					expr = String.valueOf(seq);
				} else {
					break;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}

	public static Calculator createDefault(InputStream tokenStream) {
		String allowedChars =
				"0123456789.零一二三四五六七八九点负个十百千万亿+-*/括号加上减去乘以除÷×根号开方的平方次方立方分之" +
						"sincostanlglogln反正弦反余弦反正切对数()|^度°派π又清零归零" +
						"屏空撤销倒退删除帮助示例说明版本升级";
		
		Calculator calc = new Calculator();
		calc.addFilter(new CorrectionExprFilter());
		calc.addFilter(new RedundantExprFilter(allowedChars, tokenStream));

		return calc;
	}


	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object o, int i, int i1, String s, RecognitionException e) {
		setErrorMsg(i1);
	}

	@Override
	public void reportAmbiguity(Parser parser, DFA dfa, int i, int i1, boolean b, BitSet bitSet, ATNConfigSet atnConfigSet) {
		setErrorMsg(i1);
	}

	@Override
	public void reportAttemptingFullContext(Parser parser, DFA dfa, int i, int i1, BitSet bitSet, ATNConfigSet atnConfigSet) {
		setErrorMsg(i1);
	}

	@Override
	public void reportContextSensitivity(Parser parser, DFA dfa, int i, int i1, int i2, ATNConfigSet atnConfigSet) {
		setErrorMsg(i1);
	}
}
