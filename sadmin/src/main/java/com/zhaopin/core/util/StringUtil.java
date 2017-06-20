package com.zhaopin.core.util;

/**
 * Created by Administrator on 2017/6/17.
 */
public class StringUtil {
    public static boolean idNullOrEmpty(String string) {
        return null == string || "".equals(string.trim());
    }
}
