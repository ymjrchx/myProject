package com.example.demo.hello;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author chenxin
 * @date 2019/4/3 12:49
 */
@Component
@RabbitListener(queues = "helloQueue")
public class HelloReceiver1 {
    @RabbitHandler
    public void process(String hello){
        System.out.println("Receiver1: "+hello);
    }
}
