package com.cupid.demo.common.utils.Reflect;

import org.apache.poi.ss.formula.functions.T;

import java.lang.reflect.Field;

/**
 * 反射有关工具类
 * @author cupid
 * @date 2020/04/21
 */
public class ReflectUtil {
    /**
     * 设置对象的字符串类型如果为null值的全部转换为""
     * @param t 需校验的对象
     * @return 返回替换内容之后的对象
     */
    public T updateString(T t){
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getType() == String.class) {

                field.setAccessible(true);
                try {
                    Object object = field.get(t);
                    if (null == object) {
                        field.set(t, "");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return t;
    }

    /**
     * 设置对象Integer类型的属性如果为null则全部转换为0
     * @param t 需校验的对象
     * @return 返回替换内容之后的对象
     */
    public T updateInteger(T t){
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getType() == Integer.class) {

                field.setAccessible(true);
                try {
                    Object object = field.get(t);
                    if (null == object) {
                        field.set(t, 0);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return t;
    }
}