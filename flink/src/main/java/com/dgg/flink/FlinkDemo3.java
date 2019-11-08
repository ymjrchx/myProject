package com.dgg.flink;

import com.alibaba.fastjson.JSONObject;
import com.dgg.util.FlinkUtil;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumerBase;

/**
 * @Classname FlinkDemo3
 * @Description TODO
 * @Date 2019/4/29 15:53
 * @Created by dgg-yanshun
 */
public class FlinkDemo3 {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = FlinkUtil.getStreamExecutionEnvironment();
        FlinkKafkaConsumer011<String> consumer = new FlinkKafkaConsumer011<>("ibwb_db_iboss_vis",
                new SimpleStringSchema(),
                FlinkUtil.getProperties());
        FlinkKafkaConsumerBase<String> base = consumer.setStartFromEarliest();
        env.addSource(base).map((MapFunction<String, String>) line -> {
            JSONObject jsonObject = JSONObject.parseObject(line);
            String eventType = jsonObject.getString("eventType");
            String status = jsonObject.getString("status");
            return eventType + ":" + status;
        }).print();
        env.execute("测试");
    }
}
