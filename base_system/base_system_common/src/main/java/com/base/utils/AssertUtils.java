package com.base.utils;

import com.base.helper.StringHelper;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;

public class AssertUtils extends Assert {
    public AssertUtils() {
    }

    public static void isTrue(boolean expression, RuntimeException throwIfAssertFail) {
        if (!expression) {
            throw throwIfAssertFail;
        }
    }

    public static void isNull(Object object, RuntimeException throwIfAssertFail) {
        if (object != null) {
            throw throwIfAssertFail;
        }
    }

    public static void notNull(Object object, RuntimeException throwIfAssertFail) {
        if (object == null) {
            throw throwIfAssertFail;
        }
    }

    public static void hasLength(String text, RuntimeException throwIfAssertFail) {
        if (!StringUtils.hasLength(text)) {
            throw throwIfAssertFail;
        }
    }

    public static void hasText(String text, RuntimeException throwIfAssertFail) {
        if (!StringUtils.hasText(text)) {
            throw throwIfAssertFail;
        }
    }

    public static void doesNotContain(String textToSearch, String substring, RuntimeException throwIfAssertFail) {
        if (StringUtils.hasLength(textToSearch) && StringUtils.hasLength(substring) && textToSearch.indexOf(substring) != -1) {
            throw throwIfAssertFail;
        }
    }

    public static void noNullElements(Object[] array, RuntimeException throwIfAssertFail) {
        if (array != null) {
            Object[] var2 = array;
            int var3 = array.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                Object element = var2[var4];
                if (element == null) {
                    throw throwIfAssertFail;
                }
            }
        }

    }

    public static void notEmpty(Object[] array, RuntimeException throwIfAssertFail) {
        if (ObjectUtils.isEmpty(array)) {
            throw throwIfAssertFail;
        }
    }

    public static void notEmpty(String str, String message) {
        if (!StringHelper.hasLength(str)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notEmpty(Collection<?> collection, RuntimeException throwIfAssertFail) {
        if (CollectionUtils.isEmpty(collection)) {
            throw throwIfAssertFail;
        }
    }

    public static void notEmpty(Map<?, ?> map, RuntimeException throwIfAssertFail) {
        if (CollectionUtils.isEmpty(map)) {
            throw throwIfAssertFail;
        }
    }

    public static void isInstanceOf(Class<?> clazz, Object obj, RuntimeException throwIfAssertFail) {
        notNull(clazz, "clazz to check against must not be null");
        if (!clazz.isInstance(obj)) {
            throw throwIfAssertFail;
        }
    }

    public static void isAssignable(Class<?> superType, Class<?> subType, RuntimeException throwIfAssertFail) {
        notNull(superType, "Type to check against must not be null");
        if (subType == null || !superType.isAssignableFrom(subType)) {
            throw throwIfAssertFail;
        }
    }
}
