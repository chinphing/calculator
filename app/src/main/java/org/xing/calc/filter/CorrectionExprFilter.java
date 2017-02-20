package org.xing.calc.filter;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CorrectionExprFilter implements ExprFilter {
	private StringBuilder regStr;
	private Map<String, String> correctionStringMap;

	public CorrectionExprFilter() {
		regStr = new StringBuilder();
		correctionStringMap = new HashMap<>();

		addWord("佛", "负");
		addWord("塑", "负");

		addWord("僵", "加");

		addWord("澄", "乘");
		addWord("橙", "乘");
		addWord("盛", "乘");
		addWord("重", "乘");

		addWord("区", "除");
		addWord("褚", "除");

		addWord("两", "二");
		addWord("俩", "二");
		addWord("山", "3");
		addWord("善", "3");
		addWord("仨", "三");
		addWord("是", "4");
		addWord("事", "4");
		addWord("士", "4");
		addWord("式", "4");
		addWord("氏", "4");
		addWord("市", "4");
		addWord("世", "4");
		addWord("师", "4");
		addWord("陆", "6");
		addWord("料", "6");
		addWord("乐", "6");
		addWord("榴", "6");
		addWord("亲", "7");
		addWord("夕", "7");
		addWord("班", "8");
		addWord("发", "8");
		addWord("近", "9");
		addWord("教", "9");

		addWord("鱼线", "余弦");
		addWord("预先", "余弦");
		addWord("遇险", "余弦");
		addWord("鱼鲜", "余弦");
		addWord("玉仙", "余弦");
		addWord("欲仙", "余弦");
		addWord("预选", "余弦");
		addWord("雨轩", "余弦");
		addWord("宇轩", "余弦");
		addWord("预选", "余弦");
		addWord("玉轩", "余弦");

		addWord("范玉献", "反余弦");
		addWord("范瑜轩", "反余弦");
		addWord("赣榆县", "反余弦");

		addWord("争先", "正弦");
		addWord("正线", "正弦");
		addWord("争鲜", "正弦");
		addWord("正选", "正弦");
		addWord("郑玄", "正弦");
		addWord("郑璇", "正弦");

		addWord("反正选", "反正弦");
		addWord("反正旋", "反正弦");

		addWord("正确", "正切");
		addWord("争取", "正切");
		addWord("证券", "正切");
		addWord("挣钱", "正切");
		addWord("正气", "正切");
		addWord("争气", "正切");
		addWord("政权", "正切");
		addWord("征求", "正切");

		addWord("反正钱", "反正切");
		addWord("反正去", "反正切");

		addWord("括回", "括号");
		addWord("对手", "对数");
		addWord("等于", "");
		addWord("多少", "");
		addWord("的差", "");
		addWord("的和", "");
		addWord("的积", "");
		addWord("的商", "");

		addWord("%", "/100");
		addWord("％", "/100");
		addWord("^2", "的平方");
		addWord("佳茵", "加一");
		addWord("捡漏", "减六");
		addWord("捡了", "减六");
		addWord("图六", "除六");
		addWord("除了", "除六");
		addWord("成龙", "乘六");
		addWord("成了", "乘六");
		addWord("天气", "减七");
		addWord("成绩", "乘七");
		addWord("长期", "乘七");
		addWord("城西", "乘七");
		addWord("晨曦", "乘七");
		addWord("城邦", "乘八");
		addWord("乘邦", "乘八");
		addWord("主角", "除九");
		addWord("图九", "除九");

		addWord("清平", "清屏");
		addWord("青平", "清屏");
		addWord("青萍", "清屏");
		addWord("青苹", "清屏");
		addWord("蜻蜓", "清屏");
		addWord("金瓶", "清屏");

		addWord("浙江", "撤销");
		addWord("大腿", "倒退");
		addWord("车享", "倒退");
	}
	private void addWord(String tar, String with) {
		if(regStr.length() > 0) {
			regStr.append("|");
		}
		regStr.append("(");
		String temp = tar;
		temp = temp.replace("^", "\\^");
		temp = temp.replace("%", "\\%");
		regStr.append(temp);
		regStr.append(")");

		correctionStringMap.put(tar, with);
	}

	@Override
	public String call(String expr) {
		int start = 0;
		StringBuilder str = new StringBuilder();

		Pattern correctionPattern = Pattern.compile(regStr.toString());
		Matcher mch = correctionPattern.matcher(expr);
		while(mch.find()) {
			if(mch.start() > start) {
				str.append(expr.substring(start, mch.start()));
			}
			str.append(correctionStringMap.get(mch.group()));
			start = mch.end();
		}
		if(start < expr.length()) {
			str.append(expr.substring(start));
		}

		/*
		纠正常见的结尾错误
		 */
		String result = str.toString();
		if(result.endsWith("加") || result.endsWith("减")
				|| result.endsWith("乘")
				|| result.endsWith("+") || result.endsWith("-")
				|| result.endsWith("×") || result.endsWith("/") ) {
			result = result.substring(0, result.length()-1);
		} else if(result.endsWith("加上") || result.endsWith("减去")
				|| result.endsWith("乘以") || result.endsWith("除以")) {
			result = result.substring(0, result.length()-2);
		}

		return result;
	}

}
