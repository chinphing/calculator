package org.xing.calc.cmd;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.xing.calc.cmd.grammer.cmdLexer;
import org.xing.calc.cmd.grammer.cmdParser;

public class CmdParser {
	public CmdParser() {
		
	}
	
	public int parse(String expr) {
		try {
			ANTLRInputStream input = new ANTLRInputStream(expr);
			cmdLexer lexer = new cmdLexer(input);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			cmdParser parser = new cmdParser(tokens);
			ParseTree tree = parser.command();
			if(tree == null) return 0;
			else {
				CmdDefaultVisitor visitor = new CmdDefaultVisitor();
				return visitor.visit(tree);
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}
}
