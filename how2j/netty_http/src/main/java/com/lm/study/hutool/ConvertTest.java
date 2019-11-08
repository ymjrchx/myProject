package com.lm.study.hutool;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.convert.Converter;
import cn.hutool.core.convert.ConverterRegistry;
import cn.hutool.core.util.CharsetUtil;
import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.lm.study.hutool.PrintUtil.*;

/**
 * @author chenxin
 * @date 2019/7/15 14:30
 */
public class ConvertTest {
    @Test
    public void Convert() {
        int a = 1;
        String aStr = Convert.toStr(a);
        pl(aStr);

        long[] b = {1, 2, 3, 5};
        String bStr = Convert.toStr(b);
        pl(bStr);

        String[] s1 = {"1", "2", "3", "4"};
        Integer[] intArray = Convert.toIntArray(s1);
        System.out.println(Arrays.asList(intArray));

        long[] s2 = {1, 2, 3, 4, 5};
        Integer[] inArray2 = Convert.toIntArray(s2);
        pl(Arrays.asList(inArray2));

        String s3 = "2017-05-06";
        Date value = Convert.toDate(s3);
        System.out.println(value);

        Object[] objects = {"a", "你", "好", "", 1};

        List<?> list = Convert.convert(List.class, objects);
        List<?> objects1 = Convert.toList(a);
        pl(list);
        pl(objects1);

        String s4 = "123456789";
        String sbc = Convert.toSBC(s4);
        pl(s4);
        pl(sbc);

        String s5 = "，。！。。。。１２３４５５２２５";
        String dbc = Convert.toDBC(s5);
        pl(s5);
        pl(dbc);
    }

    @Test
    public void Hex() throws UnsupportedEncodingException {
        String a = "我是一个小小的可爱的字符串";
        String hex = Convert.toHex(a, CharsetUtil.CHARSET_UTF_8);
        System.out.println(hex);
        byte[] bytes = a.getBytes("unicode");
        for (byte b : bytes) {
            p(Integer.toHexString(b & 0xff));
            p(" ");
        }
        System.out.println();
        byte[] bytes1 = a.getBytes("UTF-8");
        for (byte b : bytes1) {
            p(Integer.toHexString(b & 0xff));
            p(" ");
        }

        String hex1 = "e68891e698afe4b880e4b8aae5b08fe5b08fe79a84e58fafe788b1e79a84e5ad97e7aca6e4b8b2";
        String raw = Convert.hexToStr(hex1, CharsetUtil.CHARSET_UTF_8);
        System.out.println(raw);
    }

    @Test
    public void Unicode() throws UnsupportedEncodingException {
        String a = "我是一个小小的可爱的字符串";
        String unicode = Convert.strToUnicode(a);
        System.out.println(unicode);
        String raw = Convert.unicodeToStr(unicode);
        System.out.println(raw);

        byte[] bytes1 = a.getBytes("unicode");
        for (byte b : bytes1) {
            String str = Integer.toHexString(b & 0xff);
            p(str);
            p(" ");
        }
        String string = new String(bytes1, "unicode");
        pl(string);

        String s6 = "我不是乱码";
        String result = Convert.convertCharset(s6, CharsetUtil.UTF_8, CharsetUtil.ISO_8859_1);
        System.out.println(result);
        String raw1 = Convert.convertCharset(result, CharsetUtil.ISO_8859_1, "UTF-8");
        System.out.println(raw1);

        long a1 = 4555345;
        long minute = Convert.convertTime(a1, TimeUnit.MILLISECONDS, TimeUnit.MINUTES);
        pl(minute);

        double a2 = 67556.32;
        String digitUppercase = Convert.digitToChinese(a2);
        pl(digitUppercase);

        //去包装
        Class<?> wrapClass = Integer.class;

//结果为：int.class
        Class<?> unWraped = Convert.unWrap(wrapClass);

//包装
        Class<?> primitiveClass = long.class;

//结果为：Long.class
        Class<?> wraped = Convert.wrap(primitiveClass);


    }

    @Test
    public void ConverterRegistry() {
        int a = 3432;
        ConverterRegistry converterRegistry = ConverterRegistry.getInstance();
        String result = converterRegistry.convert(String.class,a);
        Assert.assertEquals("3432",result);

        converterRegistry.putCustom(String.class,CustomConverter.class);
        String result1 = converterRegistry.convert(String.class,a);
        System.out.println(result1);


    }

public static class CustomConverter implements Converter<String> {

    @Override
    public String convert(Object value, String defaultValue) throws IllegalArgumentException {
        return "custom: " +value.toString();
    }
}























}
