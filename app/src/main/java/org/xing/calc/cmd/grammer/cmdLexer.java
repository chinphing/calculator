// Generated from src/org/xing/calc/cmd/grammer/cmd.g4 by ANTLR 4.6
package org.xing.calc.cmd.grammer;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class cmdLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, WS=23;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "T__18", "T__19", "T__20", "T__21", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'清屏'", "'清空'", "'清除'", "'全部'", "'撤销'", "'取消'", "'倒退'", "'后退'", 
		"'删除'", "'帮助'", "'示例'", "'说明'", "'升级'", "'版本'", "'主题'", "'风格'", "'背景'", 
		"'引擎'", "'百度'", "'讯飞'", "'退出'", "'关闭'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, "WS"
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


	public cmdLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "cmd.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\31z\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\3\2\3\2\3"+
		"\2\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b"+
		"\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3"+
		"\16\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3"+
		"\22\3\23\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\26\3\27\3"+
		"\27\3\27\3\30\6\30u\n\30\r\30\16\30v\3\30\3\30\2\2\31\3\3\5\4\7\5\t\6"+
		"\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24"+
		"\'\25)\26+\27-\30/\31\3\2\3\5\2\13\f\17\17\"\"z\2\3\3\2\2\2\2\5\3\2\2"+
		"\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\3\61\3\2\2\2\5\64\3"+
		"\2\2\2\7\67\3\2\2\2\t:\3\2\2\2\13=\3\2\2\2\r@\3\2\2\2\17C\3\2\2\2\21F"+
		"\3\2\2\2\23I\3\2\2\2\25L\3\2\2\2\27O\3\2\2\2\31R\3\2\2\2\33U\3\2\2\2\35"+
		"X\3\2\2\2\37[\3\2\2\2!^\3\2\2\2#a\3\2\2\2%d\3\2\2\2\'g\3\2\2\2)j\3\2\2"+
		"\2+m\3\2\2\2-p\3\2\2\2/t\3\2\2\2\61\62\7\u6e07\2\2\62\63\7\u5c51\2\2\63"+
		"\4\3\2\2\2\64\65\7\u6e07\2\2\65\66\7\u7a7c\2\2\66\6\3\2\2\2\678\7\u6e07"+
		"\2\289\7\u9666\2\29\b\3\2\2\2:;\7\u516a\2\2;<\7\u90ea\2\2<\n\3\2\2\2="+
		">\7\u64a6\2\2>?\7\u9502\2\2?\f\3\2\2\2@A\7\u53d8\2\2AB\7\u6d8a\2\2B\16"+
		"\3\2\2\2CD\7\u5014\2\2DE\7\u9002\2\2E\20\3\2\2\2FG\7\u5410\2\2GH\7\u9002"+
		"\2\2H\22\3\2\2\2IJ\7\u5222\2\2JK\7\u9666\2\2K\24\3\2\2\2LM\7\u5e30\2\2"+
		"MN\7\u52ab\2\2N\26\3\2\2\2OP\7\u793c\2\2PQ\7\u4f8d\2\2Q\30\3\2\2\2RS\7"+
		"\u8bf6\2\2ST\7\u6610\2\2T\32\3\2\2\2UV\7\u5349\2\2VW\7\u7ea9\2\2W\34\3"+
		"\2\2\2XY\7\u724a\2\2YZ\7\u672e\2\2Z\36\3\2\2\2[\\\7\u4e3d\2\2\\]\7\u989a"+
		"\2\2] \3\2\2\2^_\7\u98d0\2\2_`\7\u683e\2\2`\"\3\2\2\2ab\7\u80ce\2\2bc"+
		"\7\u6671\2\2c$\3\2\2\2de\7\u5f17\2\2ef\7\u64d0\2\2f&\3\2\2\2gh\7\u7680"+
		"\2\2hi\7\u5ea8\2\2i(\3\2\2\2jk\7\u8bb1\2\2kl\7\u98e0\2\2l*\3\2\2\2mn\7"+
		"\u9002\2\2no\7\u51fc\2\2o,\3\2\2\2pq\7\u5175\2\2qr\7\u95ef\2\2r.\3\2\2"+
		"\2su\t\2\2\2ts\3\2\2\2uv\3\2\2\2vt\3\2\2\2vw\3\2\2\2wx\3\2\2\2xy\b\30"+
		"\2\2y\60\3\2\2\2\4\2v\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}