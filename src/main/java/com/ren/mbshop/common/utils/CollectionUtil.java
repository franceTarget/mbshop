package com.ren.mbshop.common.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CollectionUtil {
	public static boolean isNotEmpty(Collection<?> c) {
		if (c != null && c.size() != 0) {
			return true;
		}
		return false;
	}

	public static boolean isNotEmpty(List<?> list) {
		return list != null && list.size() > 0;
	}

	public static boolean isNotEmpty(Map<?, ?> map) {
		return map != null && map.size() > 0;
	}

	public static boolean isNotEmpty(Object[] objects) {
		return objects != null && objects.length > 0;
	}

	public static boolean isNullOrEmpty(List<?> list) {
		return list == null || list.size() == 0;
	}

	public static boolean isNullOrEmpty(Map<?, ?> map) {
		return map == null || map.size() == 0;
	}

	public static boolean isNullOrEmpty(Object[] objects) {
		return objects == null || objects.length == 0;
	}

	@SuppressWarnings("unchecked")
	public static <T> T[] concat(T[] first, T[]... rest) {
		int totalLength = first.length;
		for (T[] array : rest) {
			totalLength += array.length;
		}
		T[] result = Arrays.copyOf(first, totalLength);
		int offset = first.length;
		for (T[] array : rest) {
			System.arraycopy(array, 0, result, offset, array.length);
			offset += array.length;
		}
		return result;
	}
}
