package org.xing.calc.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RedundantExprFilter implements ExprFilter{
	private Set<Character> allowedChars;
	private Map<String, Character> allowedCharsPinyin;

	private Map<Character, String> pinyin;

	public RedundantExprFilter(String legalChars, InputStream tokenStream) {
		try {
			pinyin = loadTokens(tokenStream);
		} catch (Exception e) {
			e.printStackTrace();
		}

		allowedChars = new HashSet<Character>();
		allowedCharsPinyin = new HashMap<String, Character>();
		for(int i=0;i<legalChars.length();i++) {
			Character c = legalChars.charAt(i);
			allowedChars.add(c);

			if(pinyin!= null && pinyin.containsKey(c)) {
				if(allowedCharsPinyin.containsKey(pinyin.get(c))) {
					System.err.println("严重错误，有拼音相同的字："+c);
				}else {
					allowedCharsPinyin.put(pinyin.get(c), c);
				}
			}
		}
	}

	private Map<Character, String> loadTokens(InputStream input) throws IOException {
		Map<Character, String> pinyin = new HashMap<Character, String>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(input, "utf-8"));
		String line = null;
		while((line = reader.readLine()) != null) {
			if(line.length() > 0) {
				String[] fields = line.split("\\s+");
				String charPinyin = fields[1];
				String chars = fields[2];
				for(int i=0;i<chars.length();i++) {
					pinyin.put(chars.charAt(i), charPinyin);
				}
			}
		}
		return pinyin;
	}

	@Override
	public String call(String expr) {
		StringBuilder str = new StringBuilder();
		for(int i=0;i<expr.length();i++) {
			Character c = expr.charAt(i);
			if(allowedChars.contains(c)) {
				str.append(c);
			}else if(pinyin!= null && pinyin.containsKey(c)){
				String py = pinyin.get(c);
				if(allowedCharsPinyin.containsKey(py)) {
					str.append(allowedCharsPinyin.get(py));
				}else {
					//很多人前后鼻音不分
					py = py+"g";
					if(allowedCharsPinyin.containsKey(py)) {
						str.append(allowedCharsPinyin.get(py));
					}
				}
			}
		}
		return str.toString();
	}
}