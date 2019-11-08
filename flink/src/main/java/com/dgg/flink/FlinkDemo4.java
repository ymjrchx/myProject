package com.dgg.flink;

import com.dgg.util.FlinkUtil;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumerBase;

/**
 * @Classname FlinkDemo4
 * @Description TODO
 * @Date 2019/5/8 16:14
 * @Created by dgg-yanshun
 */
public class FlinkDemo4 {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = FlinkUtil.getStreamExecutionEnvironment();
        FlinkKafkaConsumer011<String> consumer = new FlinkKafkaConsumer011<>("ibwb_db_iboss_orf",
                new SimpleStringSchema(),
                FlinkUtil.getProperties());
        FlinkKafkaConsumerBase<String> base = consumer.setStartFromEarliest();
        SingleOutputStreamOperator<String> filter = env.addSource(base);
//                .filter((FilterFunction<String>) line -> line.contains("80006200002632") || line.contains("38404"));
        filter.writeAsText("E:\\test4");
        filter.print();
        env.execute();


    }
}
