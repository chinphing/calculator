package org.xing.calc.parser;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.xing.calc.parser.grammer.calculatorParser;

/**
 * Created by xing on 2017/1/1.
 */

public class CalculatorMathjaxExprVisitor extends CalculatorExprVisitor {

    @Override
    public String visitPowExpression(calculatorParser.PowExpressionContext ctx) {
        String result = visit(ctx.getChild(0));
        if(ctx.getChildCount() > 1) {
            for(int i=2;i<ctx.getChildCount();i+=2) {
                result = "{"+result +"}^{"+visit(ctx.getChild(i))+"}";
            }
        }

        return result.toString();
    }

    @Override
    public String visitChinaPowExpression(
            calculatorParser.ChinaPowExpressionContext ctx) {

        int index = 0;
        String result = visit(ctx.getChild(index++));

        while (index < ctx.getChildCount()) {
            if (ctx.getChild(index + 1) instanceof TerminalNode) {
                int type = ((TerminalNode) ctx.getChild(index + 1)).getSymbol()
                        .getType();
                if (type == calculatorParser.PINGFANG) {
                    result = "{"+result+"}^2";
                } else if (type == calculatorParser.LIFANG) {
                    result = "{"+result+"}^3";
                } else if (type == calculatorParser.KAIFANG) {
                    result = "\\\\sqrt{"+result+"}";
                } else {
                    System.err.println("未处理的终端节点:visitChinaPowExpression");
                }
                index += 2;
            } else {
                String pow = visit(ctx.getChild(index + 1));
                result = "{"+result+"}^{" + pow+"}";
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
                    return "\\\\frac{"+visit(ctx.getChild(2))+"}{"+visit(ctx.getChild(0))+"}";
                }else {
                    return "\\\\frac{"+visit(ctx.getChild(0))+"}{"+visit(ctx.getChild(2))+"}";
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
            calculatorParser.FuncnameExContext func = ctx.funcnameEx();
            String firstNum = visit(ctx.getChild(0));
            String secondNum = visit(ctx.getChild(2));
            if(firstNum == null || secondNum == null) return null;

            if(func.DUISHU() != null) {
                return "log_{"+firstNum+"}"+secondNum;
            }else if(func.GENHAO() != null) {
                return "\\\\sqrt["+firstNum+"]{"+secondNum+"}";
            }
        } else {
            String expr = visit(ctx.getChild(1));
            calculatorParser.FuncnameContext func = ctx.funcname();
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
                return "\\\\sqrt{"+expr+"}";
            }else if(func.DUISHU() != null) {
                return "ln("+expr+")";
            }else{
                System.err.println("Not supported function '"+ctx.funcname()+"'");
            }
        }
        return null;
    }

    @Override
    public String visitNumber(calculatorParser.NumberContext ctx) {
        try{
            numParser.parse(ctx.getText());
            return numParser.getReadExpr();
        }catch(Exception ex) {
            return null;
        }
    }
}
