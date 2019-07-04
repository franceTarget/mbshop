package com.ren.mbshop.common.utils;

import java.util.List;

public class StringUtil {
	public static boolean isEmpty(String s) {
		return s == null || "".equals(s.trim());
	}

	public static boolean isNotEmpty(String s) {
		return null != s && !"".equals(s.trim());
	}

	public static String ListToString(List<String> list) {
		if (list == null) {
			return null;
		}
		StringBuilder result = new StringBuilder();
		boolean first = true;
		// 第一个前面不拼接","
		for (String string : list) {
			if (first) {
				first = false;
			} else {
				result.append(",");
			}
			result.append(string);
		}
		return result.toString();
	}
}
