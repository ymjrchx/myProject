package com.dgg.flink;

import com.alibaba.fastjson.JSONObject;
import com.dgg.common.Constant;
import com.dgg.config.DggDateUtil;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @Classname FlinkSinkToKafka
 * @Description TODO
 * @Date 2019/4/23 14:55
 * @Created by dgg-yanshun
 */
public class FlinkSinkToKafka {
    public static void main(String[] args) throws InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", Constant.KAFKA_SERVERS);
        props.put("acks", "1");
        props.put("batch.size", 16384);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        producer.send(new ProducerRecord<>("ibwb_db_iboss_bus","{\"no\":\"ZY2019041001834\",\"distribution_time\":\"2019-07-18 11:35:19\",\"opportunitytype_code\":\"BUS_OPP_4\",\"dbName\":\"db_iboss_bus\",\"business_operate\":\"BUS_OUT_1002\",\"table_name\":\"bus_business_emp\",\"last_lose_time\":\"\",\"pre_follower\":\"28399\",\"follower_organization_id\":\"236\",\"update_time\":\"2019-07-18 11:35:19\",\"will_drop_time\":\"2019-07-19 12:00:00\",\"last_order_back_time\":\"\",\"id\":\"7804133674145734656\",\"vip\":\"0\",\"follower_id\":\"7835051272569405440\",\"customer_way\":\"MB\",\"customer_no\":\"KH201805211339\",\"visit_status\":\"\",\"offset\":743994086,\"create_time\":\"2019-04-10 09:44:35\",\"business_status\":\"BUS_STA_2_1\",\"next_follow_time\":\"\",\"last_lose_type\":\"org\",\"customer_phone\":\"CEC135A490B7D24F3A17EF85B376BD4B\",\"eventType\":\"UPDATE\",\"origin_type\":\"ORIGIN_TYPE_6\",\"way_code\":\"BUS_WAY_CODE_3\",\"widely_type\":\"0\",\"last_follow_time\":\"2019-07-18 11:35:19\",\"business_organization_id\":\"161\",\"add_type_code\":\"BUS_ADD_TYPE_CODE_4\",\"customer_name\":\"王旺\",\"customer_id\":\"7686832160648867840\",\"type_code\":\"BUS_YT_DK_XYDK_1\"}"));
        System.out.println("写入成功");
//        //造数据
//        for (int i = 0; i < 20; i++) {
//            JSONObject jsonObject = new JSONObject();
//            if(i%2 == 0){
//
//            }
//            producer.send(new ProducerRecord<>("ibwb_update_bus","20190124_emp"));
//            producer.send(new ProducerRecord<>("ibwb_update_phone_customer","20190124_phone"));
//            System.out.println("成功发送：" + i);
//            Thread.sleep(6000);
//        }
    }
}
