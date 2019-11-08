package com.lm.study.hutool;

import cn.hutool.core.clone.CloneRuntimeException;
import cn.hutool.core.clone.CloneSupport;
import cn.hutool.core.clone.Cloneable;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.convert.ConverterRegistry;
import cn.hutool.core.util.CharsetUtil;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * @author chenxin
 * @date 2019/7/12 11:27
 */
public class FirstTest {
    public static void main(String[] args) throws UnsupportedEncodingException {
        int[] a = {2,3,3535,44,22};
        String[] b = Convert.toStrArray(a);
        System.out.println(Arrays.asList(b));
        int[] c = Convert.convert(int[].class, b);
        ConverterRegistry  converter = new ConverterRegistry();

        String dbc = "123456";
        String sbc = Convert.toSBC(dbc);
        System.out.println(sbc);
        String dbc2 = Convert.toDBC(sbc);
        System.out.println(dbc2);
        //12233,123344441222 12222 5855４１４４４４４４４４４４５５５５５４４４４444444444444444
        String str  = "我是一个小小的可爱的字符串";

//结果："e68891e698afe4b880e4b8aae5b08fe5b08fe79a84e58fafe788b1e79a84e5ad97e7aca6e4b8b2"
        String hex = Convert.toHex(str, CharsetUtil.CHARSET_UTF_8);
        System.out.println(hex);
        System.out.println(Convert.toHex(hex, CharsetUtil.CHARSET_UTF_8));

        System.out.println(Convert.hexToStr(hex, CharsetUtil.CHARSET_UTF_8));




//结果为："\\u6211\\u662f\\u4e00\\u4e2a\\u5c0f\\u5c0f\\u7684\\u53ef\\u7231\\u7684\\u5b57\\u7b26\\u4e32"
        String unicode = Convert.strToUnicode(str);
        System.out.println(unicode);

//结果为："我是一个小小的可爱的字符串"
        String raw = Convert.unicodeToStr(unicode);
        System.out.println("中国： "+"中国".getBytes());
        byte[] bytes = "中国".getBytes("unicode");
        for (byte b1 : bytes){
            System.out.println(Integer.toHexString(b1 &0xff));
        }

        System.out.println();

        stringToUnicode("中国");
        char c1 = (char)0x56fd;
        System.out.println(c1);
        String string= "\u6211\u662f\u4e00\u4e2a\u5c0f\u5c0f\u7684\u53ef\u7231\u7684\u5b57\u7b26\u4e32";

        System.out.println(string);


        String s = "我是一个小小的可爱的字符串";

//结果："e68891e698afe4b880e4b8aae5b08fe5b08fe79a84e58fafe788b1e79a84e5ad97e7aca6e4b8b2"
        String s1 = Convert.toHex(s, CharsetUtil.CHARSET_UTF_8);
        System.out.println(s1);
        String s2 = Convert.toHex(s,CharsetUtil.CHARSET_ISO_8859_1);
        System.out.println(s2);

        String s3 = Convert.hexToStr(s2,CharsetUtil.CHARSET_ISO_8859_1);
        System.out.println(s3);

        String s4 = "我是一个小小的可爱的字符串";

//结果为："\\u6211\\u662f\\u4e00\\u4e2a\\u5c0f\\u5c0f\\u7684\\u53ef\\u7231\\u7684\\u5b57\\u7b26\\u4e32"
        String s5 = Convert.strToUnicode(s4);
        System.out.println(s5);

//结果为："我是一个小小的可爱的字符串"
        String s6 = Convert.unicodeToStr(string);
        System.out.println(s6);
        System.out.println(string);



        String aa = "我不是乱码";
//转换后result为乱码
        String result = Convert.convertCharset(aa, CharsetUtil.UTF_8, CharsetUtil.GBK);
        System.out.println(result);
        String s7 = Convert.convertCharset(result, CharsetUtil.GBK, "UTF-8");
        System.out.println(s7);

//        Assert.isTrue(s7.equals(aa));

        double d = 675565897.32;

//结果为："陆万柒仟伍佰伍拾陆元叁角贰分"
        String digitUppercase = Convert.digitToChinese(d);

        //去包装
        Class<?> wrapClass = Integer.class;

//结果为：int.class
        Class<?> unWraped = Convert.unWrap(wrapClass);

//包装
        Class<?> primitiveClass = long.class;

//结果为：Long.class
        Class<?> wraped = Convert.wrap(primitiveClass);


    }

    public static String stringToUnicode(String str) {
        String strTemp = "";
        if (str!=null) {
            for(char c:str.toCharArray()){
                strTemp+="\\u"+Integer.toHexString((int)c);
            }
        }
        System.out.println(strTemp);
        return strTemp;
    }
    private static class Cat implements Cloneable<Cat>{

        @Override
        public Cat clone() {
            try{
                return (Cat) super.clone();
            } catch (CloneNotSupportedException e) {
                throw  new CloneRuntimeException(e);
            }
        }
    }

    private static class Dog extends CloneSupport<Dog>{
        private String name = "wangwang";
        private int age = 3;
    }




















}
