package com.dgg.flink;

import com.dgg.util.FlinkUtil;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumerBase;

/**
 * @Classname FlinkDemo2
 * @Description 测试电话数据的准确性
 * @Date 2019/5/10 14:07
 * @Created by dgg-yanshun
 */
public class FlinkDemo2 {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = FlinkUtil.getStreamExecutionEnvironment();
        FlinkKafkaConsumer011<String> consumer = new FlinkKafkaConsumer011<>("ibwb_update_info",
                new SimpleStringSchema(),
                FlinkUtil.getProperties());
        FlinkKafkaConsumerBase<String> base = consumer.setStartFromEarliest();
        env.addSource(base).print();
//        env.addSource(base).writeAsText("E:\\test4");
        env.execute();
    }

}
