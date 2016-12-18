package org.xing.filter;

import java.util.HashSet;
import java.util.Set;

public class RedundantExprFilter implements ExprFilter{
	private Set<Character> allowedChars;
	
	public RedundantExprFilter(String legalChars) {
		allowedChars = new HashSet<Character>();
		for(int i=0;i<legalChars.length();i++) {
			allowedChars.add(legalChars.charAt(i));
		}
	}
	
	@Override
	public String call(String expr) {
		StringBuilder str = new StringBuilder();
		for(int i=0;i<expr.length();i++) {
			if(allowedChars.contains(expr.charAt(i))) {
				str.append(expr.charAt(i));
			}
		}
		return str.toString();
	}
	
}