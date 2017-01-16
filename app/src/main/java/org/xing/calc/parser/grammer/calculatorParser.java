// Generated from src/org/xing/calc/parser/grammer/calculator.g4 by ANTLR 4.6
package org.xing.calc.parser.grammer;
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
		COS=1, SIN=2, TAN=3, ACOS=4, ASIN=5, ATAN=6, LN=7, LOG=8, LG=9, DUISHU=10, 
		GENHAO=11, KUOHAO=12, LPAREN=13, RPAREN=14, PLUS=15, MINUS=16, TIMES=17, 
		DIV=18, FRAC=19, YOU=20, PINGFANG=21, LIFANG=22, CIFANG=23, KAIFANG=24, 
		KAIPINGFANG=25, KAILIFANG=26, DE=27, GEN=28, DU=29, PAI=30, POINT=31, 
		E=32, POW=33, LETTER=34, DIGIT=35, WS=36;
	public static final int
		RULE_expression = 0, RULE_multiplyingExpression = 1, RULE_powExpression = 2, 
		RULE_chinaPowExpression = 3, RULE_atom = 4, RULE_function = 5, RULE_funcname = 6, 
		RULE_funcnameEx = 7, RULE_postFuncname = 8, RULE_number = 9;
	public static final String[] ruleNames = {
		"expression", "multiplyingExpression", "powExpression", "chinaPowExpression", 
		"atom", "function", "funcname", "funcnameEx", "postFuncname", "number"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, null, null, "'ln'", "'log'", "'lg'", "'对数'", 
		"'根号'", null, "'('", "')'", null, null, null, null, null, "'又'", "'平方'", 
		"'立方'", "'次方'", "'开方'", "'开平方'", "'开立方'", "'的'", "'根'", null, null, null, 
		null, "'^'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "COS", "SIN", "TAN", "ACOS", "ASIN", "ATAN", "LN", "LOG", "LG", 
		"DUISHU", "GENHAO", "KUOHAO", "LPAREN", "RPAREN", "PLUS", "MINUS", "TIMES", 
		"DIV", "FRAC", "YOU", "PINGFANG", "LIFANG", "CIFANG", "KAIFANG", "KAIPINGFANG", 
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
			setState(20);
			multiplyingExpression();
			setState(25);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PLUS || _la==MINUS) {
				{
				{
				setState(21);
				_la = _input.LA(1);
				if ( !(_la==PLUS || _la==MINUS) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(22);
				multiplyingExpression();
				}
				}
				setState(27);
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
			setState(28);
			powExpression();
			setState(33);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TIMES || _la==DIV) {
				{
				{
				setState(29);
				_la = _input.LA(1);
				if ( !(_la==TIMES || _la==DIV) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(30);
				powExpression();
				}
				}
				setState(35);
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
			setState(45);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(36);
				atom(0);
				setState(41);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==POW) {
					{
					{
					setState(37);
					match(POW);
					setState(38);
					atom(0);
					}
					}
					setState(43);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(44);
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
			setState(47);
			atom(0);
			setState(58);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DE) {
				{
				{
				setState(48);
				match(DE);
				setState(54);
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
				case DUISHU:
				case GENHAO:
				case KUOHAO:
				case LPAREN:
				case MINUS:
				case PAI:
				case DIGIT:
					{
					{
					setState(49);
					atom(0);
					setState(50);
					match(CIFANG);
					}
					}
					break;
				case PINGFANG:
					{
					setState(52);
					match(PINGFANG);
					}
					break;
				case LIFANG:
					{
					setState(53);
					match(LIFANG);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				}
				setState(60);
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
		public TerminalNode YOU() { return getToken(calculatorParser.YOU, 0); }
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
		public FunctionContext function() {
			return getRuleContext(FunctionContext.class,0);
		}
		public List<AtomContext> atom() {
			return getRuleContexts(AtomContext.class);
		}
		public AtomContext atom(int i) {
			return getRuleContext(AtomContext.class,i);
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
		return atom(0);
	}

	private AtomContext atom(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		AtomContext _localctx = new AtomContext(_ctx, _parentState);
		AtomContext _prevctx = _localctx;
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_atom, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(62);
				number();
				}
				break;
			case 2:
				{
				setState(63);
				number();
				setState(64);
				match(YOU);
				setState(65);
				number();
				setState(66);
				match(FRAC);
				setState(67);
				number();
				}
				break;
			case 3:
				{
				setState(69);
				match(LPAREN);
				setState(70);
				expression();
				setState(71);
				match(RPAREN);
				}
				break;
			case 4:
				{
				setState(73);
				match(KUOHAO);
				setState(74);
				expression();
				setState(75);
				match(KUOHAO);
				}
				break;
			case 5:
				{
				setState(77);
				function();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(85);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new AtomContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_atom);
					setState(80);
					if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
					setState(81);
					match(FRAC);
					setState(82);
					atom(6);
					}
					} 
				}
				setState(87);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class FunctionContext extends ParserRuleContext {
		public FuncnameContext funcname() {
			return getRuleContext(FuncnameContext.class,0);
		}
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public PostFuncnameContext postFuncname() {
			return getRuleContext(PostFuncnameContext.class,0);
		}
		public TerminalNode DE() { return getToken(calculatorParser.DE, 0); }
		public FuncnameExContext funcnameEx() {
			return getRuleContext(FuncnameExContext.class,0);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof calculatorVisitor ) return ((calculatorVisitor<? extends T>)visitor).visitFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_function);
		int _la;
		try {
			setState(101);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(88);
				funcname();
				setState(89);
				atom(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(91);
				number();
				setState(93);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==DE) {
					{
					setState(92);
					match(DE);
					}
				}

				setState(95);
				postFuncname();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(97);
				number();
				setState(98);
				funcnameEx();
				setState(99);
				atom(0);
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
		public TerminalNode DUISHU() { return getToken(calculatorParser.DUISHU, 0); }
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
			setState(103);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COS) | (1L << SIN) | (1L << TAN) | (1L << ACOS) | (1L << ASIN) | (1L << ATAN) | (1L << LN) | (1L << LOG) | (1L << LG) | (1L << DUISHU) | (1L << GENHAO))) != 0)) ) {
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

	public static class FuncnameExContext extends ParserRuleContext {
		public TerminalNode DUISHU() { return getToken(calculatorParser.DUISHU, 0); }
		public TerminalNode GENHAO() { return getToken(calculatorParser.GENHAO, 0); }
		public FuncnameExContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcnameEx; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof calculatorVisitor ) return ((calculatorVisitor<? extends T>)visitor).visitFuncnameEx(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncnameExContext funcnameEx() throws RecognitionException {
		FuncnameExContext _localctx = new FuncnameExContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_funcnameEx);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			_la = _input.LA(1);
			if ( !(_la==DUISHU || _la==GENHAO) ) {
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

	public static class PostFuncnameContext extends ParserRuleContext {
		public TerminalNode KAIFANG() { return getToken(calculatorParser.KAIFANG, 0); }
		public TerminalNode KAIPINGFANG() { return getToken(calculatorParser.KAIPINGFANG, 0); }
		public TerminalNode KAILIFANG() { return getToken(calculatorParser.KAILIFANG, 0); }
		public TerminalNode PINGFANG() { return getToken(calculatorParser.PINGFANG, 0); }
		public TerminalNode GEN() { return getToken(calculatorParser.GEN, 0); }
		public TerminalNode LIFANG() { return getToken(calculatorParser.LIFANG, 0); }
		public PostFuncnameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postFuncname; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof calculatorVisitor ) return ((calculatorVisitor<? extends T>)visitor).visitPostFuncname(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PostFuncnameContext postFuncname() throws RecognitionException {
		PostFuncnameContext _localctx = new PostFuncnameContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_postFuncname);
		try {
			setState(114);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case KAIFANG:
				enterOuterAlt(_localctx, 1);
				{
				setState(107);
				match(KAIFANG);
				}
				break;
			case KAIPINGFANG:
				enterOuterAlt(_localctx, 2);
				{
				setState(108);
				match(KAIPINGFANG);
				}
				break;
			case KAILIFANG:
				enterOuterAlt(_localctx, 3);
				{
				setState(109);
				match(KAILIFANG);
				}
				break;
			case PINGFANG:
				enterOuterAlt(_localctx, 4);
				{
				setState(110);
				match(PINGFANG);
				setState(111);
				match(GEN);
				}
				break;
			case LIFANG:
				enterOuterAlt(_localctx, 5);
				{
				setState(112);
				match(LIFANG);
				setState(113);
				match(GEN);
				}
				break;
			default:
				throw new NoViableAltException(this);
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
		public TerminalNode PAI() { return getToken(calculatorParser.PAI, 0); }
		public TerminalNode MINUS() { return getToken(calculatorParser.MINUS, 0); }
		public List<TerminalNode> DIGIT() { return getTokens(calculatorParser.DIGIT); }
		public TerminalNode DIGIT(int i) {
			return getToken(calculatorParser.DIGIT, i);
		}
		public TerminalNode POINT() { return getToken(calculatorParser.POINT, 0); }
		public TerminalNode DU() { return getToken(calculatorParser.DU, 0); }
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
		enterRule(_localctx, 18, RULE_number);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==MINUS) {
				{
				setState(116);
				match(MINUS);
				}
			}

			setState(136);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DIGIT:
				{
				{
				setState(120); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(119);
						match(DIGIT);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(122); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
				} while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER );
				setState(130);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
				case 1:
					{
					setState(124);
					match(POINT);
					setState(126); 
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
						case 1:
							{
							{
							setState(125);
							match(DIGIT);
							}
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(128); 
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
					} while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER );
					}
					break;
				}
				setState(133);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
				case 1:
					{
					setState(132);
					_la = _input.LA(1);
					if ( !(_la==DU || _la==PAI) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					break;
				}
				}
				}
				break;
			case PAI:
				{
				setState(135);
				match(PAI);
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 4:
			return atom_sempred((AtomContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean atom_sempred(AtomContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 5);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3&\u008d\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\3\2\3\2\3\2\7\2\32\n\2\f\2\16\2\35\13\2\3\3\3\3\3\3\7\3\"\n\3\f\3"+
		"\16\3%\13\3\3\4\3\4\3\4\7\4*\n\4\f\4\16\4-\13\4\3\4\5\4\60\n\4\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\5\59\n\5\7\5;\n\5\f\5\16\5>\13\5\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6Q\n\6\3\6\3\6"+
		"\3\6\7\6V\n\6\f\6\16\6Y\13\6\3\7\3\7\3\7\3\7\3\7\5\7`\n\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\5\7h\n\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n"+
		"u\n\n\3\13\5\13x\n\13\3\13\6\13{\n\13\r\13\16\13|\3\13\3\13\6\13\u0081"+
		"\n\13\r\13\16\13\u0082\5\13\u0085\n\13\3\13\5\13\u0088\n\13\3\13\5\13"+
		"\u008b\n\13\3\13\2\3\n\f\2\4\6\b\n\f\16\20\22\24\2\7\3\2\21\22\3\2\23"+
		"\24\3\2\3\r\3\2\f\r\3\2\37 \u009b\2\26\3\2\2\2\4\36\3\2\2\2\6/\3\2\2\2"+
		"\b\61\3\2\2\2\nP\3\2\2\2\fg\3\2\2\2\16i\3\2\2\2\20k\3\2\2\2\22t\3\2\2"+
		"\2\24w\3\2\2\2\26\33\5\4\3\2\27\30\t\2\2\2\30\32\5\4\3\2\31\27\3\2\2\2"+
		"\32\35\3\2\2\2\33\31\3\2\2\2\33\34\3\2\2\2\34\3\3\2\2\2\35\33\3\2\2\2"+
		"\36#\5\6\4\2\37 \t\3\2\2 \"\5\6\4\2!\37\3\2\2\2\"%\3\2\2\2#!\3\2\2\2#"+
		"$\3\2\2\2$\5\3\2\2\2%#\3\2\2\2&+\5\n\6\2\'(\7#\2\2(*\5\n\6\2)\'\3\2\2"+
		"\2*-\3\2\2\2+)\3\2\2\2+,\3\2\2\2,\60\3\2\2\2-+\3\2\2\2.\60\5\b\5\2/&\3"+
		"\2\2\2/.\3\2\2\2\60\7\3\2\2\2\61<\5\n\6\2\628\7\35\2\2\63\64\5\n\6\2\64"+
		"\65\7\31\2\2\659\3\2\2\2\669\7\27\2\2\679\7\30\2\28\63\3\2\2\28\66\3\2"+
		"\2\28\67\3\2\2\29;\3\2\2\2:\62\3\2\2\2;>\3\2\2\2<:\3\2\2\2<=\3\2\2\2="+
		"\t\3\2\2\2><\3\2\2\2?@\b\6\1\2@Q\5\24\13\2AB\5\24\13\2BC\7\26\2\2CD\5"+
		"\24\13\2DE\7\25\2\2EF\5\24\13\2FQ\3\2\2\2GH\7\17\2\2HI\5\2\2\2IJ\7\20"+
		"\2\2JQ\3\2\2\2KL\7\16\2\2LM\5\2\2\2MN\7\16\2\2NQ\3\2\2\2OQ\5\f\7\2P?\3"+
		"\2\2\2PA\3\2\2\2PG\3\2\2\2PK\3\2\2\2PO\3\2\2\2QW\3\2\2\2RS\f\7\2\2ST\7"+
		"\25\2\2TV\5\n\6\bUR\3\2\2\2VY\3\2\2\2WU\3\2\2\2WX\3\2\2\2X\13\3\2\2\2"+
		"YW\3\2\2\2Z[\5\16\b\2[\\\5\n\6\2\\h\3\2\2\2]_\5\24\13\2^`\7\35\2\2_^\3"+
		"\2\2\2_`\3\2\2\2`a\3\2\2\2ab\5\22\n\2bh\3\2\2\2cd\5\24\13\2de\5\20\t\2"+
		"ef\5\n\6\2fh\3\2\2\2gZ\3\2\2\2g]\3\2\2\2gc\3\2\2\2h\r\3\2\2\2ij\t\4\2"+
		"\2j\17\3\2\2\2kl\t\5\2\2l\21\3\2\2\2mu\7\32\2\2nu\7\33\2\2ou\7\34\2\2"+
		"pq\7\27\2\2qu\7\36\2\2rs\7\30\2\2su\7\36\2\2tm\3\2\2\2tn\3\2\2\2to\3\2"+
		"\2\2tp\3\2\2\2tr\3\2\2\2u\23\3\2\2\2vx\7\22\2\2wv\3\2\2\2wx\3\2\2\2x\u008a"+
		"\3\2\2\2y{\7%\2\2zy\3\2\2\2{|\3\2\2\2|z\3\2\2\2|}\3\2\2\2}\u0084\3\2\2"+
		"\2~\u0080\7!\2\2\177\u0081\7%\2\2\u0080\177\3\2\2\2\u0081\u0082\3\2\2"+
		"\2\u0082\u0080\3\2\2\2\u0082\u0083\3\2\2\2\u0083\u0085\3\2\2\2\u0084~"+
		"\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0087\3\2\2\2\u0086\u0088\t\6\2\2\u0087"+
		"\u0086\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u008b\3\2\2\2\u0089\u008b\7 "+
		"\2\2\u008az\3\2\2\2\u008a\u0089\3\2\2\2\u008b\25\3\2\2\2\23\33#+/8<PW"+
		"_gtw|\u0082\u0084\u0087\u008a";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}