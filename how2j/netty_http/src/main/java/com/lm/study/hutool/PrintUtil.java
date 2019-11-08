package com.lm.study.hutool;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author chenxin
 * @date 2019/7/15 14:34
 */
public class PrintUtil {
    public static void  p(Object o){
        System.out.println(o.getClass());
        if(o.getClass().isArray()){
            return;
        }
        System.out.print(o);
    }

    public static void pl(Object o){
        System.out.println(o);
    }
}
