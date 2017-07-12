package com.zhaopin.core.util;

import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

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

    private static final Pattern NUMBER_PATTERN = Pattern
            .compile("^[0-9]+(\\.[.0-9]+)?[BSILFDbsilfd]?$");

    public static boolean isNumber(String value) {
        return NUMBER_PATTERN.matcher(value).matches();
    }

    private static final Pattern NAMED_PATTERN = Pattern
            .compile("^[_A-Za-z][_0-9A-Za-z]*$");

    public static boolean isNamed(String value) {
        return NAMED_PATTERN.matcher(value).matches();
    }

    private static final Pattern TYPED_PATTERN = Pattern
            .compile("^[_A-Za-z][_.0-9A-Za-z]*$");

    public static boolean isTyped(String value) {
        return TYPED_PATTERN.matcher(value).matches();
    }

    private static final Pattern FUNCTION_PATTERN = Pattern
            .compile("^\\.[_A-Za-z][_0-9A-Za-z]*$");

    public static boolean isFunction(String value) {
        return FUNCTION_PATTERN.matcher(value).matches();
    }

    public static String toString(Object value) {
        if (value == null)
            return null;
        if (value.getClass().isArray()) {
            if (value instanceof boolean[]) {
                return Arrays.toString((boolean[]) value);
            } else if (value instanceof char[]) {
                return Arrays.toString((char[]) value);
            } else if (value instanceof byte[]) {
                return Arrays.toString((byte[]) value);
            } else if (value instanceof short[]) {
                return Arrays.toString((short[]) value);
            } else if (value instanceof int[]) {
                return Arrays.toString((int[]) value);
            } else if (value instanceof long[]) {
                return Arrays.toString((long[]) value);
            } else if (value instanceof float[]) {
                return Arrays.toString((float[]) value);
            } else if (value instanceof double[]) {
                return Arrays.toString((double[]) value);
            } else if (value instanceof Object[]) {
                return Arrays.toString((Object[]) value);
            } else {
                int len = Array.getLength(value);
                StringBuilder buf = new StringBuilder("[");
                for (int i = 0; i < len; i++) {
                    if (i != 0) {
                        buf.append(", ");
                    }
                    buf.append(Array.get(value, i));
                }
                buf.append("]");
                return buf.toString();
            }
        }
        return String.valueOf(value);
    }

    public static String toByteString(byte[] bytes) {
        StringBuilder buf = new StringBuilder();
        for (byte b : bytes) {
            if (buf.length() > 0) {
                buf.append(", ");
            }
            buf.append((int) b);
        }
        return buf.toString();
    }

    public static String escapeSolrString(String value) {
        if (value != null) {
            StringBuilder buf = null;
            for (int i = 0; i < value.length(); i++) {
                char ch = value.charAt(i);
                String str = null;
                switch (ch) {
                    case '(':
                        str = "\\(";
                        break;
                    case ')':
                        str = "\\)";
                        break;
                    case '/':
                        str = "\\/";
                        break;
                    case '<':
                        str = "\\<";
                        break;
                    case '>':
                        str = "\\>";
                        break;
                    case '{':
                        str = "\\{";
                        break;
                    case '}':
                        str = "\\}";
                        break;
                    case '+':
                        str = "\\+";
                        break;
                    case '-':
                        str = "\\-";
                        break;
                    case '^':
                        str = "\\^";
                        break;
                    case '\\':
                        str = "\\\\";
                        break;
//                    case '*':
//                        //如果以*结尾，表示前缀查询，否则表示特殊字符
//                        if (i != value.length() - 1) {
//                            str = "\\*";
//                        }
//                        break;
                    case '~':
                        str = "\\~";
                        break;
                    case '&':
                        str = "\\&";
                        break;
                    case '!':
                        str = "\\!";
                        break;
                    case ':':
                        str = "\\:";
                        break;
                    case '|':
                        str = "\\|";
                        break;
                    case '?':
                        str = "\\?";
                        break;
                    case '[':
                        str = "\\[";
                        break;
                    case ']':
                        str = "\\]";
                        break;
                    case '\"':
                        str = " ";
                        break;
                    case '\'':
                        str = " ";
                        break;
                    default:
                        break;
                }
                if (str != null) {
                    if (buf == null) {
                        buf = new StringBuilder();
                        if (i > 0) {
                            buf.append(value.substring(0, i));
                        }
                    }
                    buf.append(str);
                } else {
                    if (buf != null) {
                        buf.append(ch);
                    }
                }
            }
            if (buf != null) {
                return buf.toString();
            }
        }
        return value;
    }

    public static String escapeString(String value) {
        if (value != null) {
            StringBuilder buf = null;
            for (int i = 0; i < value.length(); i++) {
                char ch = value.charAt(i);
                String str;
                switch (ch) {
                    case '\\':
                        str = "\\\\";
                        break;
                    case '\"':
                        str = "\\\"";
                        break;
                    case '\'':
                        str = "\\\'";
                        break;
                    case '\t':
                        str = "\\t";
                        break;
                    case '\n':
                        str = "\\n";
                        break;
                    case '\r':
                        str = "\\r";
                        break;
                    case '\b':
                        str = "\\b";
                        break;
                    case '\f':
                        str = "\\f";
                        break;
                    default:
                        str = null;
                        break;
                }
                if (str != null) {
                    if (buf == null) {
                        buf = new StringBuilder();
                        if (i > 0) {
                            buf.append(value.substring(0, i));
                        }
                    }
                    buf.append(str);
                } else {
                    if (buf != null) {
                        buf.append(ch);
                    }
                }
            }
            if (buf != null) {
                return buf.toString();
            }
        }
        return value;
    }

    public static String removeSpecialChar(String value) {
        StringBuffer result = new StringBuffer();
        //,.:;”’/*+-!@#$%^&(-={}[]<>?”:"
        String specialChars = ",，.。:：;；“‘”’\"\'/*+-!@#$%^&(-={}[]<>?？";
        for (int i = 0; i < value.length(); i++) {
            char ch = value.charAt(i);
            boolean containSpecialChar = false;
            for (int j = 0; j < specialChars.length(); j++) {
                char specialChar = specialChars.charAt(j);
                if (ch == specialChar) {
                    containSpecialChar = true;
                    break;
                }
            }
            if (!containSpecialChar) {
                result.append(ch);
            }
        }
        return result.toString();
    }

    public static String unescapeString(String text) { // TODO 性能
        return text.replace("\\\"", "\"").replace("\\t", "\t")
                .replace("\\n", "\n").replace("\\r", "\r").replace("\\b", "\b")
                .replace("\\f", "\f").replace("\\\\", "\\");
    }

    public static String escapeHtml(String value) {
        if (value != null) {
            StringBuilder buf = null;
            for (int i = 0; i < value.length(); i++) {
                char ch = value.charAt(i);
                String str;
                switch (ch) {
                    case '&':
                        str = "&amp;";
                        break;
                    case '<':
                        str = "&lt;";
                        break;
                    case '>':
                        str = "&gt;";
                        break;
                    case '\"':
                        str = "&quot;";
                        break;
                    case '\'':
                        str = "&apos;";
                        break;
                    default:
                        str = null;
                        break;
                }
                if (str != null) {
                    if (buf == null) {
                        buf = new StringBuilder();
                        if (i > 0) {
                            buf.append(value.substring(0, i));
                        }
                    }
                    buf.append(str);
                } else {
                    if (buf != null) {
                        buf.append(ch);
                    }
                }
            }
            if (buf != null) {
                return buf.toString();
            }
        }
        return value;
    }

    public static String unescapeHtml(String text) { // TODO 性能
        return text.replace("&lt;", "<").replace("&gt;", ">")
                .replace("&quot;", "\"").replace("&apos;", "\'")
                .replace("&amp;", "&");
    }

    public static String getLocationMessage(Reader reader, int offset) {
        String location = "";
        if (offset <= 0) {
            return location;
        }
        try {
            int line = 1;
            int column = 0;
            int count = 0;
            int len = 0;
            char[] buf = new char[128];
            StringBuilder cur = new StringBuilder();
            while ((len = reader.read(buf)) > 0) {
                for (int i = 0; i < len; i++) {
                    char ch = buf[i];
                    if (ch == '\n') {
                        line++;
                        column = 0;
                        cur.setLength(0);
                    } else {
                        column++;
                        cur.append(ch);
                    }
                    if (count >= offset) {
                        int padding = 20;
                        String before;
                        if (cur.length() <= padding) {
                            before = cur.toString();
                        } else {
                            before = cur.substring(cur.length() - padding);
                        }
                        int c = i + 1;
                        int remain = len - c;
                        StringBuilder after = new StringBuilder();
                        boolean breaked = false;
                        if (remain > 0) {
                            for (int j = c; j < padding + c && j < buf.length; j++) {
                                if (buf[j] == '\r' || buf[j] == '\n') {
                                    breaked = true;
                                    break;
                                }
                                after.append(buf[j]);
                            }
                        }
                        if (!breaked && remain < padding) {
                            char[] b = new char[padding - remain];
                            int l = reader.read(b);
                            if (l > 0) {
                                for (int j = 0; j < l; j++) {
                                    if (b[j] == '\r' || b[j] == '\n') {
                                        break;
                                    }
                                    after.append(b[j]);
                                }
                            }
                        }
                        StringBuilder msg = new StringBuilder();
                        msg.append("line: " + line + ", column: " + column
                                + ", char: " + ch + ", in: \n");
                        for (int j = 0; j < padding * 2; j++) {
                            msg.append("=");
                        }
                        msg.append("\n");
                        msg.append("...");
                        msg.append(before);
                        msg.append(after);
                        msg.append("...");
                        msg.append("\n");
                        for (int j = 0; j < before.length() + 2; j++) {
                            msg.append(" ");
                        }
                        msg.append("^-here\n");
                        for (int j = 0; j < padding * 2; j++) {
                            msg.append("=");
                        }
                        msg.append("\n");
                        return msg.toString();
                    }
                    count++;
                }
            }
        } catch (Throwable t) {
        }
        return location;
    }

    public static String splitCamelName(String name, String split) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                if (buf.length() > 0) {
                    buf.append(split);
                }
                buf.append(Character.toLowerCase(c));
            } else {
                buf.append(c);
            }
        }
        return buf.toString();
    }

    public static byte[] toBytes(String src, String encoding) {
        try {
            return src.getBytes(encoding);
        } catch (UnsupportedEncodingException e) {
            return src.getBytes();
        }
    }

    public static String[] splitString(String str, String sdelimiter,
                                       boolean isRemoveEmptyEntries) {
        String[] array = str.split(sdelimiter);

        if (!isRemoveEmptyEntries)
            return array;
        List<String> list = new ArrayList<String>();
        for (String entry : array) {
            if (!isNullOrWhiteSpace(entry))
                list.add(entry);
        }
        return list.toArray(new String[list.size()]);
    }

    public static boolean isNullOrWhiteSpace(String str) {
        return isNullOrEmpty(str)
                || (str.length() > 0 && str.trim().length() == 0);
    }

    public static Date strToDate(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return strToDate(str, format);
    }

    public static Date strToDate(String str, String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return strToDate(str, format);
    }

    public static Date strToCstDate(String str) {
        SimpleDateFormat format = new SimpleDateFormat(
                "EEE MMM dd HH:mm:ss 'CST' yyyy", Locale.US);
        Date dt = strToDate(str, format);
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.HOUR_OF_DAY, -8);
        dt=c.getTime();
        return dt;
    }

    public static Date strToDate(String str, DateFormat format) {
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String DateToStr(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        return dateString;
    }

    public static boolean IsNullOrEmpty(String str) {
        return str == null || str.equals("");
    }

    public static String joinString(String[] strs, int pos, int limit,
                                    String prefix, String suffix, String separate) {
        if (pos > strs.length - 1) {
            return joinString(strs, prefix, suffix, separate);
        }
        int size = pos + limit <= strs.length ? limit : strs.length - pos;
        String[] desStrs = new String[size];
        System.arraycopy(strs, pos, desStrs, 0, size);
        return joinString(desStrs, prefix, suffix, separate);
    }

    public static String joinString(String[] strs, String prefix,
                                    String suffix, String separate) {
        if (strs == null) {
            return null;
        }
        int length = strs.length;
        if (length == 0) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        if (strs.length >= 1) {
            if (!IsNullOrEmpty(prefix))
                sb.append(prefix);
            sb.append(strs[0]);
            if (!IsNullOrEmpty(suffix))
                sb.append(suffix);
        }
        for (int i = 1; i < length; i++) {
            if (IsNullOrEmpty(strs[i])) {
                continue;
            }
            if (!IsNullOrEmpty(separate))
                sb.append(separate);
            if (!IsNullOrEmpty(prefix))
                sb.append(prefix);
            sb.append(strs[i]);
            if (!IsNullOrEmpty(suffix))
                sb.append(suffix);
        }

        return sb.toString();
    }

    public static String joinString(List<String> strs, String prefix,
                                    String suffix, String separate) {
        if (strs == null) {
            return null;
        }
        int length = strs.size();
        if (length == 0) {
            return null;
        }

        StringBuffer sb = new StringBuffer();
        if (strs.size() >= 1) {
            if (!IsNullOrEmpty(prefix))
                sb.append(prefix);
            sb.append(strs.get(0));
            if (!IsNullOrEmpty(suffix))
                sb.append(suffix);
        }
        for (int i = 1; i < length; i++) {
            if (IsNullOrEmpty(strs.get(i))) {
                continue;
            }
            if (!IsNullOrEmpty(separate))
                sb.append(separate);
            if (!IsNullOrEmpty(prefix))
                sb.append(prefix);
            sb.append(strs.get(i));
            if (!IsNullOrEmpty(suffix))
                sb.append(suffix);
        }

        return sb.toString();
    }

    public static String subString(String oriStr, int beginIndex, int len) {
        String str = "";
        int strlen = oriStr.length();
        beginIndex = beginIndex - 1;
        if (strlen <= beginIndex) {
            System.out.println("out of " + oriStr
                    + "'s length, please recheck!");
        } else if (strlen <= beginIndex + len) {
            str = oriStr.substring(beginIndex);
        } else {
            str = oriStr.substring(beginIndex, beginIndex + len);
        }
        return str;
    }

    public static String padLeft(String oriStr, int len, char alexin) {
        String str = "";
        int strlen = oriStr.length();
        if (strlen < len) {
            for (int i = 0; i < len - strlen; i++) {
                str = str + alexin;
            }
        }
        str = str + oriStr;
        return str;
    }

    public static String padRight(String oriStr, int len, char alexin) {
        String str = "";
        int strlen = oriStr.length();
        if (strlen < len) {
            for (int i = 0; i < len - strlen; i++) {
                str = str + alexin;
            }
        }
        str = oriStr + str;
        return str;
    }

    public static String joinString(String[] arr, String sdelimiter) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1)
                sb.append(sdelimiter);
        }
        return sb.toString();
    }
}
