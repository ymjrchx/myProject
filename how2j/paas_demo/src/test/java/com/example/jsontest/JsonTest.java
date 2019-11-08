package com.example.jsontest;

import net.dgg.utils.json.DggJsonUtil;
import org.junit.Test;

import java.util.Date;

/**
 * @Author: dgg-linhongda
 * @Date: 2019/10/23 0023
 * @Description:
 */
public class JsonTest {

    @Test
    public void objectToJsonStringTest(){
        UserBean userBean01 = new UserBean("张三", 22);
        String result01 = DggJsonUtil.objectToJson(userBean01);
        System.out.printf("全属性有值输出结果为: %s", result01);
        System.out.println();
        System.out.println("==============================================");

        UserBean userBean02 = new UserBean();
        userBean02.setAge(23);

        String result02 = DggJsonUtil.objectToJson(userBean02);
        System.out.printf("未格式化NULL不填充结果为: %s", result02);
        System.out.println();
        System.out.println("==============================================");

        String result03 = DggJsonUtil.objectToJson(userBean02, false, true);
        System.out.printf("未格式化NULL填充结果为: %s", result03);
        System.out.println();
        System.out.println("==============================================");

        String result04 = DggJsonUtil.objectToJson(userBean02, true, true);
        System.out.printf("格式化NULL填充结果为: %s", result04);
        System.out.println();
        System.out.println("==============================================");
    }

    @Test
    public void jsonFieldTest(){

        UserBean userBean = new UserBean("张三", 22);
        String userBeanString = DggJsonUtil.objectToJson(userBean);

        JsonFiled jsonFiled = new JsonFiled();
        jsonFiled.setId(111);
        jsonFiled.setName("test001");
        jsonFiled.setCurrentDate(new Date());
        jsonFiled.setNotSerialize("我不会被序列化");
        jsonFiled.setNotDeserialize("我不会被反序列化");
        jsonFiled.setJsonDirect01(userBeanString);
        jsonFiled.setJsonDirect02(userBeanString);

        String result = DggJsonUtil.objectToJson(jsonFiled, true);
        System.out.printf("序列化后的结果为: %s", result);
        System.out.println();

        JsonFiled deResult = DggJsonUtil.jsonStringToObject(result, JsonFiled.class);
        System.out.printf("反序列化后的结果为: %s", deResult);
    }

}
