package com.example.jsontest;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @Author: dgg-linhongda
 * @Date: 2019/6/28 0028
 * @Description:
 */
public class JsonFiled {

    @JSONField(name = "ID", ordinal = 12)
    private Integer id;

    @JSONField(ordinal = 11)
    private String name;
    @JSONField(format = "yyyy-MM-dd")
    private Date currentDate;

    @JSONField(serialize=false)
    private String notSerialize;

    @JSONField(deserialize=false)
    private String notDeserialize;

    private String jsonDirect01;

    /**
     * 直接输出不转义
     */
    @JSONField(jsonDirect = true)
    private String jsonDirect02;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public String getNotSerialize() {
        return notSerialize;
    }

    public void setNotSerialize(String notSerialize) {
        this.notSerialize = notSerialize;
    }

    public String getNotDeserialize() {
        return notDeserialize;
    }

    public void setNotDeserialize(String notDeserialize) {
        this.notDeserialize = notDeserialize;
    }

    public String getJsonDirect01() {
        return jsonDirect01;
    }

    public void setJsonDirect01(String jsonDirect01) {
        this.jsonDirect01 = jsonDirect01;
    }

    public String getJsonDirect02() {
        return jsonDirect02;
    }

    public void setJsonDirect02(String jsonDirect02) {
        this.jsonDirect02 = jsonDirect02;
    }

    @Override
    public String toString() {
        return "JsonFiledTest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", currentDate=" + currentDate +
                ", notSerialize='" + notSerialize + '\'' +
                ", notDeserialize='" + notDeserialize + '\'' +
                ", jsonDirect01='" + jsonDirect01 + '\'' +
                ", jsonDirect02='" + jsonDirect02 + '\'' +
                '}';
    }
}
