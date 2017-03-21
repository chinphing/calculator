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
		
		//通过拼音来加入纠正一批由于用户读音不准确导致的识别错误
		allowedCharsPinyin.put("qu", '除');
		allowedCharsPinyin.put("xuan", '弦');
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


		/*
		纠正常见的结尾错误
		 */
		String result = str.toString();
		if(result.endsWith("加") || result.endsWith("减")
				|| result.endsWith("乘") || result.endsWith("点")
				|| result.endsWith("+") || result.endsWith("-")
				|| result.endsWith("×") || result.endsWith("/") ) {
			result = result.substring(0, result.length()-1);
		} else if(result.endsWith("加上") || result.endsWith("减去")
				|| result.endsWith("乘以") || result.endsWith("除以")) {
			result = result.substring(0, result.length()-2);
		} else if(result.startsWith("-")){
			/*讯飞语音直接将‘负’改成了‘-’号，导致错误的识别为连续计算*/
			result = "负" + result.substring(1, result.length());
		}

		return result;
	}
}