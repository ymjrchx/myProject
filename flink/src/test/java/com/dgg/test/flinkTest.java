package com.dgg.test;

import com.dgg.common.Constant;
import com.dgg.util.FlinkUtil;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumerBase;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer011;

/**
 * @Classname flinkTest
 * @Description TODO
 * @Date 2019/4/23 8:23
 * @Created by dgg-yanshun
 */
public class flinkTest {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = FlinkUtil.getStreamExecutionEnvironment();
        FlinkKafkaConsumer011<String> consumer = new FlinkKafkaConsumer011<>(Constant.GROUP_ID_1,
                new SimpleStringSchema(),
                FlinkUtil.getProperties());
        FlinkKafkaConsumerBase<String> base1 = consumer.setStartFromEarliest();//从最早开始消费
        SingleOutputStreamOperator<String> map = env.addSource(base1).map((MapFunction<String, String>) line -> line).returns(Types.STRING);
//        map.addSink(
//                new FlinkKafkaProducer011<>(Constant.KAFKA_SERVERS,"phoneTest",new SimpleStringSchema()))
//                .name("flink-connectors-kafka").setParallelism(5);
        map.print();
//        map.writeAsText("E://test");
        env.execute();
    }
}
