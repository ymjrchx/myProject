package com.example.demo.controller;

import com.example.demo.callback.CallBackSender;
import com.example.demo.hello.HelloReceiver1;
import com.example.demo.hello.HelloSender1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenxin
 * @date 2019/4/3 12:52
 */
@RestController
@RequestMapping("/rabbit")
public class RabbitController {
    @Autowired
    private HelloSender1 helloSender1;
    @Autowired
    private HelloReceiver1 helloReceiver1;

    @Autowired
    private CallBackSender callBackSender;
    @PostMapping("/hello")
    public void hello(){
        helloSender1.send("ddddd");
    }
    @PostMapping("/callback")
    public void callback(){
        callBackSender.send();
    }





    @PostMapping("/oneToMany")
    public void oneToMany(){
        for (int i = 0; i < 10 ; i++) {
       helloSender1.send("hellomsg: "+i);
        }
    }



}
