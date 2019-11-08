package com.dgg.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import sun.misc.Unsafe;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

/**
 * 解析json的数据类
 */
public class JSONHelper {

    private static volatile ObjectMapper objectMapper = null;
    /**
     * flag for initialization ,
     * 0  => default
     * -1 => initializing
     * 1  => initialized
     */
    private transient volatile int flag = 0;

    /**
     * transform Object to String
     * for example : toJson(new Student()) -> transform Student instance to String
     *
     * @param object
     * @return
     */
    public static String toJSON(Object object) {
        Objects.requireNonNull(object,"param can not be null");
        new JSONHelper().initObjectMapper();
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param object
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T readValue(Object object, Class<T> clazz) {
        Objects.requireNonNull(object,"param can not be null");
        new JSONHelper().initObjectMapper();
        try {
            return object instanceof String
                    ? readValue(object.toString(), clazz)
                    : objectMapper.readValue(toJSON(object), clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * transform Object to List of any Class
     * for example : readValue(new String("[{\"name\":\"Eric\",\"age\":\"23\"}]"),Student.class) -> transform string to List<Student> class instance
     *
     * @param object
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> readValueList(Object object, Class<T> clazz) {
        Objects.requireNonNull(object,"param can not be null");
        new JSONHelper().initObjectMapper();
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, clazz);
        try {
            return object instanceof String
                    ? readValueList(object.toString(), javaType)
                    : objectMapper.readValue(toJSON(object), javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static <T> T readValue(String original, Class<T> clazz) {
        new JSONHelper().initObjectMapper();
        try {
            return objectMapper.readValue(original, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List<T> readValueList(String original, Class<T> clazz) {
        new JSONHelper().initObjectMapper();
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, clazz);
        try {
            return objectMapper.readValue(original, javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static <T> List<T> readValueList(String original, JavaType javaType) {
        new JSONHelper().initObjectMapper();
        try {
            return objectMapper.readValue(original, javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * make sure JSONHelper is singleton , user spin , use more CPU,more efficient
     * thread safe
     */
    private void initObjectMapper() {

        while (objectMapper == null) {
            int flag_cur;
            if ((flag_cur = flag) < 0)
                Thread.yield();//lose initialization race, just spin
            else if (U.compareAndSwapInt(this, FLAG, flag_cur, -1)) {
                if (objectMapper == null) {
                    try {
                        objectMapper = new ObjectMapper();
                        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
                        flag_cur = 1;
                    } finally {
                        flag = flag_cur;
                    }
                    break;
                }
            }
        }

    }

    private static sun.misc.Unsafe U;
    private static long FLAG;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            U = (Unsafe) field.get(null);
            Class<?> k = JSONHelper.class;
            FLAG = U.objectFieldOffset(k.getDeclaredField("flag"));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }
}
