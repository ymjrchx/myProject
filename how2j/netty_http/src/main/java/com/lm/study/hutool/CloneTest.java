package com.lm.study.hutool;

import cn.hutool.core.clone.CloneRuntimeException;
import cn.hutool.core.clone.CloneSupport;
import cn.hutool.core.clone.Cloneable;
import cn.hutool.core.util.ObjectUtil;
import org.junit.Test;

import java.io.Serializable;

import static com.lm.study.hutool.PrintUtil.*;

/**
 * @author chenxin
 * @date 2019/7/15 14:29
 */
public class CloneTest {
    @Test
    public void Clone(){
        Cat cat = new Cat();
        Cat cat1 = cat.clone();
        pl(cat);
        pl(cat1);
        Dog dog = new Dog();
        Dog dog1 = dog.clone();
        pl(dog);
        pl(dog1);

        Pig pig = new Pig();
        Pig pig1 = ObjectUtil.cloneByStream(pig);
        pl(pig1);
        Cat clone = ObjectUtil.clone(cat);
        pl(clone);
        Cat cat2 = ObjectUtil.cloneIfPossible(cat);
        pl(cat2);


    }

    private static class Cat implements Cloneable<Cat>,Serializable{
        private static String name = "miaomiao";
        private int age = 2;

        @Override
        public Cat clone() {
            try {
                return (Cat) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new CloneRuntimeException(e);
            }
        }
    }

    private static class Dog extends CloneSupport<Dog>{
        private String name = "wangwang";
        private int age = 2;

    }

    private static class Pig implements Serializable{
        private String name = "zhuzhu";
        private int age = 3;
        private Cat cat = new Cat();
    }











}
