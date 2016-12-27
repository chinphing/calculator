// Generated from src/org/xing/parser/grammer/calculator.g4 by ANTLR 4.6
package org.xing.parser.grammer;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link calculatorParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface calculatorVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link calculatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(calculatorParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link calculatorParser#multiplyingExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplyingExpression(calculatorParser.MultiplyingExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link calculatorParser#powExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPowExpression(calculatorParser.PowExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link calculatorParser#chinaPowExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChinaPowExpression(calculatorParser.ChinaPowExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link calculatorParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(calculatorParser.AtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link calculatorParser#func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc(calculatorParser.FuncContext ctx);
	/**
	 * Visit a parse tree produced by {@link calculatorParser#funcname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncname(calculatorParser.FuncnameContext ctx);
	/**
	 * Visit a parse tree produced by {@link calculatorParser#funcnameEx}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncnameEx(calculatorParser.FuncnameExContext ctx);
	/**
	 * Visit a parse tree produced by {@link calculatorParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(calculatorParser.NumberContext ctx);
}