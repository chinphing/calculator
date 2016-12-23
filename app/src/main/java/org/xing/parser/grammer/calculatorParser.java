// Generated from src/org/xing/parser/grammer/calculator.g4 by ANTLR 4.6
package org.xing.parser.grammer;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class calculatorParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COS=1, SIN=2, TAN=3, ACOS=4, ASIN=5, ATAN=6, LN=7, LOG=8, LG=9, GENHAO=10, 
		KUOHAO=11, LPAREN=12, RPAREN=13, PLUS=14, MINUS=15, TIMES=16, DIV=17, 
		FRAC=18, PINGFANG=19, LIFANG=20, CIFANG=21, KAIFANG=22, DE=23, POINT=24, 
		E=25, POW=26, LETTER=27, DIGIT=28, WS=29;
	public static final int
		RULE_expression = 0, RULE_multiplyingExpression = 1, RULE_powExpression = 2, 
		RULE_chinaPowExpression = 3, RULE_atom = 4, RULE_func = 5, RULE_funcname = 6, 
		RULE_number = 7;
	public static final String[] ruleNames = {
		"expression", "multiplyingExpression", "powExpression", "chinaPowExpression", 
		"atom", "func", "funcname", "number"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'cos'", "'sin'", "'tan'", "'acos'", "'asin'", "'atan'", "'ln'", 
		"'log'", "'lg'", "'根号'", null, "'('", "')'", null, null, null, null, "'分之'", 
		"'平方'", "'立方'", "'次方'", "'开方'", "'的'", null, null, "'^'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "COS", "SIN", "TAN", "ACOS", "ASIN", "ATAN", "LN", "LOG", "LG", 
		"GENHAO", "KUOHAO", "LPAREN", "RPAREN", "PLUS", "MINUS", "TIMES", "DIV", 
		"FRAC", "PINGFANG", "LIFANG", "CIFANG", "KAIFANG", "DE", "POINT", "E", 
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

	@Override
	public String getGrammarFileName() { return "calculator.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public calculatorParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ExpressionContext extends ParserRuleContext {
		public List<MultiplyingExpressionContext> multiplyingExpression() {
			return getRuleContexts(MultiplyingExpressionContext.class);
		}
		public MultiplyingExpressionContext multiplyingExpression(int i) {
			return getRuleContext(MultiplyingExpressionContext.class,i);
		}
		public List<TerminalNode> PLUS() { return getTokens(calculatorParser.PLUS); }
		public TerminalNode PLUS(int i) {
			return getToken(calculatorParser.PLUS, i);
		}
		public List<TerminalNode> MINUS() { return getTokens(calculatorParser.MINUS); }
		public TerminalNode MINUS(int i) {
			return getToken(calculatorParser.MINUS, i);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof calculatorVisitor ) return ((calculatorVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(16);
			multiplyingExpression();
			setState(21);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PLUS || _la==MINUS) {
				{
				{
				setState(17);
				_la = _input.LA(1);
				if ( !(_la==PLUS || _la==MINUS) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(18);
				multiplyingExpression();
				}
				}
				setState(23);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultiplyingExpressionContext extends ParserRuleContext {
		public List<PowExpressionContext> powExpression() {
			return getRuleContexts(PowExpressionContext.class);
		}
		public PowExpressionContext powExpression(int i) {
			return getRuleContext(PowExpressionContext.class,i);
		}
		public List<TerminalNode> TIMES() { return getTokens(calculatorParser.TIMES); }
		public TerminalNode TIMES(int i) {
			return getToken(calculatorParser.TIMES, i);
		}
		public List<TerminalNode> DIV() { return getTokens(calculatorParser.DIV); }
		public TerminalNode DIV(int i) {
			return getToken(calculatorParser.DIV, i);
		}
		public MultiplyingExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplyingExpression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof calculatorVisitor ) return ((calculatorVisitor<? extends T>)visitor).visitMultiplyingExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiplyingExpressionContext multiplyingExpression() throws RecognitionException {
		MultiplyingExpressionContext _localctx = new MultiplyingExpressionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_multiplyingExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			powExpression();
			setState(29);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TIMES || _la==DIV) {
				{
				{
				setState(25);
				_la = _input.LA(1);
				if ( !(_la==TIMES || _la==DIV) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(26);
				powExpression();
				}
				}
				setState(31);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PowExpressionContext extends ParserRuleContext {
		public List<AtomContext> atom() {
			return getRuleContexts(AtomContext.class);
		}
		public AtomContext atom(int i) {
			return getRuleContext(AtomContext.class,i);
		}
		public List<TerminalNode> POW() { return getTokens(calculatorParser.POW); }
		public TerminalNode POW(int i) {
			return getToken(calculatorParser.POW, i);
		}
		public ChinaPowExpressionContext chinaPowExpression() {
			return getRuleContext(ChinaPowExpressionContext.class,0);
		}
		public PowExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_powExpression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof calculatorVisitor ) return ((calculatorVisitor<? extends T>)visitor).visitPowExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PowExpressionContext powExpression() throws RecognitionException {
		PowExpressionContext _localctx = new PowExpressionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_powExpression);
		int _la;
		try {
			setState(41);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(32);
				atom();
				setState(37);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==POW) {
					{
					{
					setState(33);
					match(POW);
					setState(34);
					atom();
					}
					}
					setState(39);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(40);
				chinaPowExpression();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ChinaPowExpressionContext extends ParserRuleContext {
		public List<AtomContext> atom() {
			return getRuleContexts(AtomContext.class);
		}
		public AtomContext atom(int i) {
			return getRuleContext(AtomContext.class,i);
		}
		public List<TerminalNode> DE() { return getTokens(calculatorParser.DE); }
		public TerminalNode DE(int i) {
			return getToken(calculatorParser.DE, i);
		}
		public List<TerminalNode> PINGFANG() { return getTokens(calculatorParser.PINGFANG); }
		public TerminalNode PINGFANG(int i) {
			return getToken(calculatorParser.PINGFANG, i);
		}
		public List<TerminalNode> LIFANG() { return getTokens(calculatorParser.LIFANG); }
		public TerminalNode LIFANG(int i) {
			return getToken(calculatorParser.LIFANG, i);
		}
		public List<TerminalNode> KAIFANG() { return getTokens(calculatorParser.KAIFANG); }
		public TerminalNode KAIFANG(int i) {
			return getToken(calculatorParser.KAIFANG, i);
		}
		public List<TerminalNode> CIFANG() { return getTokens(calculatorParser.CIFANG); }
		public TerminalNode CIFANG(int i) {
			return getToken(calculatorParser.CIFANG, i);
		}
		public ChinaPowExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_chinaPowExpression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof calculatorVisitor ) return ((calculatorVisitor<? extends T>)visitor).visitChinaPowExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ChinaPowExpressionContext chinaPowExpression() throws RecognitionException {
		ChinaPowExpressionContext _localctx = new ChinaPowExpressionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_chinaPowExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			atom();
			setState(55);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DE) {
				{
				{
				setState(44);
				match(DE);
				setState(51);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case COS:
				case SIN:
				case TAN:
				case ACOS:
				case ASIN:
				case ATAN:
				case LN:
				case LOG:
				case LG:
				case GENHAO:
				case KUOHAO:
				case LPAREN:
				case MINUS:
				case DIGIT:
					{
					{
					setState(45);
					atom();
					setState(46);
					match(CIFANG);
					}
					}
					break;
				case PINGFANG:
					{
					setState(48);
					match(PINGFANG);
					}
					break;
				case LIFANG:
					{
					setState(49);
					match(LIFANG);
					}
					break;
				case KAIFANG:
					{
					setState(50);
					match(KAIFANG);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				}
				setState(57);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AtomContext extends ParserRuleContext {
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public TerminalNode FRAC() { return getToken(calculatorParser.FRAC, 0); }
		public TerminalNode LPAREN() { return getToken(calculatorParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(calculatorParser.RPAREN, 0); }
		public List<TerminalNode> KUOHAO() { return getTokens(calculatorParser.KUOHAO); }
		public TerminalNode KUOHAO(int i) {
			return getToken(calculatorParser.KUOHAO, i);
		}
		public FuncContext func() {
			return getRuleContext(FuncContext.class,0);
		}
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof calculatorVisitor ) return ((calculatorVisitor<? extends T>)visitor).visitAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_atom);
		try {
			setState(72);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(58);
				number();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(59);
				number();
				setState(60);
				match(FRAC);
				setState(61);
				number();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(63);
				match(LPAREN);
				setState(64);
				expression();
				setState(65);
				match(RPAREN);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(67);
				match(KUOHAO);
				setState(68);
				expression();
				setState(69);
				match(KUOHAO);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(71);
				func();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncContext extends ParserRuleContext {
		public FuncnameContext funcname() {
			return getRuleContext(FuncnameContext.class,0);
		}
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public FuncContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof calculatorVisitor ) return ((calculatorVisitor<? extends T>)visitor).visitFunc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncContext func() throws RecognitionException {
		FuncContext _localctx = new FuncContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_func);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			funcname();
			setState(75);
			atom();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncnameContext extends ParserRuleContext {
		public TerminalNode COS() { return getToken(calculatorParser.COS, 0); }
		public TerminalNode TAN() { return getToken(calculatorParser.TAN, 0); }
		public TerminalNode SIN() { return getToken(calculatorParser.SIN, 0); }
		public TerminalNode ACOS() { return getToken(calculatorParser.ACOS, 0); }
		public TerminalNode ATAN() { return getToken(calculatorParser.ATAN, 0); }
		public TerminalNode ASIN() { return getToken(calculatorParser.ASIN, 0); }
		public TerminalNode LOG() { return getToken(calculatorParser.LOG, 0); }
		public TerminalNode LN() { return getToken(calculatorParser.LN, 0); }
		public TerminalNode LG() { return getToken(calculatorParser.LG, 0); }
		public TerminalNode GENHAO() { return getToken(calculatorParser.GENHAO, 0); }
		public FuncnameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcname; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof calculatorVisitor ) return ((calculatorVisitor<? extends T>)visitor).visitFuncname(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncnameContext funcname() throws RecognitionException {
		FuncnameContext _localctx = new FuncnameContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_funcname);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COS) | (1L << SIN) | (1L << TAN) | (1L << ACOS) | (1L << ASIN) | (1L << ATAN) | (1L << LN) | (1L << LOG) | (1L << LG) | (1L << GENHAO))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumberContext extends ParserRuleContext {
		public TerminalNode MINUS() { return getToken(calculatorParser.MINUS, 0); }
		public List<TerminalNode> DIGIT() { return getTokens(calculatorParser.DIGIT); }
		public TerminalNode DIGIT(int i) {
			return getToken(calculatorParser.DIGIT, i);
		}
		public TerminalNode POINT() { return getToken(calculatorParser.POINT, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof calculatorVisitor ) return ((calculatorVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==MINUS) {
				{
				setState(79);
				match(MINUS);
				}
			}

			setState(83); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(82);
				match(DIGIT);
				}
				}
				setState(85); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DIGIT );
			setState(93);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==POINT) {
				{
				setState(87);
				match(POINT);
				setState(89); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(88);
					match(DIGIT);
					}
					}
					setState(91); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==DIGIT );
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\37b\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\3\2\7\2\26"+
		"\n\2\f\2\16\2\31\13\2\3\3\3\3\3\3\7\3\36\n\3\f\3\16\3!\13\3\3\4\3\4\3"+
		"\4\7\4&\n\4\f\4\16\4)\13\4\3\4\5\4,\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\5\5\66\n\5\7\58\n\5\f\5\16\5;\13\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\5\6K\n\6\3\7\3\7\3\7\3\b\3\b\3\t\5\tS\n\t\3\t"+
		"\6\tV\n\t\r\t\16\tW\3\t\3\t\6\t\\\n\t\r\t\16\t]\5\t`\n\t\3\t\2\2\n\2\4"+
		"\6\b\n\f\16\20\2\5\3\2\20\21\3\2\22\23\3\2\3\fi\2\22\3\2\2\2\4\32\3\2"+
		"\2\2\6+\3\2\2\2\b-\3\2\2\2\nJ\3\2\2\2\fL\3\2\2\2\16O\3\2\2\2\20R\3\2\2"+
		"\2\22\27\5\4\3\2\23\24\t\2\2\2\24\26\5\4\3\2\25\23\3\2\2\2\26\31\3\2\2"+
		"\2\27\25\3\2\2\2\27\30\3\2\2\2\30\3\3\2\2\2\31\27\3\2\2\2\32\37\5\6\4"+
		"\2\33\34\t\3\2\2\34\36\5\6\4\2\35\33\3\2\2\2\36!\3\2\2\2\37\35\3\2\2\2"+
		"\37 \3\2\2\2 \5\3\2\2\2!\37\3\2\2\2\"\'\5\n\6\2#$\7\34\2\2$&\5\n\6\2%"+
		"#\3\2\2\2&)\3\2\2\2\'%\3\2\2\2\'(\3\2\2\2(,\3\2\2\2)\'\3\2\2\2*,\5\b\5"+
		"\2+\"\3\2\2\2+*\3\2\2\2,\7\3\2\2\2-9\5\n\6\2.\65\7\31\2\2/\60\5\n\6\2"+
		"\60\61\7\27\2\2\61\66\3\2\2\2\62\66\7\25\2\2\63\66\7\26\2\2\64\66\7\30"+
		"\2\2\65/\3\2\2\2\65\62\3\2\2\2\65\63\3\2\2\2\65\64\3\2\2\2\668\3\2\2\2"+
		"\67.\3\2\2\28;\3\2\2\29\67\3\2\2\29:\3\2\2\2:\t\3\2\2\2;9\3\2\2\2<K\5"+
		"\20\t\2=>\5\20\t\2>?\7\24\2\2?@\5\20\t\2@K\3\2\2\2AB\7\16\2\2BC\5\2\2"+
		"\2CD\7\17\2\2DK\3\2\2\2EF\7\r\2\2FG\5\2\2\2GH\7\r\2\2HK\3\2\2\2IK\5\f"+
		"\7\2J<\3\2\2\2J=\3\2\2\2JA\3\2\2\2JE\3\2\2\2JI\3\2\2\2K\13\3\2\2\2LM\5"+
		"\16\b\2MN\5\n\6\2N\r\3\2\2\2OP\t\4\2\2P\17\3\2\2\2QS\7\21\2\2RQ\3\2\2"+
		"\2RS\3\2\2\2SU\3\2\2\2TV\7\36\2\2UT\3\2\2\2VW\3\2\2\2WU\3\2\2\2WX\3\2"+
		"\2\2X_\3\2\2\2Y[\7\32\2\2Z\\\7\36\2\2[Z\3\2\2\2\\]\3\2\2\2][\3\2\2\2]"+
		"^\3\2\2\2^`\3\2\2\2_Y\3\2\2\2_`\3\2\2\2`\21\3\2\2\2\r\27\37\'+\659JRW"+
		"]_";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}