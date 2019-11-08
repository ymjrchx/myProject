package com.dgg.test;


import com.alibaba.fastjson.JSONObject;


public class JsonTest {
    public static void main(String[] args) {
        String json1 = "{'loginName':'1','realName':'JAVAEE-1703','time':'2019-04-24 16:51:24','age':null}";
//        UserInfo userInfo = JSON.parseObject(json1, UserInfo.class);
        JSONObject jsonObject = JSONObject.parseObject(json1);
        System.out.println(jsonObject.getString("loginName"));
        System.out.println(jsonObject.getDate("time"));
        System.out.println(jsonObject.getLong("age"));

        JSONObject jsonObject1 = new JSONObject();
        System.out.println(jsonObject1.isEmpty());
    }
}
