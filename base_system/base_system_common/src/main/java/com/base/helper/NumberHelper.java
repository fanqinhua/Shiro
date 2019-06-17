package com.base.helper;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberHelper {
    public NumberHelper() {
    }

    public static boolean isDigital(String str) {
        return str != null && str.matches("^[0-9]*$");
    }

    public static boolean isInteger(String s) {
        return s != null && s.matches("[-\\+]?[\\d]*$");
    }

    public static boolean isDouble(String s) {
        return s != null && s.matches("[-\\+]?[.\\d]*$");
    }

    public static Double parseDouble(String s) {
        return isDouble(s) ? Double.parseDouble(s) : 0.0D;
    }

    public static Integer parseInteger(String s) {
        if (isInteger(s)) {
            return Integer.parseInt(s);
        } else {
            if (isDouble(s)) {
                s = s.substring(0, s.indexOf("."));
                if (isDigital(s)) {
                    return Integer.parseInt(s);
                }
            }

            return 0;
        }
    }

    public static double formatDouble2(Double d) {
        if (d == null) {
            return 0.0D;
        } else {
            BigDecimal bg = (new BigDecimal(d)).setScale(2, RoundingMode.DOWN);
            return bg.doubleValue();
        }
    }

    public static void main(String[] args) {
        double s = 90.199999999999D;
        System.out.println(formatDouble2(s));
    }
}
