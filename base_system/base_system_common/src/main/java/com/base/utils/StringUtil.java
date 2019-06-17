package com.base.utils;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StringUtil {

    public static List<Integer> splitToListInt(String str) {
        List<Integer> list = new ArrayList<>();
        if (StringUtils.isNotEmpty(str)) {
            String[] split = StringUtils.split(str, ",");
            for (String string : split) {
                list.add(NumberUtils.toInt(string, -1));
            }
        }
        return list;
    }

    public static String generateUID() {
        Random random = new Random();
        String result = "";
        for (int i = 0; i < 8; i++) {
            //首字母不能为0
            result += (random.nextInt(9) + 1);
        }
        return result;
    }
}
