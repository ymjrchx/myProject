package cn.how2j.rabbitmq.direct;

import cn.how2j.rabbitmq.RabbitMQUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TestDriectCustomer {
    private final static String QUEUE_NAME = "direct_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        String name ="";
        RabbitMQUtil.checkServer();
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,true,null);
        System.out.println(QUEUE_NAME +"等待接收消息");
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(QUEUE_NAME + " 接收到消息 '" + message + "'");
            }
        };
        channel.basicConsume(QUEUE_NAME,true,consumer);

    }




}


































































