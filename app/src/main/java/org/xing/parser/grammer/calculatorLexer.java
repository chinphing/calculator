// Generated from src/org/xing/parser/grammer/calculator.g4 by ANTLR 4.6
package org.xing.parser.grammer;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class calculatorLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COS=1, SIN=2, TAN=3, ACOS=4, ASIN=5, ATAN=6, LN=7, LOG=8, LG=9, KUOHAO=10, 
		LPAREN=11, RPAREN=12, PLUS=13, MINUS=14, TIMES=15, DIV=16, FRAC=17, GENHAO=18, 
		PINGFANG=19, LIFANG=20, CIFANG=21, KAIFANG=22, DE=23, POINT=24, E=25, 
		POW=26, LETTER=27, DIGIT=28, WS=29;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"COS", "SIN", "TAN", "ACOS", "ASIN", "ATAN", "LN", "LOG", "LG", "KUOHAO", 
		"LPAREN", "RPAREN", "PLUS", "MINUS", "TIMES", "DIV", "FRAC", "GENHAO", 
		"PINGFANG", "LIFANG", "CIFANG", "KAIFANG", "DE", "POINT", "E", "POW", 
		"LETTER", "DIGIT", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'cos'", "'sin'", "'tan'", "'acos'", "'asin'", "'atan'", "'ln'", 
		"'log'", "'lg'", null, "'('", "')'", null, null, null, null, "'分之'", "'根号'", 
		"'平方'", "'立方'", "'次方'", "'开方'", "'的'", null, null, "'^'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "COS", "SIN", "TAN", "ACOS", "ASIN", "ATAN", "LN", "LOG", "LG", 
		"KUOHAO", "LPAREN", "RPAREN", "PLUS", "MINUS", "TIMES", "DIV", "FRAC", 
		"GENHAO", "PINGFANG", "LIFANG", "CIFANG", "KAIFANG", "DE", "POINT", "E", 
		"POW", "LETTER", "DIGIT", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public calculatorLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "calculator.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\37\u00ae\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\3\2\3\2\3"+
		"\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6"+
		"\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\13"+
		"\3\13\3\13\5\13f\n\13\3\f\3\f\3\r\3\r\3\16\3\16\3\16\5\16o\n\16\5\16q"+
		"\n\16\3\17\3\17\3\17\5\17v\n\17\5\17x\n\17\3\20\3\20\3\20\5\20}\n\20\5"+
		"\20\177\n\20\3\21\3\21\3\21\5\21\u0084\n\21\5\21\u0086\n\21\3\22\3\22"+
		"\3\22\3\23\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\26\3\27"+
		"\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\5\34\u00a3\n\34"+
		"\3\35\5\35\u00a6\n\35\3\36\6\36\u00a9\n\36\r\36\16\36\u00aa\3\36\3\36"+
		"\2\2\37\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17"+
		"\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\35"+
		"9\36;\37\3\2\t\4\2,,\u00d9\u00d9\4\2\61\61\u00f9\u00f9\4\2\60\60\u70bb"+
		"\u70bb\4\2GGgg\4\2C\\c|\22\2\62;\u4e02\u4e02\u4e05\u4e05\u4e09\u4e09\u4e0b"+
		"\u4e0b\u4e5f\u4e5f\u4e8e\u4e8e\u4e96\u4e96\u4ec1\u4ec1\u516d\u516d\u516f"+
		"\u516f\u5343\u5343\u5345\u5345\u56dd\u56dd\u7680\u7680\u96f8\u96f8\5\2"+
		"\13\f\17\17\"\"\u00b7\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2"+
		"\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2"+
		"\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2"+
		"\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3"+
		"\2\2\2\29\3\2\2\2\2;\3\2\2\2\3=\3\2\2\2\5A\3\2\2\2\7E\3\2\2\2\tI\3\2\2"+
		"\2\13N\3\2\2\2\rS\3\2\2\2\17X\3\2\2\2\21[\3\2\2\2\23_\3\2\2\2\25e\3\2"+
		"\2\2\27g\3\2\2\2\31i\3\2\2\2\33p\3\2\2\2\35w\3\2\2\2\37~\3\2\2\2!\u0085"+
		"\3\2\2\2#\u0087\3\2\2\2%\u008a\3\2\2\2\'\u008d\3\2\2\2)\u0090\3\2\2\2"+
		"+\u0093\3\2\2\2-\u0096\3\2\2\2/\u0099\3\2\2\2\61\u009b\3\2\2\2\63\u009d"+
		"\3\2\2\2\65\u009f\3\2\2\2\67\u00a2\3\2\2\29\u00a5\3\2\2\2;\u00a8\3\2\2"+
		"\2=>\7e\2\2>?\7q\2\2?@\7u\2\2@\4\3\2\2\2AB\7u\2\2BC\7k\2\2CD\7p\2\2D\6"+
		"\3\2\2\2EF\7v\2\2FG\7c\2\2GH\7p\2\2H\b\3\2\2\2IJ\7c\2\2JK\7e\2\2KL\7q"+
		"\2\2LM\7u\2\2M\n\3\2\2\2NO\7c\2\2OP\7u\2\2PQ\7k\2\2QR\7p\2\2R\f\3\2\2"+
		"\2ST\7c\2\2TU\7v\2\2UV\7c\2\2VW\7p\2\2W\16\3\2\2\2XY\7n\2\2YZ\7p\2\2Z"+
		"\20\3\2\2\2[\\\7n\2\2\\]\7q\2\2]^\7i\2\2^\22\3\2\2\2_`\7n\2\2`a\7i\2\2"+
		"a\24\3\2\2\2bf\7~\2\2cd\7\u62ee\2\2df\7\u53f9\2\2eb\3\2\2\2ec\3\2\2\2"+
		"f\26\3\2\2\2gh\7*\2\2h\30\3\2\2\2ij\7+\2\2j\32\3\2\2\2kq\7-\2\2ln\7\u52a2"+
		"\2\2mo\7\u4e0c\2\2nm\3\2\2\2no\3\2\2\2oq\3\2\2\2pk\3\2\2\2pl\3\2\2\2q"+
		"\34\3\2\2\2rx\7/\2\2su\7\u51d1\2\2tv\7\u53bd\2\2ut\3\2\2\2uv\3\2\2\2v"+
		"x\3\2\2\2wr\3\2\2\2ws\3\2\2\2x\36\3\2\2\2y\177\t\2\2\2z|\7\u4e5a\2\2{"+
		"}\7\u4ee7\2\2|{\3\2\2\2|}\3\2\2\2}\177\3\2\2\2~y\3\2\2\2~z\3\2\2\2\177"+
		" \3\2\2\2\u0080\u0086\t\3\2\2\u0081\u0083\7\u9666\2\2\u0082\u0084\7\u4ee7"+
		"\2\2\u0083\u0082\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0086\3\2\2\2\u0085"+
		"\u0080\3\2\2\2\u0085\u0081\3\2\2\2\u0086\"\3\2\2\2\u0087\u0088\7\u5208"+
		"\2\2\u0088\u0089\7\u4e4d\2\2\u0089$\3\2\2\2\u008a\u008b\7\u683b\2\2\u008b"+
		"\u008c\7\u53f9\2\2\u008c&\3\2\2\2\u008d\u008e\7\u5e75\2\2\u008e\u008f"+
		"\7\u65bb\2\2\u008f(\3\2\2\2\u0090\u0091\7\u7acd\2\2\u0091\u0092\7\u65bb"+
		"\2\2\u0092*\3\2\2\2\u0093\u0094\7\u6b23\2\2\u0094\u0095\7\u65bb\2\2\u0095"+
		",\3\2\2\2\u0096\u0097\7\u5f02\2\2\u0097\u0098\7\u65bb\2\2\u0098.\3\2\2"+
		"\2\u0099\u009a\7\u7686\2\2\u009a\60\3\2\2\2\u009b\u009c\t\4\2\2\u009c"+
		"\62\3\2\2\2\u009d\u009e\t\5\2\2\u009e\64\3\2\2\2\u009f\u00a0\7`\2\2\u00a0"+
		"\66\3\2\2\2\u00a1\u00a3\t\6\2\2\u00a2\u00a1\3\2\2\2\u00a38\3\2\2\2\u00a4"+
		"\u00a6\t\7\2\2\u00a5\u00a4\3\2\2\2\u00a6:\3\2\2\2\u00a7\u00a9\t\b\2\2"+
		"\u00a8\u00a7\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00a8\3\2\2\2\u00aa\u00ab"+
		"\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00ad\b\36\2\2\u00ad<\3\2\2\2\17\2"+
		"enpuw|~\u0083\u0085\u00a2\u00a5\u00aa\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}