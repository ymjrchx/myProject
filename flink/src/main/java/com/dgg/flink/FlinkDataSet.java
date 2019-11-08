package com.dgg.flink;

import com.dgg.util.FlinkUtil;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/**
 * @Classname FlinkDataSet
 * @Description TODO
 * @Date 2019/8/8 15:25
 * @Created by dgg-yanshun
 */
public class FlinkDataSet {

    public static void main(String[] args) {
        StreamExecutionEnvironment env = FlinkUtil.getStreamExecutionEnvironment();
        DataStreamSource<String> dataStreamSource = env.fromElements("1111", "222", "3333");
    }
}
