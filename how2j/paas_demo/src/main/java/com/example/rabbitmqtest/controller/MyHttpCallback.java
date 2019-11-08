package com.example.rabbitmqtest.controller;

import net.dgg.utils.abs.DggHttpCallback;
import org.apache.http.HttpResponse;

/**
 * @Author: dgg-linhongda
 * @Date: 2019/10/18 0018
 * @Description:
 */
public class MyHttpCallback implements DggHttpCallback {


    @Override
    public void handlerCompleted(String s) {
        System.out.println(s);
    }

    @Override
    public void failed(Exception ex) {
        ex.printStackTrace();
    }

    @Override
    public void cancelled() {

    }
}
