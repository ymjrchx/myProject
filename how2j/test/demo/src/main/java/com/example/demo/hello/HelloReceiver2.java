package com.example.demo.hello;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author chenxin
 * @date 2019/4/3 16:12
 */
@Component
@RabbitListener(queues = {"helloQueue1","allMessage","topic.message"})
public class HelloReceiver2 {

    @RabbitHandler
    public void receriver2(String hello){
        System.out.println("receriver2 : "+hello);
    }
}
