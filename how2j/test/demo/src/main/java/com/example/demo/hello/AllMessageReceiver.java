package com.example.demo.hello;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author chenxin
 * @date 2019/4/3 15:58
 */
@RabbitListener(queues = {"allMessage","net.dgg.resource.center.queue.RabbitQueue.bus_allot_record"})
@Component
public class AllMessageReceiver {

    @RabbitHandler
    public void receiver(Channel channel, Message message, String date) {

        System.out.println("allMessage : " + date);
    }

}
