package cn.how2j.rabbitmq.direct;

import cn.how2j.rabbitmq.RabbitMQUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TestDriectProducer {
    public final static String QUEUE_NAME="direct_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        RabbitMQUtil.checkServer();
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        for (int i = 0; i < 100 ; i++) {
            String message = "direct 消息 "+i;
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes("UTF-8"));
            System.out.println("发送消息: " + message);

        }
        channel.close();
        connection.close();

    }
}


















































