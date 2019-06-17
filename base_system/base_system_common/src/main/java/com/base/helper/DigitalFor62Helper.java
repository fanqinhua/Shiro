package com.base.helper;

import java.util.Stack;

public class DigitalFor62Helper {
    private static char[] charSet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    public DigitalFor62Helper() {
    }

    public static String to62(long number, int length) {
        Long rest = number;
        Stack<Character> stack = new Stack();

        StringBuilder result;
        for(result = new StringBuilder(0); rest != 0L; rest = rest / 62L) {
            stack.add(charSet[(new Long(rest - rest / 62L * 62L)).intValue()]);
        }

        while(!stack.isEmpty()) {
            result.append(stack.pop());
        }

        int result_length = result.length();
        StringBuilder temp0 = new StringBuilder();

        for(int i = 0; i < length - result_length; ++i) {
            temp0.append('0');
        }

        return temp0.toString() + result.toString();
    }

    public static String to10(String ident62) {
        Long dst = 0L;

        for(int i = 0; i < ident62.length(); ++i) {
            char c = ident62.charAt(i);

            for(int j = 0; j < charSet.length; ++j) {
                if (c == charSet[j]) {
                    dst = dst * 62L + (long)j;
                    break;
                }
            }
        }

        String str = String.format("%08d", dst);
        return str;
    }

    public static String convertBase62ToDecimal(String ident62) {
        int decimal = 0;
        int base = 62;
        int cnt = 0;
        byte[] ident = ident62.getBytes();

        for(int i = ident.length - 1; i >= 0; --i) {
            int num = 0;
            if (ident[i] > 48 && ident[i] <= 57) {
                num = ident[i] - 48;
            } else if (ident[i] >= 65 && ident[i] <= 90) {
                num = ident[i] - 65 + 10;
            } else if (ident[i] >= 97 && ident[i] <= 122) {
                num = ident[i] - 97 + 10 + 26;
            }

            int keisu = (int)Math.pow((double)base, (double)cnt);
            decimal += num * keisu;
            ++cnt;
        }

        return String.format("%08d", decimal);
    }

    public static void main(String[] args) {
        long s = System.currentTimeMillis();
        String s1 = to62(System.currentTimeMillis(), 0);
        String s2 = to10(s1);
        System.out.println("s=" + s);
        System.out.println("s1=" + s1);
        System.out.println("s2=" + s2);
        System.out.println("10System=" + convertBase62ToDecimal("2NaWL"));
        System.out.println("10System=" + to10("00008sKrPTsBmv"));
    }
}
