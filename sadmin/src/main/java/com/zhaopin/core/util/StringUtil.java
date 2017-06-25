package com.zhaopin.core.util;

/**
 * Created by Administrator on 2017/6/17.
 */
public class StringUtil {
    public static boolean isNullOrEmpty(String string) {
        return null == string || "".equals(string.trim());
    }

    public static String frontCompWithZore(int sourceDate, int formatLength) {
        return String.format("%0" + formatLength + "d", sourceDate);
    }
}
