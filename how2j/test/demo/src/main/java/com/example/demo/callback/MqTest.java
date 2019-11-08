package com.example.demo.callback;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Envelope;
import net.dgg.framework.tac.mq.rabbitmq.queue.normal.DggAbstractNormalConsumer;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author chenxin
 * @date 2019/5/25 6:56
 */
@Component
public class MqTest extends DggAbstractNormalConsumer<String> {

    public MqTest(){
        this.queueName = "net.dgg.resource.center.queue.RabbitQueue.bus_allot_record";
    }


    @Override
    protected void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, String data) throws IOException {
        System.out.println("11111111111111");
    }
}
