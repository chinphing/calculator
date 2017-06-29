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
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, WS=24;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'清屏'", "'清空'", "'清除'", "'全部'", "'撤销'", "'取消'", "'倒退'", "'后退'", 
		"'删除'", "'帮助'", "'示例'", "'说明'", "'升级'", "'版本'", "'主题'", "'风格'", "'背景'", 
		"'引擎'", "'百度'", "'讯飞'", "'退出'", "'关闭'", "'暂停'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		"WS"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\32\177\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\3\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3"+
		"\7\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\r"+
		"\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\21\3"+
		"\22\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3"+
		"\26\3\27\3\27\3\27\3\30\3\30\3\30\3\31\6\31z\n\31\r\31\16\31{\3\31\3\31"+
		"\2\2\32\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17"+
		"\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\3\2\3\5\2\13\f\17"+
		"\17\"\"\177\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2"+
		"\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2"+
		"\2/\3\2\2\2\2\61\3\2\2\2\3\63\3\2\2\2\5\66\3\2\2\2\79\3\2\2\2\t<\3\2\2"+
		"\2\13?\3\2\2\2\rB\3\2\2\2\17E\3\2\2\2\21H\3\2\2\2\23K\3\2\2\2\25N\3\2"+
		"\2\2\27Q\3\2\2\2\31T\3\2\2\2\33W\3\2\2\2\35Z\3\2\2\2\37]\3\2\2\2!`\3\2"+
		"\2\2#c\3\2\2\2%f\3\2\2\2\'i\3\2\2\2)l\3\2\2\2+o\3\2\2\2-r\3\2\2\2/u\3"+
		"\2\2\2\61y\3\2\2\2\63\64\7\u6e07\2\2\64\65\7\u5c51\2\2\65\4\3\2\2\2\66"+
		"\67\7\u6e07\2\2\678\7\u7a7c\2\28\6\3\2\2\29:\7\u6e07\2\2:;\7\u9666\2\2"+
		";\b\3\2\2\2<=\7\u516a\2\2=>\7\u90ea\2\2>\n\3\2\2\2?@\7\u64a6\2\2@A\7\u9502"+
		"\2\2A\f\3\2\2\2BC\7\u53d8\2\2CD\7\u6d8a\2\2D\16\3\2\2\2EF\7\u5014\2\2"+
		"FG\7\u9002\2\2G\20\3\2\2\2HI\7\u5410\2\2IJ\7\u9002\2\2J\22\3\2\2\2KL\7"+
		"\u5222\2\2LM\7\u9666\2\2M\24\3\2\2\2NO\7\u5e30\2\2OP\7\u52ab\2\2P\26\3"+
		"\2\2\2QR\7\u793c\2\2RS\7\u4f8d\2\2S\30\3\2\2\2TU\7\u8bf6\2\2UV\7\u6610"+
		"\2\2V\32\3\2\2\2WX\7\u5349\2\2XY\7\u7ea9\2\2Y\34\3\2\2\2Z[\7\u724a\2\2"+
		"[\\\7\u672e\2\2\\\36\3\2\2\2]^\7\u4e3d\2\2^_\7\u989a\2\2_ \3\2\2\2`a\7"+
		"\u98d0\2\2ab\7\u683e\2\2b\"\3\2\2\2cd\7\u80ce\2\2de\7\u6671\2\2e$\3\2"+
		"\2\2fg\7\u5f17\2\2gh\7\u64d0\2\2h&\3\2\2\2ij\7\u7680\2\2jk\7\u5ea8\2\2"+
		"k(\3\2\2\2lm\7\u8bb1\2\2mn\7\u98e0\2\2n*\3\2\2\2op\7\u9002\2\2pq\7\u51fc"+
		"\2\2q,\3\2\2\2rs\7\u5175\2\2st\7\u95ef\2\2t.\3\2\2\2uv\7\u6684\2\2vw\7"+
		"\u505e\2\2w\60\3\2\2\2xz\t\2\2\2yx\3\2\2\2z{\3\2\2\2{y\3\2\2\2{|\3\2\2"+
		"\2|}\3\2\2\2}~\b\31\2\2~\62\3\2\2\2\4\2{\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}