package com.example.rabbitmqtest.controller;

import net.dgg.utils.http.DggAsyncHttpUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: dgg-linhongda
 * @Date: 2019/8/16 0016
 * @Description:
 */
@RestController
@RequestMapping("/http")
public class MyHttpController {

    @GetMapping("/httpSyncTest")
    public String httpSyncTest(){
        return getData();
    }

    @GetMapping("/httpAsyncTest")
    public String httpAsyncTest(){
        DggAsyncHttpUtil.doGet("http://172.16.22.150:6001/http/httpSyncTest", new MyHttpCallback());
        return "httpAsyncTest";
    }


    private String getData(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello, World!!!";
    }

}
