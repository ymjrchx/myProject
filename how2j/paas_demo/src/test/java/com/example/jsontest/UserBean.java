package com.example.jsontest;

/**
 * @Author: dgg-linhongda
 * @Date: 2019/6/28 0028
 * @Description:
 */
public class UserBean {
    private String name;
    private Integer age;

    public UserBean() {}

    public UserBean(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserBeanTest{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
