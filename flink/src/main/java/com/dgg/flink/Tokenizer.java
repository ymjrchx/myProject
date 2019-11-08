package com.dgg.flink;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;

/**
 * @Classname Tokenizer
 * @Description TODO
 * @Date 2019/5/8 19:12
 * @Created by dgg-yanshun
 */
public class Tokenizer extends ProcessFunction<String, Tuple2<String, Integer>> {

    public static final OutputTag<String> rejectedWordsTag = new OutputTag<String>("rejected") {};

    @Override
    public void processElement(String value, Context ctx, Collector<Tuple2<String, Integer>> out) throws Exception {
        String[] tokens = value.toLowerCase().split("\\W+");
        for (String token : tokens) {
            if (token.length() > 5) {
                ctx.output(rejectedWordsTag, token);
            } else if (token.length() > 0) {
                out.collect(new Tuple2<>(token, 1));
            }
        }
    }
}

