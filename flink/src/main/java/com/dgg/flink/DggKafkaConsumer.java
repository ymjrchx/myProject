package com.dgg.flink;

import com.dgg.config.DggDateUtil;
import com.dgg.config.RedisConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Properties;

/**
 * @Classname DggKafkaConsumer
 * @Description TODO
 * @Date 2019/6/20 9:28
 * @Created by dgg-yanshun
 */
public class DggKafkaConsumer {
    private static JedisCluster jedisCluster;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static{
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(50);//最大连接数
        jedisPoolConfig.setMaxIdle(10);//最大空闲连接数
        jedisPoolConfig.setMaxWaitMillis(-1);//获取连接时的最大等待毫秒数，若超时则抛异常。-1代表不确定的毫秒数
        jedisPoolConfig.setTestOnBorrow(true);//获取连接时检测其有效性
        jedisCluster = new JedisCluster(RedisConfig.getRedisClusterConfig(),15000,100,
                jedisPoolConfig);//第二个参数：超时时间     第三个参数：最大尝试重连次数
    }

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "172.16.0.217:9092,172.16.0.218:9092,172.16.0.219:9092");
        props.put("group.id", "CountryCounter");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("auto.offset.reset", "latest");
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("ibwb_manager_info","ibwb_today_dynamic"));
        String date = DggDateUtil.getCurrentDateString();
        while(true){
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                String value = record.value();
                System.out.println(value);
//                String[] split = value.split(",");
//                String loginName = split[0];
//                String mark = split[1];
//                System.out.println("从redis获取的数据：" + jedisCluster.get("ibwb_todo_" + loginName + "_" + date));
//                System.out.println("收到的时间：" + sdf.format(new Date()));
//                System.out.println("mark:" + mark);
            }
        }
    }
}
