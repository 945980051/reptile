package com.sunshine.reptile.utils;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author 张梓枫
 * @Description 对象工具类
 * @date:   2019年1月2日 上午10:08:44
 */
public abstract class ObjectUtils {

    /**
     * @author 张梓枫
     * @Description: 判断对象是否为空
     * @param @param obj
     * @param @return
     * @return boolean
     * @throws Exception 
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        }
        if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        }
        if (obj instanceof List) {
            return ((List<?>) obj).isEmpty();
        }
        if (obj instanceof Map) {
            return ((Map<?, ?>) obj).isEmpty();
        }
        if (obj instanceof Set) {
            return ((Set<?>)obj).isEmpty();
        }
        return false;
    }

    /**
     * @author 张梓枫
     * @Description:判断对象是否不为空
     * @param @param obj
     * @param @return
     * @return boolean
     * @throws Exception 
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    /**
     * @author 张梓枫
     * @Description:判断数组是否为空
     * @param @param array
     * @param @return
     * @return boolean
     * @throws Exception 
     */
    public static boolean isEmpty(Object[] array) {
        return (array == null || array.length == 0);
    }

    /**
     * @author 张梓枫
     * @Description:判断数组是否不为空
     * @param @param array
     * @param @return
     * @return boolean
     * @throws Exception 
     */
    public static boolean isNotEmpty(Object[] array) {
        return !isEmpty(array);
    }

    /**
     * @author 张梓枫
     * @Description:判断对象是否是数组
     * @param @param obj
     * @param @return
     * @return boolean
     * @throws Exception 
     */
    public static boolean isArray(Object obj) {
        return (obj != null && obj.getClass().isArray());
    }

    /**
     * @author 张梓枫
     * @Description:将与数组中相同的对象添加到数组中
     * @param @param array 数组
     * @param @param obj 与数组元素相同的对象
     * @param @return
     * @return A[]
     * @throws Exception 
     */
    public static <A, O extends A> A[] addObjectToArray(A[] array, O obj) {
        Class<?> compType = Object.class;
        if (array != null) {
            compType = array.getClass().getComponentType();
        } else if (obj != null) {
            compType = obj.getClass();
        }
        int newArrLength = (array != null ? array.length + 1 : 1);
        @SuppressWarnings("unchecked")
        A[] newArr = (A[]) Array.newInstance(compType, newArrLength);
        if (array != null) {
            System.arraycopy(array, 0, newArr, 0, array.length);
        }
        newArr[newArr.length - 1] = obj;
        return newArr;
    }

    /**
     * @author 张梓枫
     * @Description: 判断两个对象是否相等
     * @param @param a
     * @param @param b
     * @param @return
     * @return boolean
     * @throws Exception 
     */
    public static boolean equals(Object a, Object b) {
        if (Objects.equals(a, b)) {
            return true;
        }
        return false;
    }

    /**
     * @author 张梓枫
     * @Description:判断两个数组是否相等
     * @param @param o1
     * @param @param o2
     * @param @return
     * @return boolean
     * @throws Exception 
     */
    public static boolean arrayEquals(Object o1, Object o2) {
        if (o1 instanceof Object[] && o2 instanceof Object[]) {
            return Arrays.equals((Object[]) o1, (Object[]) o2);
        }
        if (o1 instanceof boolean[] && o2 instanceof boolean[]) {
            return Arrays.equals((boolean[]) o1, (boolean[]) o2);
        }
        if (o1 instanceof byte[] && o2 instanceof byte[]) {
            return Arrays.equals((byte[]) o1, (byte[]) o2);
        }
        if (o1 instanceof char[] && o2 instanceof char[]) {
            return Arrays.equals((char[]) o1, (char[]) o2);
        }
        if (o1 instanceof double[] && o2 instanceof double[]) {
            return Arrays.equals((double[]) o1, (double[]) o2);
        }
        if (o1 instanceof float[] && o2 instanceof float[]) {
            return Arrays.equals((float[]) o1, (float[]) o2);
        }
        if (o1 instanceof int[] && o2 instanceof int[]) {
            return Arrays.equals((int[]) o1, (int[]) o2);
        }
        if (o1 instanceof long[] && o2 instanceof long[]) {
            return Arrays.equals((long[]) o1, (long[]) o2);
        }
        if (o1 instanceof short[] && o2 instanceof short[]) {
            return Arrays.equals((short[]) o1, (short[]) o2);
        }
        return false;
    }

    /**
     * @author 张梓枫
     * @Description: 将对象转换为数组
     * @param @param source
     * @param @return
     * @return Object[]
     * @throws Exception 
     */
    public static Object[] toObjectArray(Object source) {
        if (source instanceof Object[]) {
            return (Object[]) source;
        }
        if (source == null) {
            return new Object[0];
        }
        if (!source.getClass().isArray()) {
            throw new IllegalArgumentException("Source is not an array: " + source);
        }
        int length = Array.getLength(source);
        if (length == 0) {
            return new Object[0];
        }
        Class<?> wrapperType = Array.get(source, 0).getClass();
        Object[] newArray = (Object[]) Array.newInstance(wrapperType, length);
        for (int i = 0; i < length; i++) {
            newArray[i] = Array.get(source, i);
        }
        return newArray;
    }

    /**
     * @author 张梓枫
     * @Description:将对象转换为集合
     * @param @param source
     * @param @return
     * @return List<?>
     * @throws Exception 
     */
    public static List<?> arrayToList(Object source) {
        return Arrays.asList(ObjectUtils.toObjectArray(source));
    }
    
    public static Boolean arrayContains(Object t,Object r){
        List<?> list = arrayToList(t);
        if (list.contains(r)) {
            return true;
        }
        return false;
    }

    public static String convertToString(Object obj) {
        if (ObjectUtils.isNotEmpty(obj)) {
            return String.valueOf(obj);
        }
        return "";
    }

    public static Integer convertToInteger(Object obj) {
        if (ObjectUtils.isNotEmpty(obj)) {
            return Integer.valueOf(convertToString(obj));
        }
        return null;
    }

    public static Double convertToDouble(Object obj) {
        if (ObjectUtils.isNotEmpty(obj)) {
            return Double.valueOf(convertToString(obj));
        }
        return null;
    }

    public static Long convertToLong(Object obj) {
        if (ObjectUtils.isNotEmpty(obj)) {
            return Long.valueOf(convertToString(obj));
        }
        return null;
    }

    public static boolean convertToBoolean(String str) {
        if (ObjectUtils.isNotEmpty(str)) {
            return Boolean.valueOf(str);
        }
        return false;
    }

    public static Short convertToShort(Object obj) {
        if (ObjectUtils.isNotEmpty(obj)) {
            return Short.valueOf(convertToString(obj));
        }
        return null;
    }

    public static BigDecimal convertToBigDecimal(Object obj) {
        if (ObjectUtils.isNotEmpty(obj)) {
            return new BigDecimal(convertToString(obj));
        }
        return null;
    }

}
