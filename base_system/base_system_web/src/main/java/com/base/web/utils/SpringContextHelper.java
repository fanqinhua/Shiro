package com.base.web.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Locale;

@Component
@Lazy(false)
public class SpringContextHelper implements ApplicationContextAware {
    private static Logger logger = LoggerFactory.getLogger(SpringContextHelper.class);
    private static ApplicationContext applicationContext;

    public SpringContextHelper() {
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        checkApplicationContext();
        return applicationContext;
    }

    public static <T> T getBean(String name) {
        checkApplicationContext();
        return (T) applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        checkApplicationContext();
        return applicationContext.getBean(clazz);
    }

    private static void checkApplicationContext() {
        if (applicationContext == null) {
            throw new IllegalStateException("applicaitonContext not inject,please in applicationContext.xml define SpringContextHelper");
        }
    }

    public static String getMessage(String code, Object... args) {
        if (applicationContext != null) {
            return filterMessage(applicationContext.getMessage(code, args, getDefaultMessage(code), getLocale()));
        } else {
            logger.warn("Spring applicationContext not set in SpringContextHelper. Please check jaf-core-applicationContext.xml is load?");
            return filterMessage(MessageFormat.format(getDefaultMessage(code), args));
        }
    }

    public static String getMessage(String code, String defaultMessage, Object... args) {
        if (applicationContext != null) {
            return filterMessage(applicationContext.getMessage(code, args, defaultMessage, getLocale()));
        } else {
            logger.warn("Spring applicationContext not set in SpringContextHelper. Please check jaf-core-applicationContext.xml is load?");
            return filterMessage(MessageFormat.format(defaultMessage, args));
        }
    }

    public static String getMessage(MessageSourceResolvable resolvable) {
        if (applicationContext != null) {
            return filterMessage(applicationContext.getMessage(resolvable, getLocale()));
        } else {
            logger.warn("Spring applicationContext not set in SpringContextHelper. Please check jaf-core-applicationContext.xml is load?");
            return "Unknow Exception";
        }
    }

    private static String filterMessage(String message) {
        return message.replaceAll("\\{[^{]+\\}", "");
    }

    public static String getDefaultMessage(String code) {
        StringBuffer sb = (new StringBuffer()).append('[').append(code).append("]Undefined");
        return sb.toString();
    }

    private static Locale getLocale() {
        Locale locale = Locale.CHINA;

        return locale;
    }
}
