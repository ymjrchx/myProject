package com.dgg.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dgg.Bean.DggBusinessSocreDetail;
import com.dgg.common.Constant;
import com.dgg.config.DggDateUtil;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @Classname Test
 * @Description TODO
 * @Date 2019/4/26 13:37
 * @Created by dgg-yanshun
 */
public class Test {

    private static Properties props = new Properties();

    static{
        props.put("bootstrap.servers", Constant.KAFKA_SERVERS);
        props.put("acks", "1");
        props.put("retries", 0);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    }

    public static void sendLoginNameToKafka(String loginName) throws InterruptedException {
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        for (int i = 0; i < 20; i++) {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("tableName","bus_business_emp");
            jsonObj.put("will_drop_time", DggDateUtil.getCurrentDateTime());
            jsonObj.put("id",i);
            jsonObj.put("follower_id",7788935428928626688L);
            jsonObj.put("customer_no",i+"test");
            jsonObj.put("create_time",DggDateUtil.getCurrentDateTime());
            jsonObj.put("business_status","BUS_STA_2_1");
            jsonObj.put("customer_phone","F68EFD4BB355E09B55D1BC096CF5DDDE");
            jsonObj.put("eventType","DELETE");
            jsonObj.put("no",i + "no");
            jsonObj.put("follower_organization_id",8009L);
            jsonObj.put("update_time",DggDateUtil.getCurrentDateTime());
            jsonObj.put("timestamp",System.currentTimeMillis());
            jsonObj.put("next_follow_time","");
            jsonObj.put("customer_name","女士");
            jsonObj.put("customer_id",i + 200);
            producer.send(new ProducerRecord<>("ibwb_update_login_name",jsonObj.toString()));
            System.out.println("成功发送：" + i);
            Thread.sleep(1000);
        }
    }


    public static void main(String[] args) throws InterruptedException {


    }
}
