package com.base.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClassHelper extends ClassUtils {
    private static final Logger log = LoggerFactory.getLogger(ClassHelper.class);

    public ClassHelper() {
    }

    public static <T> Class<T> getSuperClassGenricType(Class clazz) {
        return getSuperClassGenricType(clazz, 0);
    }

    public static Class getSuperClassGenricType(Class clazz, int index) {
        Type genType = clazz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        } else {
            Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
            if (index < params.length && index >= 0) {
                return !(params[index] instanceof Class) ? Object.class : (Class)params[index];
            } else {
                return Object.class;
            }
        }
    }

    public static <T> List<T> getStaticFieldByType(Class clazz, Class<T> type) {
        Field[] fields = clazz.getFields();
        ArrayList list = new ArrayList();

        try {
            Field[] var4 = fields;
            int var5 = fields.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                Field field = var4[var6];
                if ((field.getModifiers() & 8) != 0 && type.equals(field.getType())) {
                    list.add(field.get(type));
                }
            }
        } catch (Exception var8) {
            log.error("error found in getStaticFieldByType of " + clazz.getName(), var8);
        }

        return list;
    }

    public static Set<String> getFieldNameByType(Class clazz, Class type) {
        Assert.notNull(clazz);
        Assert.notNull(type);
        Set<String> fieldNames = new HashSet();
        Field[] var3 = clazz.getDeclaredFields();
        int var4 = var3.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            Field field = var3[var5];
            if (field.getType().equals(type)) {
                fieldNames.add(field.getName());
            }
        }

        return fieldNames;
    }

    public static Set<String> getFieldNameByAllType(Class clazz, Class type) {
        Assert.notNull(clazz);
        Assert.notNull(type);
        Set<String> fieldNames = new HashSet();
        Field[] var3 = clazz.getDeclaredFields();
        int var4 = var3.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            Field field = var3[var5];
            if (isAssignable(type, field.getType())) {
                fieldNames.add(field.getName());
            }
        }

        return fieldNames;
    }

    public static boolean isInterfaceOf(Class<?> interfaceClass, Class<?> implementsClass) {
        boolean result = false;
        Assert.isTrue(interfaceClass.isInterface(), "接口类必须是一个接口");
        if (implementsClass != null) {
            Class<?>[] types = implementsClass.getInterfaces();
            Class[] var4 = types;
            int var5 = types.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                Class<?> cls = var4[var6];
                if (cls.equals(interfaceClass)) {
                    result = true;
                    break;
                }

                result = isInterfaceOf(interfaceClass, cls.getSuperclass());
                if (result) {
                    break;
                }
            }
        }

        return result;
    }
}
