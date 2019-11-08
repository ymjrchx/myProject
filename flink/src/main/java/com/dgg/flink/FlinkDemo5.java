package com.dgg.flink;

import com.dgg.util.FlinkUtil;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @Classname FlinkDemo5
 * @Description 侧输出
 * @Date 2019/5/8 19:19
 * @Created by dgg-yanshun
 */
public class FlinkDemo5 {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = FlinkUtil.getStreamExecutionEnvironment();
        DataStream<String> text = env.fromElements(WordCountData.WORDS);
        SingleOutputStreamOperator<Tuple2<String, Integer>> process = text.keyBy(new KeySelector<String, Integer>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Integer getKey(String value) throws Exception {
                return 0;
            }
        }).process(new Tokenizer());
        DataStream<String> sideOutput = process.getSideOutput(Tokenizer.rejectedWordsTag);

        process.print();
        sideOutput.print();
        env.execute();
    }
}
