package com.example.demo.iptest.entity;

import lombok.Data;

@Data
public class ConsultMessage  {
    private String id;
    private String tel;
    private String tel_city;

    public ConsultMessage(String id, String tel, String tel_city) {
        this.id = id;
        this.tel = tel;
        this.tel_city = tel_city;
    }

    public ConsultMessage() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTel_city() {
        return tel_city;
    }

    public void setTel_city(String tel_city) {
        this.tel_city = tel_city;
    }
}