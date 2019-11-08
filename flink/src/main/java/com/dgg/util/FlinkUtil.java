package com.dgg.util;

import com.dgg.common.Constant;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class FlinkUtil {


    public static StreamExecutionEnvironment getStreamExecutionEnvironment(){
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.enableCheckpointing(5000);
        env.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);//这个是默认的
        env.getCheckpointConfig().setCheckpointTimeout(60000);
        env.getCheckpointConfig().setMaxConcurrentCheckpoints(1);
        env.setRestartStrategy(RestartStrategies.failureRateRestart(3, // max failures per unit
                Time.of(5, TimeUnit.MINUTES), //time interval for measuring failure rate
                Time.of(10, TimeUnit.SECONDS)));// delay
        return env;
    }

    public static Properties getProperties(){
        Properties pro = new Properties();
        pro.setProperty("bootstrap.servers", Constant.KAFKA_SERVERS);
        pro.setProperty("group.id","yanshun");
//        pro.setProperty("enable.auto.commit", "true");待定
////        pro.setProperty("auto.commit.interval.ms", "1000");待定
        pro.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        pro.setProperty("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        return pro;
    }
}
