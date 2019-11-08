package com.dgg.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dgg.common.Constant;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @Classname KafkaUtils
 * @Description TODO
 * @Date 2019/8/20 15:21
 * @Created by dgg-yanshun
 */
public class KafkaUtils {


    //flink 读取kafka写入mysql exactly-once 的topic
    private static final String topic_ExactlyOnce = "mysql-exactly-Once";

    public static void writeToKafka2() throws InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", Constant.KAFKA_SERVERS);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new org.apache.kafka.clients.producer.KafkaProducer<>(props);
        int i = 1;
        try {
            while(true){
                ProducerRecord record = new ProducerRecord<String, String>(topic_ExactlyOnce, String.valueOf(i++));
                producer.send(record);
                System.out.println(i);
                Thread.sleep(2000);
            }
        }catch (Exception e){

        }
        producer.flush();
    }

    public static void main(String[] args) throws InterruptedException {
        writeToKafka2();
    }

}
