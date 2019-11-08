package cn.how2j.rabbitmq.topic;

import cn.how2j.rabbitmq.RabbitMQUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TestTopicProducer {
    public final static String EXCHANGE_NAME="topics_exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        RabbitMQUtil.checkServer();
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"topic");
        String[] routing_keys = new String[] { "usa.news", "usa.weather",
                "europe.news", "europe.weather" };
        String[] messages = new String[] { "美国新闻", "美国天气",
                "欧洲新闻", "欧洲天气" };
        for (int i = 0; i < routing_keys.length; i++) {
            String routingKey = routing_keys[i];
            String msg = messages[i];
            channel.basicPublish(EXCHANGE_NAME,routingKey,null,msg.getBytes());
            System.out.printf("发送消息到路由：%s, 内容是: %s%n ", routingKey,msg);


        }
        channel.close();
        connection.close();


    }









































}
