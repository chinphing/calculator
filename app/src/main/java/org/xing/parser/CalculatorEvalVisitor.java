package org.xing.parser;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.xing.parser.grammer.calculatorBaseVisitor;
import org.xing.parser.grammer.calculatorParser;
import org.xing.parser.grammer.calculatorParser.FuncContext;
import org.xing.parser.grammer.calculatorParser.FuncnameContext;
import org.xing.parser.grammer.calculatorParser.FuncnameExContext;

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
	public Double visitFunc(FuncContext ctx) {
		Double result = Double.NaN;
		if(ctx.getChildCount() == 3) {
			FuncnameExContext func = ctx.funcnameEx();
			Double firstNum = visit(ctx.getChild(0));
			Double secondNum = visit(ctx.getChild(2));
			if(func.DUISHU() != null) {
				result = Math.log(secondNum) / Math.log(firstNum);
			}else if(func.GENHAO() != null) {
				result = Math.pow(secondNum, 1/firstNum);
			}
		} else {
			FuncnameContext func = ctx.funcname();
			Double exprValue = visit(ctx.getChild(1));

			if(exprValue.isNaN()) return result;
			
			if(func.SIN() != null) {
				result = Math.sin(exprValue);
			}else if(func.COS() != null) {
				result = Math.cos(exprValue);
			}else if(func.TAN() != null) {
				result = Math.tan(exprValue);
			}else if(func.ASIN() != null) {
				result = Math.asin(exprValue);
			}else if(func.ACOS() != null) {
				result = Math.acos(exprValue);
			}else if(func.ATAN() != null) {
				result = Math.atan(exprValue);
			}else if(func.LG() != null) {
				result = Math.log10(exprValue);
			}else if(func.LOG() != null) {
				result =  Math.log(exprValue) / Math.log(2);
			}else if(func.LN() != null) {
				result =  Math.log(exprValue);
			}else if(func.GENHAO() != null) {
				result =  Math.pow(exprValue, 0.5);
			}else if(func.DUISHU() != null) {
				result =  Math.log(exprValue);
			}else{
				System.err.println("Not supported function '"+ctx.funcname()+"'");
			}	
		}
		
		return result;
	}

	@Override
	public Double visitFuncname(FuncnameContext ctx) {
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
