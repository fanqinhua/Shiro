package com.base.helper;

import com.base.utils.AssertUtils;
import org.apache.commons.lang.text.StrBuilder;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public class StringHelper extends StringUtils {
    public static final String EMPTY = "";

    public StringHelper() {
    }

    public static String capitalize(String str) {
        if (hasLength(str)) {
            StringBuffer wrod = new StringBuffer();
            str = wrod.append(str.substring(0, 1).toUpperCase()).append(str.substring(1)).toString();
        }

        return str;
    }

    public static String firstLetterLowercase(String str) {
        if (hasLength(str)) {
            StringBuffer wrod = new StringBuffer();
            str = wrod.append(str.substring(0, 1).toLowerCase()).append(str.substring(1)).toString();
        }

        return str;
    }

    public static boolean rightFormat(String str, String regex) {
        AssertUtils.notEmpty(str, "The str not should be empty or null!");
        AssertUtils.notEmpty(regex, "The regex not should be empty or null!");
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(str).matches();
    }

    public static boolean isBlank(String str) {
        return org.apache.commons.lang.StringUtils.isBlank(str);
    }

    public static boolean arrayContainValue(String[] array, String value) {
        if (array != null && array.length != 0 && hasText(value)) {
            String[] var2 = array;
            int var3 = array.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                String s = var2[var4];
                if (value.equals(s)) {
                    return true;
                }
            }

            return false;
        } else {
            return false;
        }
    }

    public static boolean arrayContainValue(List<String> array, String value) {
        return array != null && array.size() != 0 && hasText(value) ? arrayContainValue((String[])array.toArray(new String[array.size()]), value) : false;
    }

    public static boolean isNotBlank(String str) {
        return org.apache.commons.lang.StringUtils.isNotBlank(str);
    }

    public static String trimToEmpty(String str) {
        return org.apache.commons.lang.StringUtils.trimToEmpty(str);
    }

    public static String removeEndIgnoreCase(String str, String remove) {
        return org.apache.commons.lang.StringUtils.removeEndIgnoreCase(str, remove);
    }

    public static String removeEnd(String str, String remove) {
        return org.apache.commons.lang.StringUtils.removeEnd(str, remove);
    }

    public static String removeStart(String str, String remove) {
        return org.apache.commons.lang.StringUtils.removeStart(str, remove);
    }

    public static String removeStartIgnoreCase(String str, String remove) {
        return org.apache.commons.lang.StringUtils.removeStartIgnoreCase(str, remove);
    }

    public static String remove(String str, String remove) {
        return org.apache.commons.lang.StringUtils.remove(str, remove);
    }

    public static boolean equalsIgnoreCase(String str1, String str2) {
        return org.apache.commons.lang.StringUtils.equalsIgnoreCase(str1, str2);
    }

    public static boolean equals(String str1, String str2) {
        return org.apache.commons.lang.StringUtils.equals(str1, str2);
    }

    public static int indexOf(String str, String searchStr) {
        return org.apache.commons.lang.StringUtils.indexOf(str, searchStr);
    }

    public static String defaultString(String str) {
        return str == null ? "" : str;
    }

    public static String defaultString(String str, String defaultStr) {
        return str == null ? defaultStr : str;
    }

    public static String defaultIfBlank(String str, String defaultStr) {
        return org.apache.commons.lang.StringUtils.isBlank(str) ? defaultStr : str;
    }

    public static String defaultIfEmpty(String str, String defaultStr) {
        return org.apache.commons.lang.StringUtils.isEmpty(str) ? defaultStr : str;
    }

    public static String reverse(String str) {
        return str == null ? null : (new StrBuilder(str)).reverse().toString();
    }

    public static String randomCode(String prefix, int numLen) {
        if (numLen == 0) {
            return prefix;
        } else {
            StringBuffer sb = new StringBuffer(defaultIfBlank(prefix, ""));
            Random rnd = new Random();

            for(int i = 0; i < numLen; ++i) {
                sb.append((char)(48 + rnd.nextInt(10)));
            }

            return sb.toString();
        }
    }

    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < length; ++i) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }

        return sb.toString().toUpperCase();
    }

    public static String getRandomNumber(int length) {
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < length; ++i) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }

        return sb.toString().toUpperCase();
    }

    public static String getSerialString() {
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < 6; ++i) {
            int number = random.nextInt(base.length());
            sb.append(number);
        }

        long s = System.currentTimeMillis() * 1000000L + (long)Integer.parseInt(sb.toString());
        return DigitalFor62Helper.to62(s, 12);
    }

    public static void main(String[] args) {
        System.out.println("10System=" + getSerialString());
    }
}
