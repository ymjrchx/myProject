package com.example.demo.callback;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author chenxin
 * @date 2019/4/3 16:36
 */
@Component
public class CallBackSender implements RabbitTemplate.ConfirmCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(){
        rabbitTemplate.setConfirmCallback(this);
        String msg = "callbackSender: i am callback sender";
        System.out.println(msg);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        System.out.println("callbackSender UUID : "+ correlationData.getId());
        rabbitTemplate.convertAndSend("exchange","topic.message",msg,correlationData);
    }





    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {

        System.out.println("callback confirm :" + correlationData.getId());
        System.out.println(rabbitTemplate);


    }
}
