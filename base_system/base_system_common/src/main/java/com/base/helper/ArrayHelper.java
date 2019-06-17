package com.base.helper;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.util.Assert;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;

public class ArrayHelper {
    public ArrayHelper() {
    }

    public static String toSimpleString(Object array) {
        return toSimpleString(array, ",");
    }

    public static boolean isNotEmpty(Object[] array) {
        return ArrayUtils.isNotEmpty(array);
    }

    public static boolean isEmpty(Object[] array) {
        return ArrayUtils.isEmpty(array);
    }

    public static boolean contains(Object[] array, Object objectToFind) {
        return ArrayUtils.contains(array, objectToFind);
    }

    public static boolean containsOne(Object[] array, Object[] arrayToFind) {
        boolean containsOne = false;
        Assert.notNull(arrayToFind);
        Object[] var3 = arrayToFind;
        int var4 = arrayToFind.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            Object obj = var3[var5];
            if (ArrayUtils.contains(array, obj)) {
                containsOne = true;
                break;
            }
        }

        return containsOne;
    }

    public static boolean containsAll(Object[] array, Object[] arrayToFind) {
        boolean containsAll = true;
        Assert.notNull(arrayToFind);
        Object[] var3 = arrayToFind;
        int var4 = arrayToFind.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            Object obj = var3[var5];
            if (!ArrayUtils.contains(array, obj)) {
                containsAll = false;
                break;
            }
        }

        return containsAll;
    }

    public static Object[] add(Object[] array, Object element) {
        return ArrayUtils.add(array, element);
    }

    public static String toSimpleString(Object array, String separator) {
        StringBuilder sb = new StringBuilder();
        if (array != null) {
            if (!array.getClass().isArray()) {
                sb.append(array);
            } else {
                for(int i = 0; i < Array.getLength(array); ++i) {
                    if (sb.length() == 0) {
                        sb.append(Array.get(array, i));
                    } else {
                        sb.append(separator).append(Array.get(array, i));
                    }
                }
            }
        }

        return sb.toString();
    }




    public static String[] toArrayByCollection(Collection<String> collection) {
        Assert.notEmpty(collection);
        String[] array = new String[collection.size()];
        int i = 0;

        String str;
        for(Iterator var3 = collection.iterator(); var3.hasNext(); array[i++] = str) {
            str = (String)var3.next();
        }

        return array;
    }

    public static Object findObject(Object[] objets, Class<?> clazz) {
        if (clazz == null) {
            return null;
        } else {
            if (isNotEmpty(objets)) {
                Object[] var2 = objets;
                int var3 = objets.length;

                for(int var4 = 0; var4 < var3; ++var4) {
                    Object obj = var2[var4];
                    if (obj != null && clazz.isAssignableFrom(obj.getClass())) {
                        return obj;
                    }
                }
            }

            return null;
        }
    }

    public static <T> T[] remove(T[] objects, T obj) {
        Assert.notNull(objects);

        for(int i = objects.length - 1; i >= 0; --i) {
            if (objects[i] != null && objects[i].equals(obj)) {
                objects = (T[])ArrayUtils.remove(objects, i);
            }
        }

        return objects;
    }

    public static <T> Integer[] toArrayInteger(String s) {
        return toArrayInteger(s, ",");
    }

    public static <T> Integer[] toArrayInteger(String s, String split) {
        Assert.notNull(s);
        String[] ss = s.split(split);
        Integer[] result = new Integer[ss.length];

        for(int i = 0; i < ss.length; ++i) {
            if (NumberHelper.isInteger(ss[i])) {
                result[i] = NumberHelper.parseInteger(ss[i]);
            }
        }

        return result;
    }
}
