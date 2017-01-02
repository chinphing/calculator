package org.xing.calc.parser;

import java.util.Stack;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.xing.calc.parser.grammer.calculatorBaseVisitor;
import org.xing.calc.parser.grammer.calculatorParser;
import org.xing.calc.parser.grammer.calculatorParser.FuncnameContext;
import org.xing.calc.parser.grammer.calculatorParser.FuncnameExContext;

public class CalculatorExprVisitor extends calculatorBaseVisitor<String>{
	protected NumberParser numParser;
	public CalculatorExprVisitor() {
		numParser = new NumberParser();
	}
	
	private boolean bracketsLeggal(String expr) {
		Stack<Character> brackets = new Stack<>();
		for(int i=0;i<expr.length();i++) {
			Character c = expr.charAt(i);
			if(c == '(') {
				brackets.add(c);
			} else if(c== ')') {
				if((!brackets.empty()) && brackets.peek() == '(') {
					brackets.pop();
				}else {
					return false;
				}
			}
		}
		
		if(brackets.empty()) return true;
		return false;
	}
	
	protected String trimBrackets(String expr) {
		if(expr == null) return null;
		
		if(expr.startsWith("(") && expr.endsWith(")")) {
			String substr = expr.substring(1, expr.length()-1);
			if(bracketsLeggal(substr)) return substr;
		}
		
		return expr;
	}
	
	@Override
	public String visitExpression(calculatorParser.ExpressionContext ctx) {
		StringBuilder result = new StringBuilder();
		for(int i=0;i<ctx.getChildCount();i++) {
			ParseTree child = ctx.getChild(i);
			
			if(child instanceof TerminalNode) {
				int type = ((TerminalNode) child).getSymbol().getType();
				if(type == calculatorParser.PLUS) {
					result.append("+");
				} else {
					result.append("-");
				}
			}else {
				result.append(visit(child));
			}
		}
		return result.toString();
	}

	@Override
	public String visitMultiplyingExpression(
			calculatorParser.MultiplyingExpressionContext ctx) {
		StringBuilder result = new StringBuilder();
		for(int i=0;i<ctx.getChildCount();i++) {
			ParseTree child = ctx.getChild(i);
			
			if(child instanceof TerminalNode) {
				int type = ((TerminalNode) child).getSymbol().getType();
				if(type == calculatorParser.DIV) {
					result.append("÷");
				} else {
					result.append("×");
				}
			}else {
				result.append(visit(child));
			}
		}
		return result.toString();
	}

	@Override
	public String visitPowExpression(calculatorParser.PowExpressionContext ctx) {
		StringBuilder result = new StringBuilder();
		result.append(visit(ctx.getChild(0)));
		
		if(ctx.getChildCount() > 1) {
			for(int i=2;i<ctx.getChildCount();i+=2) {
				result.append("^");
				result.append(visit(ctx.getChild(i)));
			}
		}
		
		return result.toString();
	}
	
	@Override 
	public String visitChinaPowExpression(
			calculatorParser.ChinaPowExpressionContext ctx) { 
		StringBuilder result = new StringBuilder();

		int index = 0;
		result.append(visit(ctx.getChild(index++)));
		while (index < ctx.getChildCount()) {
			if (ctx.getChild(index + 1) instanceof TerminalNode) {
				int type = ((TerminalNode) ctx.getChild(index + 1)).getSymbol()
						.getType();
				if (type == calculatorParser.PINGFANG) {
					result.append("^2");
				} else if (type == calculatorParser.LIFANG) {
					result.append("^3");
				} else if (type == calculatorParser.KAIFANG) {
					result.append("^0.5");
				} else {
					System.err.println("未处理的终端节点:visitChinaPowExpression");
				}
				index += 2;
			} else {
				String pow = visit(ctx.getChild(index + 1));
				result.append("^" + pow);
				index += 3;
			}
		}
		return result.toString();
	}

	@Override
	public String visitAtom(calculatorParser.AtomContext ctx) {
		if(ctx.getChildCount() == 3) {
			if(ctx.FRAC() != null) {
				if(ctx.FRAC().getText().equals("分之")) {
					return "("+visit(ctx.getChild(2)) + "/" + visit(ctx.getChild(0))+")";
				}else {
					//分数用'/'表示，这是语音识别的结果导致的，它偶尔自动把分数转化成了'/'
					return "("+visit(ctx.getChild(0)) + "/" + visit(ctx.getChild(2))+")";
				}
			}else {
				return "("+visit(ctx.expression())+")";
			}
		}else {
			return visit(ctx.getChild(0));
		}
	}

	@Override
	public String visitFunc(calculatorParser.FuncContext ctx) {
		if(ctx.getChildCount() == 3) {
			FuncnameExContext func = ctx.funcnameEx();
			String firstNum = visit(ctx.getChild(0));
			String secondNum = visit(ctx.getChild(2));
			if(firstNum == null || secondNum == null) return null;
			
			if(func.DUISHU() != null) {
				return "(ln(" + secondNum + ")/ln(" + firstNum + "))";
			}else if(func.GENHAO() != null) {
				return secondNum+"^(1/"+firstNum+")";
			}
		} else {
			String expr = visit(ctx.getChild(1));
			FuncnameContext func = ctx.funcname();
			if(expr == null) return null;
			
			if(func.SIN() != null) {
				return "sin("+expr+")";
			}else if(func.COS() != null) {
				return "cos("+expr+")";
			}else if(func.TAN() != null) {
				return "tan("+expr+")";
			}else if(func.ASIN() != null) {
				return "asin("+expr+")";
			}else if(func.ACOS() != null) {
				return "acos("+expr+")";
			}else if(func.ATAN() != null) {
				return "atan("+expr+")";
			}else if(func.LG() != null) {
				return "lg("+expr+")";
			}else if(func.LOG() != null) {
				return "log("+expr+")";
			}else if(func.LN() != null) {
				return "ln("+expr+")";
			}else if(func.GENHAO() != null) {
				return expr+"^0.5";
			}else if(func.DUISHU() != null) {
				return "ln("+expr+")";
			}else{
				System.err.println("Not supported function '"+ctx.funcname()+"'");
			}	
		}
		return null;
	}

	@Override
	public String visitFuncname(FuncnameContext ctx) {
		return null;
	}

	@Override
	public String visitNumber(calculatorParser.NumberContext ctx) {
		try{
			numParser.parse(ctx.getText());
			if(numParser.getReadExpr().startsWith("-")) {
				numParser.setReadExpr("("+numParser.getReadExpr()+")");
			}
			return numParser.getReadExpr();
		}catch(Exception ex) { 
			return null;
		}
	}
}
