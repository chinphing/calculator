// Generated from src/org/xing/calc/parser/grammer/calculator.g4 by ANTLR 4.6
package org.xing.calc.parser.grammer;
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
		COS=1, SIN=2, TAN=3, ACOS=4, ASIN=5, ATAN=6, LN=7, LOG=8, LG=9, DUISHU=10, 
		GENHAO=11, KUOHAO=12, LPAREN=13, RPAREN=14, PLUS=15, MINUS=16, TIMES=17, 
		DIV=18, FRAC=19, PINGFANG=20, LIFANG=21, CIFANG=22, KAIFANG=23, KAIPINGFANG=24, 
		KAILIFANG=25, DE=26, GEN=27, DU=28, PAI=29, POINT=30, E=31, POW=32, LETTER=33, 
		DIGIT=34, WS=35;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"COS", "SIN", "TAN", "ACOS", "ASIN", "ATAN", "LN", "LOG", "LG", "DUISHU", 
		"GENHAO", "KUOHAO", "LPAREN", "RPAREN", "PLUS", "MINUS", "TIMES", "DIV", 
		"FRAC", "PINGFANG", "LIFANG", "CIFANG", "KAIFANG", "KAIPINGFANG", "KAILIFANG", 
		"DE", "GEN", "DU", "PAI", "POINT", "E", "POW", "LETTER", "DIGIT", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, null, null, "'ln'", "'log'", "'lg'", "'对数'", 
		"'根号'", null, "'('", "')'", null, null, null, null, null, "'平方'", "'立方'", 
		"'次方'", "'开方'", "'开平方'", "'开立方'", "'的'", "'根'", null, null, null, null, 
		"'^'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "COS", "SIN", "TAN", "ACOS", "ASIN", "ATAN", "LN", "LOG", "LG", 
		"DUISHU", "GENHAO", "KUOHAO", "LPAREN", "RPAREN", "PLUS", "MINUS", "TIMES", 
		"DIV", "FRAC", "PINGFANG", "LIFANG", "CIFANG", "KAIFANG", "KAIPINGFANG", 
		"KAILIFANG", "DE", "GEN", "DU", "PAI", "POINT", "E", "POW", "LETTER", 
		"DIGIT", "WS"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2%\u00e2\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\3\2\3\2\3\2\3\2\3\2\5\2O\n\2\3\3\3\3\3\3\3\3\3"+
		"\3\5\3V\n\3\3\4\3\4\3\4\3\4\3\4\5\4]\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5"+
		"\5f\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6o\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\5\7x\n\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\f"+
		"\3\f\3\f\3\r\3\r\3\r\5\r\u008d\n\r\3\16\3\16\3\17\3\17\3\20\3\20\3\20"+
		"\5\20\u0096\n\20\5\20\u0098\n\20\3\21\3\21\3\21\5\21\u009d\n\21\5\21\u009f"+
		"\n\21\3\22\3\22\3\22\5\22\u00a4\n\22\5\22\u00a6\n\22\3\23\3\23\3\23\5"+
		"\23\u00ab\n\23\5\23\u00ad\n\23\3\24\3\24\3\24\5\24\u00b2\n\24\3\25\3\25"+
		"\3\25\3\26\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\31\3\31"+
		"\3\32\3\32\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37"+
		"\3 \3 \3!\3!\3\"\5\"\u00d7\n\"\3#\5#\u00da\n#\3$\6$\u00dd\n$\r$\16$\u00de"+
		"\3$\3$\2\2%\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33"+
		"\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67"+
		"\359\36;\37= ?!A\"C#E$G%\3\2\13\4\2//\u8d21\u8d21\4\2,,\u00d9\u00d9\4"+
		"\2\u00b2\u00b2\u5ea8\u5ea8\4\2\u03c2\u03c2\u6d40\u6d40\4\2\60\60\u70bb"+
		"\u70bb\4\2GGgg\4\2C\\c|\22\2\62;\u4e02\u4e02\u4e05\u4e05\u4e09\u4e09\u4e0b"+
		"\u4e0b\u4e5f\u4e5f\u4e8e\u4e8e\u4e96\u4e96\u4ec1\u4ec1\u516d\u516d\u516f"+
		"\u516f\u5343\u5343\u5345\u5345\u56dd\u56dd\u7680\u7680\u96f8\u96f8\5\2"+
		"\13\f\17\17\"\"\u00f2\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2"+
		"\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2"+
		"\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2"+
		"\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3"+
		"\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2"+
		"\2\2E\3\2\2\2\2G\3\2\2\2\3N\3\2\2\2\5U\3\2\2\2\7\\\3\2\2\2\te\3\2\2\2"+
		"\13n\3\2\2\2\rw\3\2\2\2\17y\3\2\2\2\21|\3\2\2\2\23\u0080\3\2\2\2\25\u0083"+
		"\3\2\2\2\27\u0086\3\2\2\2\31\u008c\3\2\2\2\33\u008e\3\2\2\2\35\u0090\3"+
		"\2\2\2\37\u0097\3\2\2\2!\u009e\3\2\2\2#\u00a5\3\2\2\2%\u00ac\3\2\2\2\'"+
		"\u00b1\3\2\2\2)\u00b3\3\2\2\2+\u00b6\3\2\2\2-\u00b9\3\2\2\2/\u00bc\3\2"+
		"\2\2\61\u00bf\3\2\2\2\63\u00c3\3\2\2\2\65\u00c7\3\2\2\2\67\u00c9\3\2\2"+
		"\29\u00cb\3\2\2\2;\u00cd\3\2\2\2=\u00cf\3\2\2\2?\u00d1\3\2\2\2A\u00d3"+
		"\3\2\2\2C\u00d6\3\2\2\2E\u00d9\3\2\2\2G\u00dc\3\2\2\2IJ\7e\2\2JK\7q\2"+
		"\2KO\7u\2\2LM\7\u4f5b\2\2MO\7\u5f28\2\2NI\3\2\2\2NL\3\2\2\2O\4\3\2\2\2"+
		"PQ\7u\2\2QR\7k\2\2RV\7p\2\2ST\7\u6b65\2\2TV\7\u5f28\2\2UP\3\2\2\2US\3"+
		"\2\2\2V\6\3\2\2\2WX\7v\2\2XY\7c\2\2Y]\7p\2\2Z[\7\u6b65\2\2[]\7\u5209\2"+
		"\2\\W\3\2\2\2\\Z\3\2\2\2]\b\3\2\2\2^_\7c\2\2_`\7e\2\2`a\7q\2\2af\7u\2"+
		"\2bc\7\u53cf\2\2cd\7\u4f5b\2\2df\7\u5f28\2\2e^\3\2\2\2eb\3\2\2\2f\n\3"+
		"\2\2\2gh\7c\2\2hi\7u\2\2ij\7k\2\2jo\7p\2\2kl\7\u53cf\2\2lm\7\u6b65\2\2"+
		"mo\7\u5f28\2\2ng\3\2\2\2nk\3\2\2\2o\f\3\2\2\2pq\7c\2\2qr\7v\2\2rs\7c\2"+
		"\2sx\7p\2\2tu\7\u53cf\2\2uv\7\u6b65\2\2vx\7\u5209\2\2wp\3\2\2\2wt\3\2"+
		"\2\2x\16\3\2\2\2yz\7n\2\2z{\7p\2\2{\20\3\2\2\2|}\7n\2\2}~\7q\2\2~\177"+
		"\7i\2\2\177\22\3\2\2\2\u0080\u0081\7n\2\2\u0081\u0082\7i\2\2\u0082\24"+
		"\3\2\2\2\u0083\u0084\7\u5bfb\2\2\u0084\u0085\7\u6572\2\2\u0085\26\3\2"+
		"\2\2\u0086\u0087\7\u683b\2\2\u0087\u0088\7\u53f9\2\2\u0088\30\3\2\2\2"+
		"\u0089\u008d\7~\2\2\u008a\u008b\7\u62ee\2\2\u008b\u008d\7\u53f9\2\2\u008c"+
		"\u0089\3\2\2\2\u008c\u008a\3\2\2\2\u008d\32\3\2\2\2\u008e\u008f\7*\2\2"+
		"\u008f\34\3\2\2\2\u0090\u0091\7+\2\2\u0091\36\3\2\2\2\u0092\u0098\7-\2"+
		"\2\u0093\u0095\7\u52a2\2\2\u0094\u0096\7\u4e0c\2\2\u0095\u0094\3\2\2\2"+
		"\u0095\u0096\3\2\2\2\u0096\u0098\3\2\2\2\u0097\u0092\3\2\2\2\u0097\u0093"+
		"\3\2\2\2\u0098 \3\2\2\2\u0099\u009f\t\2\2\2\u009a\u009c\7\u51d1\2\2\u009b"+
		"\u009d\7\u53bd\2\2\u009c\u009b\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u009f"+
		"\3\2\2\2\u009e\u0099\3\2\2\2\u009e\u009a\3\2\2\2\u009f\"\3\2\2\2\u00a0"+
		"\u00a6\t\3\2\2\u00a1\u00a3\7\u4e5a\2\2\u00a2\u00a4\7\u4ee7\2\2\u00a3\u00a2"+
		"\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a6\3\2\2\2\u00a5\u00a0\3\2\2\2\u00a5"+
		"\u00a1\3\2\2\2\u00a6$\3\2\2\2\u00a7\u00ad\7\u00f9\2\2\u00a8\u00aa\7\u9666"+
		"\2\2\u00a9\u00ab\7\u4ee7\2\2\u00aa\u00a9\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab"+
		"\u00ad\3\2\2\2\u00ac\u00a7\3\2\2\2\u00ac\u00a8\3\2\2\2\u00ad&\3\2\2\2"+
		"\u00ae\u00b2\7\61\2\2\u00af\u00b0\7\u5208\2\2\u00b0\u00b2\7\u4e4d\2\2"+
		"\u00b1\u00ae\3\2\2\2\u00b1\u00af\3\2\2\2\u00b2(\3\2\2\2\u00b3\u00b4\7"+
		"\u5e75\2\2\u00b4\u00b5\7\u65bb\2\2\u00b5*\3\2\2\2\u00b6\u00b7\7\u7acd"+
		"\2\2\u00b7\u00b8\7\u65bb\2\2\u00b8,\3\2\2\2\u00b9\u00ba\7\u6b23\2\2\u00ba"+
		"\u00bb\7\u65bb\2\2\u00bb.\3\2\2\2\u00bc\u00bd\7\u5f02\2\2\u00bd\u00be"+
		"\7\u65bb\2\2\u00be\60\3\2\2\2\u00bf\u00c0\7\u5f02\2\2\u00c0\u00c1\7\u5e75"+
		"\2\2\u00c1\u00c2\7\u65bb\2\2\u00c2\62\3\2\2\2\u00c3\u00c4\7\u5f02\2\2"+
		"\u00c4\u00c5\7\u7acd\2\2\u00c5\u00c6\7\u65bb\2\2\u00c6\64\3\2\2\2\u00c7"+
		"\u00c8\7\u7686\2\2\u00c8\66\3\2\2\2\u00c9\u00ca\7\u683b\2\2\u00ca8\3\2"+
		"\2\2\u00cb\u00cc\t\4\2\2\u00cc:\3\2\2\2\u00cd\u00ce\t\5\2\2\u00ce<\3\2"+
		"\2\2\u00cf\u00d0\t\6\2\2\u00d0>\3\2\2\2\u00d1\u00d2\t\7\2\2\u00d2@\3\2"+
		"\2\2\u00d3\u00d4\7`\2\2\u00d4B\3\2\2\2\u00d5\u00d7\t\b\2\2\u00d6\u00d5"+
		"\3\2\2\2\u00d7D\3\2\2\2\u00d8\u00da\t\t\2\2\u00d9\u00d8\3\2\2\2\u00da"+
		"F\3\2\2\2\u00db\u00dd\t\n\2\2\u00dc\u00db\3\2\2\2\u00dd\u00de\3\2\2\2"+
		"\u00de\u00dc\3\2\2\2\u00de\u00df\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0\u00e1"+
		"\b$\2\2\u00e1H\3\2\2\2\26\2NU\\enw\u008c\u0095\u0097\u009c\u009e\u00a3"+
		"\u00a5\u00aa\u00ac\u00b1\u00d6\u00d9\u00de\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}