package org.xing.parser;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.xing.parser.grammer.calculatorBaseVisitor;
import org.xing.parser.grammer.calculatorParser;
import org.xing.parser.grammer.calculatorParser.FuncnameContext;
import org.xing.parser.grammer.calculatorParser.FuncnameExContext;

public class CalculatorExprVisitor extends calculatorBaseVisitor<String>{
	private NumberParser numParser;
	public CalculatorExprVisitor() {
		numParser = new NumberParser();
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
				return "("+visit(ctx.getChild(2)) + "/" + visit(ctx.getChild(0))+")";
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
				return "ln(" + secondNum + ")/ln(" + firstNum + ")";
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
