package org.xing.calc.filter;

import java.util.HashMap;
import java.util.Map;

public class CorrectionExprFilter implements ExprFilter {
	private Map<Character, String> correctionCharMap;
	private Map<String, String> correctionStringMap;

	public CorrectionExprFilter() {
		correctionCharMap = new HashMap<Character, String>();

		correctionCharMap.put('典', "点");
		correctionCharMap.put('店', "点");
		correctionCharMap.put('电', "点");
		correctionCharMap.put('垫', "点");
		correctionCharMap.put('颠', "点");
		correctionCharMap.put('掂', "点");

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

		correctionCharMap.put('家', "加");
		correctionCharMap.put('佳', "加");
		correctionCharMap.put('嘉', "加");
		correctionCharMap.put('甲', "加");
		correctionCharMap.put('假', "加");
		correctionCharMap.put('僵', "加");

		correctionCharMap.put('捡', "减");
		correctionCharMap.put('剑', "减");
		correctionCharMap.put('兼', "减");
		correctionCharMap.put('建', "减");
		correctionCharMap.put('简', "减");
		correctionCharMap.put('剪', "减");
		correctionCharMap.put('检', "减");
		correctionCharMap.put('奸', "减");
		correctionCharMap.put('歼', "减");

		correctionCharMap.put('成', "乘");
		correctionCharMap.put('称', "乘");
		correctionCharMap.put('城', "乘");
		correctionCharMap.put('程', "乘");
		correctionCharMap.put('诚', "乘");
		correctionCharMap.put('呈', "乘");
		correctionCharMap.put('承', "乘");
		correctionCharMap.put('澄', "乘");
		correctionCharMap.put('橙', "乘");
		correctionCharMap.put('逞', "乘");
		correctionCharMap.put('丞', "乘");
		correctionCharMap.put('惩', "乘");
		correctionCharMap.put('陈', "乘");
		correctionCharMap.put('晨', "乘");
		correctionCharMap.put('沉', "乘");
		correctionCharMap.put('尘', "乘");
		correctionCharMap.put('臣', "乘");
		correctionCharMap.put('盛', "乘");

		correctionCharMap.put('出', "除");
		correctionCharMap.put('初', "除");
		correctionCharMap.put('处', "除");
		correctionCharMap.put('楚', "除");
		correctionCharMap.put('厨', "除");
		correctionCharMap.put('褚', "除");

		correctionCharMap.put('灵', "0");
		correctionCharMap.put('壹', "1");
		correctionCharMap.put('乙', "1");
		correctionCharMap.put('毅', "1");
		correctionCharMap.put('衣', "1");
		correctionCharMap.put('耳', "2");
		correctionCharMap.put('而', "2");
		correctionCharMap.put('贰', "2");
		correctionCharMap.put('两', "二");
		correctionCharMap.put('俩', "二");
		correctionCharMap.put('山', "3");
		correctionCharMap.put('叁', "3");
		correctionCharMap.put('善', "3");
		correctionCharMap.put('仨', "三");
		correctionCharMap.put('是', "4");
		correctionCharMap.put('肆', "4");
		correctionCharMap.put('事', "4");
		correctionCharMap.put('士', "4");
		correctionCharMap.put('思', "4");
		correctionCharMap.put('氏', "4");
		correctionCharMap.put('市', "4");
		correctionCharMap.put('世', "4");
		correctionCharMap.put('师', "4");
		correctionCharMap.put('务', "5");
		correctionCharMap.put('伍', "5");
		correctionCharMap.put('午', "5");
		correctionCharMap.put('武', "5");
		correctionCharMap.put('舞', "5");
		correctionCharMap.put('污', "5");
		correctionCharMap.put('溜', "6");
		correctionCharMap.put('陆', "6");
		correctionCharMap.put('料', "6");
		correctionCharMap.put('乐', "6");
		correctionCharMap.put('期', "7");
		correctionCharMap.put('柒', "7");
		correctionCharMap.put('妻', "7");
		correctionCharMap.put('亲', "7");
		correctionCharMap.put('夕', "7");
		correctionCharMap.put('捌', "8");
		correctionCharMap.put('班', "8");
		correctionCharMap.put('吧', "8");
		correctionCharMap.put('发', "8");
		correctionCharMap.put('疤', "8");
		correctionCharMap.put('近', "9");
		correctionCharMap.put('玖', "9");
		correctionCharMap.put('就', "9");
		correctionCharMap.put('酒', "9");
		correctionCharMap.put('教', "9");
		correctionCharMap.put('拾', "十");
		correctionCharMap.put('实', "十");
		correctionCharMap.put('尸', "十");
		correctionCharMap.put('时', "十");

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
