package cn.how2j.rabbitmq.fanout;

import cn.how2j.rabbitmq.RabbitMQUtil;
import cn.hutool.core.util.RandomUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TestCustomer {
    public final static String EXCHANGE_NAME="fanout_exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        String queueName = "consumer-061ce";
        RabbitMQUtil.checkServer();
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
       //   channel.queueDeclare(queueName,true,false,false,null);
        channel.queueBind(queueName,EXCHANGE_NAME,"123");
        System.out.println(queueName + "等待接收消息");
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body)
                    throws IOException{
                String message = new String(body,"UTF-8");
                System.out.println(queueName +"接收到消息 '"+message+"'");

            }
        };
        channel.basicConsume(queueName,true,consumer);
    }











































}
