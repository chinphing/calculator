package org.xing.parser;

public abstract class Parser {

	//用于计算的表达式
	protected String evalExpr;
	
	//易于阅读的表达式
	protected String readExpr;
	
	//用于语音朗读的表达式
	protected String speechExpr;
	
	public String getEvalExpr() {
		return evalExpr;
	}

	public void setEvalExpr(String evalExpr) {
		this.evalExpr = evalExpr;
	}

	public String getReadExpr() {
		return readExpr;
	}

	public void setReadExpr(String readExpr) {
		this.readExpr = readExpr;
	}

	public String getSpeechExpr() {
		return speechExpr;
	}

	public void setSpeechExpr(String speechExpr) {
		this.speechExpr = speechExpr;
	}

	public void parse(String expr)  {

	}
}
