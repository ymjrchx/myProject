package com.dgg.flink;

import com.dgg.Bean.DggOrfInfo;
import com.dgg.config.RedisConfig;
import com.dgg.util.FlinkUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumerBase;


/**
 * @Classname FlinkDemo6
 * @Description 侧输出
 * @Date 2019/5/9 14:58
 * @Created by dgg-yanshun
 */
public class FlinkDemo6  {


    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = FlinkUtil.getStreamExecutionEnvironment();
        FlinkKafkaConsumer011<String> consumer = new FlinkKafkaConsumer011<>("ibwb_db_iboss_orf",
                new SimpleStringSchema(),
                FlinkUtil.getProperties());
        FlinkKafkaConsumerBase<String> base = consumer.setStartFromEarliest();
        SingleOutputStreamOperator<DggOrfInfo> process = env.addSource(base)
                .filter((FilterFunction<String>) line -> line.matches("\\{.+\\}")).process(new DggSplitterInfo());
        DataStream<DggOrfInfo> sideOutput = process.getSideOutput(DggSplitterInfo.rejectedOrfTag);
//                .filter((FilterFunction<DggOrfInfo>) dggOrfInfo -> StringUtils.isNotEmpty(dggOrfInfo.getDataDate()));
//        process.print();
        sideOutput.print();
        env.execute();
    }
}
