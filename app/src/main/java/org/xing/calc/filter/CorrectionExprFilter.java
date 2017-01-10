package org.xing.calc.filter;

import java.util.HashMap;
import java.util.Map;

public class CorrectionExprFilter implements ExprFilter {
	private Map<Character, String> correctionCharMap;
	private Map<String, String> correctionStringMap;

	public CorrectionExprFilter() {
		correctionCharMap = new HashMap<Character, String>();

		correctionCharMap.put('佛', "负");
		correctionCharMap.put('塑', "负");

		correctionCharMap.put('僵', "加");

		correctionCharMap.put('澄', "乘");
		correctionCharMap.put('橙', "乘");
		correctionCharMap.put('盛', "乘");

		correctionCharMap.put('褚', "除");

		correctionCharMap.put('两', "二");
		correctionCharMap.put('俩', "二");
		correctionCharMap.put('山', "3");
		correctionCharMap.put('善', "3");
		correctionCharMap.put('仨', "三");
		correctionCharMap.put('是', "4");
		correctionCharMap.put('事', "4");
		correctionCharMap.put('士', "4");
		correctionCharMap.put('式', "4");
		correctionCharMap.put('氏', "4");
		correctionCharMap.put('市', "4");
		correctionCharMap.put('世', "4");
		correctionCharMap.put('师', "4");
		correctionCharMap.put('陆', "6");
		correctionCharMap.put('料', "6");
		correctionCharMap.put('乐', "6");
		correctionCharMap.put('榴', "6");
		correctionCharMap.put('亲', "7");
		correctionCharMap.put('夕', "7");
		correctionCharMap.put('班', "8");
		correctionCharMap.put('发', "8");
		correctionCharMap.put('近', "9");
		correctionCharMap.put('教', "9");

		correctionStringMap = new HashMap<String, String>();
		correctionStringMap.put("鱼线", "余弦");
		correctionStringMap.put("预先", "余弦");
		correctionStringMap.put("遇险", "余弦");
		correctionStringMap.put("鱼鲜", "余弦");
		correctionStringMap.put("玉仙", "余弦");
		correctionStringMap.put("欲仙", "余弦");
		correctionStringMap.put("预选", "余弦");
		correctionStringMap.put("雨轩", "余弦");
		correctionStringMap.put("宇轩", "余弦");
		correctionStringMap.put("预选", "余弦");
		correctionStringMap.put("玉轩", "余弦");

		correctionStringMap.put("范玉献", "反余弦");
		correctionStringMap.put("范瑜轩", "反余弦");
		correctionStringMap.put("赣榆县", "反余弦");

		correctionStringMap.put("争先", "正弦");
		correctionStringMap.put("正线", "正弦");
		correctionStringMap.put("争鲜", "正弦");
		correctionStringMap.put("正选", "正弦");
		correctionStringMap.put("郑玄", "正弦");
		correctionStringMap.put("郑璇", "正弦");

		correctionStringMap.put("反正选", "反正弦");
		correctionStringMap.put("反正旋", "反正弦");

		correctionStringMap.put("正确", "正切");
		correctionStringMap.put("争取", "正切");
		correctionStringMap.put("证券", "正切");
		correctionStringMap.put("挣钱", "正切");
		correctionStringMap.put("正气", "正切");
		correctionStringMap.put("争气", "正切");
		correctionStringMap.put("政权", "正切");
		correctionStringMap.put("征求", "正切");

		correctionStringMap.put("反正钱", "反正切");
		correctionStringMap.put("反正去", "反正切");

		correctionStringMap.put("对手", "对数");

		correctionStringMap.put("%", "/100");
		correctionStringMap.put("％", "/100");
		correctionStringMap.put("^2", "的平方");
		correctionStringMap.put("佳茵", "加一");
		correctionStringMap.put("捡漏", "减六");
		correctionStringMap.put("捡了", "减六");
		correctionStringMap.put("图六", "除六");
		correctionStringMap.put("除了", "除六");
		correctionStringMap.put("成龙", "乘六");
		correctionStringMap.put("成了", "乘六");
		correctionStringMap.put("天气", "减七");
		correctionStringMap.put("成绩", "乘七");
		correctionStringMap.put("长期", "乘七");
		correctionStringMap.put("城西", "乘七");
		correctionStringMap.put("晨曦", "乘七");
		correctionStringMap.put("城邦", "乘八");
		correctionStringMap.put("乘邦", "乘八");
		correctionStringMap.put("主角", "除九");
		correctionStringMap.put("图九", "除九");
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
