package com.base.web.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.codec.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class CustomizedPropertyConfigurer  extends PropertyPlaceholderConfigurer {
    private static Map<String, Object> ctxPropertiesMap;
    private static Logger logger = LoggerFactory.getLogger(CustomizedPropertyConfigurer.class);
    public CustomizedPropertyConfigurer() {
    }
    protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) throws BeansException {
        String needDecrypties = (String)props.get("field.decrypty");
        if (StringUtils.isNotEmpty(needDecrypties)) {
            String[] decProps = needDecrypties.split(",");
            String[] var5 = decProps;
            int var6 = decProps.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                String sProps = var5[var7];
                if (StringUtils.isNotEmpty(sProps)) {
                    String base64Password = (String)props.get(sProps);
                    String password = decodeBase64(base64Password);
                    props.setProperty(sProps, password);
                }
            }
        }
        super.processProperties(beanFactory, props);
        ctxPropertiesMap = new HashMap();
        Iterator var11 = props.keySet().iterator();

        while(var11.hasNext()) {
            Object key = var11.next();
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            ctxPropertiesMap.put(keyStr, value);
        }
    }
    public static Object getContextProperty(String name) {
        return ctxPropertiesMap.get(name);
    }

    public static Integer paserInteger(String name) {
        String s = String.valueOf(getContextProperty(name));
        return s != null && s.matches("\\d+") ? Integer.parseInt(s) : 0;
    }

    public static Long paserLong(String name) {
        String s = String.valueOf(getContextProperty(name));
        return s != null && s.matches("\\d+") ? Long.parseLong(s) : 0L;
    }

    public static String paserString(String name) {
        return String.valueOf(getContextProperty(name));
    }

    public static boolean paserBoolean(String name) {
        return "true".equalsIgnoreCase(String.valueOf(getContextProperty(name)));
    }

    public static String decodeBase64(String base64Str) {
        if (StringUtils.isEmpty(base64Str)) {
            return "";
        } else {
            String s = Base64.decodeToString(base64Str);
            return s;
        }
    }

}
