package org.xing.parser;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.xing.parser.grammer.calculatorBaseVisitor;
import org.xing.parser.grammer.calculatorParser;

public class CalculatorEvalVisitor extends calculatorBaseVisitor<Double> {
	private NumberParser numParser;
	public CalculatorEvalVisitor() {
		numParser = new NumberParser();
	}
	
	@Override
	public Double visitExpression(calculatorParser.ExpressionContext ctx) {
		Double result = visit(ctx.getChild(0));
		if(ctx.getChildCount() > 2) {
			for(int i=1;i<ctx.getChildCount();i+=2) {
				TerminalNode oper = (TerminalNode)ctx.getChild(i);
				Double exprValue = visit(ctx.getChild(i+1));
				if(oper.getSymbol().getType() == calculatorParser.PLUS) {
					result = result + exprValue;
				} else {
					result = result - exprValue;
				}
			}
		}
		return result;
	}

	@Override
	public Double visitMultiplyingExpression(
			calculatorParser.MultiplyingExpressionContext ctx) {
		Double result = visit(ctx.getChild(0));
		
		if(ctx.getChildCount() > 2) {
			for(int i=1;i<ctx.getChildCount();i+=2) {
				
				TerminalNode oper = (TerminalNode)ctx.getChild(i);
				Double exprValue = visit(ctx.getChild(i+1));
				if(oper.getSymbol().getType() == calculatorParser.DIV) {
					result = result / exprValue;
				} else {
					result = result * exprValue;
				}
			}
		}
		return result;
	}

	@Override
	public Double visitPowExpression(calculatorParser.PowExpressionContext ctx) {
		Double result = visit(ctx.getChild(0));
		
		if(ctx.getChildCount() > 1) {	
			for(int i=2;i<ctx.getChildCount();i+=2) {
				Double pow = visit(ctx.getChild(i));
				result = Math.pow(result, pow);
			}
		}
		
		return result;
	}
	
	@Override 
	public Double visitChinaPowExpression(
			calculatorParser.ChinaPowExpressionContext ctx) { 
		Double result = Double.NaN;

		int index = 0;
		result = visit(ctx.getChild(index++));
		while (index < ctx.getChildCount()) {
			if (ctx.getChild(index + 1) instanceof TerminalNode) {
				int type = ((TerminalNode) ctx.getChild(index + 1)).getSymbol()
						.getType();
				if (type == calculatorParser.PINGFANG) {
					result = Math.pow(result, 2);
				} else if (type == calculatorParser.LIFANG) {
					result = Math.pow(result, 3);
				} else if (type == calculatorParser.KAIFANG) {
					result = Math.pow(result, 0.5);
				} else {
					System.err.println("未处理的终端节点:visitChinaPowExpression");
				}
				index += 2;
			} else {
				Double pow = visit(ctx.getChild(index + 1));
				result = Math.pow(result, pow);
				index += 3;
			}
		}
		return result;
	}

	@Override
	public Double visitAtom(calculatorParser.AtomContext ctx) {
		if(ctx.getChildCount() == 3) {
			if(ctx.FRAC() != null) {
				return visit(ctx.getChild(2)) / visit(ctx.getChild(0));
			}else {
				return visit(ctx.expression());
			}
		}else {
			return visit(ctx.getChild(0));
		}
	}

	@Override
	public Double visitFunc(calculatorParser.FuncContext ctx) {
		String funcname = ctx.funcname().getText();
		Double exprValue = visit(ctx.getChild(1));

		Double result = Double.NaN;
		if(exprValue.isNaN()) return result;
		
		switch(funcname) {
		case "sin":
			result = Math.sin(exprValue);
			break;
		case "cos":
			result = Math.cos(exprValue);
		break;
		case "tan":
			result = Math.tan(exprValue);
			break;
		case "asin":
			result = Math.asin(exprValue);
			break;
		case "acos":
			result = Math.acos(exprValue);
			break;
		case "atan":
			result = Math.atan(exprValue);
			break;
		case "lg":
			result = Math.log10(exprValue);
			break;
		case "log":
			result = Math.log(exprValue) / Math.log(2);
			break;
		case "ln":
			result = Math.log(exprValue);
			break;
		case "根号":
			result = Math.pow(exprValue, 0.5);
			break;
		default:
			System.err.println("Not supported function '"+funcname+"'");
			break;
		}
		
		return result;
	}

	@Override
	public Double visitFuncname(calculatorParser.FuncnameContext ctx) {
		return Double.NaN;
	}

	@Override
	public Double visitNumber(calculatorParser.NumberContext ctx) {
		try{
			numParser.parse(ctx.getText());
			return Double.parseDouble(numParser.getEvalExpr());
		}catch(Exception ex) {
			return Double.NaN;
		}
	}
}
