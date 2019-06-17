package com.base.helper;

import org.apache.commons.net.util.Base64;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public class Base64Helper {
    public Base64Helper() {
    }

    public static String encode(byte[] data) {
        return Base64.encodeBase64String(data, true);
    }

    public static byte[] decode(String str) {
        return Base64.decodeBase64(str);
    }

    public static BigInteger decodeInteger(String str) {
        return Base64.decodeInteger(str.getBytes());
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String s = "655371111111sddd11111111111111111111111111";
        System.out.println(s);
        String x = encode(s.getBytes());
        System.out.println(x);
        String x1 = new String(decode(x));
        System.out.println(x1);
    }
}
