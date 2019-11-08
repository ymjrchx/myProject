package com.example.demo.iptest.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author chenxin
 * @date 2019/4/8 12:39
 */
@Data
public class ConsultIPRegion {
    private long id;
    private String ip;
    private int districts_num;
    private String ip_country;
    private String ip_city;
    private String ip_province;
    private String tel_city;
    private String page_city;
    private String place;
    private String consult_message_id;
    private Date addTime;
    private String front_ip;
}
