package org.xing.filter;

import java.util.HashMap;
import java.util.Map;

public class CorrectionExprFilter implements ExprFilter {
	private Map<Character, String> correctionCharMap;
	private Map<String, String> correctionStringMap;

	public CorrectionExprFilter() {
		correctionCharMap = new HashMap<Character, String>();
		correctionCharMap.put('付', "负");
		correctionCharMap.put('傅', "负");
		correctionCharMap.put('附', "负");
		correctionCharMap.put('佛', "负");
		correctionCharMap.put('富', "负");
		correctionCharMap.put('服', "负");
		correctionCharMap.put('复', "负");
		correctionCharMap.put('塑', "负");
		correctionCharMap.put('夫', "负");
		correctionCharMap.put('父', "负");
		correctionCharMap.put('副', "负");

		correctionCharMap.put('耳', "2");
		correctionCharMap.put('山', "3");
		correctionCharMap.put('是', "4");
		correctionCharMap.put('士', "4");
		correctionCharMap.put('务', "5");
		correctionCharMap.put('溜', "6");
		correctionCharMap.put('料', "6");
		correctionCharMap.put('期', "7");
		correctionCharMap.put('妻', "7");
		correctionCharMap.put('亲', "7");
		correctionCharMap.put('近', "9");

		correctionStringMap = new HashMap<String, String>();
		correctionStringMap.put("^2", "的平方");
	}

	@Override
	public String call(String expr) {
		StringBuilder str = new StringBuilder();
		for(int i=0;i<expr.length();i++) {
			char c = expr.charAt(i);
			if(correctionCharMap.containsKey(c)) {
				str.append(correctionCharMap.get(c));
			} else {
				str.append(c);
			}
		}

		String result = str.toString();
		for(String s: correctionStringMap.keySet()) {
			result = result.replace(s, correctionStringMap.get(s));
		}

		return result;
	}

}
