// Generated from src/org/xing/calc/cmd/grammer/cmd.g4 by ANTLR 4.6
package org.xing.calc.cmd.grammer;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link cmdParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface cmdVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link cmdParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommand(cmdParser.CommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link cmdParser#qingping}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQingping(cmdParser.QingpingContext ctx);
	/**
	 * Visit a parse tree produced by {@link cmdParser#goback}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGoback(cmdParser.GobackContext ctx);
	/**
	 * Visit a parse tree produced by {@link cmdParser#help}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHelp(cmdParser.HelpContext ctx);
	/**
	 * Visit a parse tree produced by {@link cmdParser#update}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdate(cmdParser.UpdateContext ctx);
	/**
	 * Visit a parse tree produced by {@link cmdParser#theme}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTheme(cmdParser.ThemeContext ctx);
	/**
	 * Visit a parse tree produced by {@link cmdParser#engine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEngine(cmdParser.EngineContext ctx);
	/**
	 * Visit a parse tree produced by {@link cmdParser#close}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClose(cmdParser.CloseContext ctx);
}