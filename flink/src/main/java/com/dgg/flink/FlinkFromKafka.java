package com.dgg.flink;

import com.alibaba.fastjson.JSONObject;
import com.dgg.Bean.DggOrfInfo;
import com.dgg.Bean.DggPhoneInfo;
import com.dgg.common.Constant;
import com.dgg.util.FlinkUtil;
import com.dgg.util.MysqlSink1;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.api.java.tuple.Tuple4;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumerBase;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.table.api.java.StreamTableEnvironment;


public class FlinkFromKafka {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = FlinkUtil.getStreamExecutionEnvironment();


//        DataStream<String> kafkaStream = env.addSource(base).map((MapFunction<String, String>) line -> line).returns(Types.STRING);
//        DataStream<String> kafkaStream1 = env.addSource(base1).map((MapFunction<String, String>) line -> line).returns(Types.STRING);
//        kafkaStream.writeAsText("E:\\test");
//        kafkaStream.print();

//        StreamTableEnvironment tEnv = TableEnvironment.getTableEnvironment(env);
//        tEnv.registerDataStream("test",kafkaStream,"dbName,call_30s_counts,dataDate");
//        Table result = tEnv.sqlQuery("select count(*) total,dbName from test group by dbName");
//        tEnv.toRetractStream(result, DggPhoneInfo.class).filter((FilterFunction<Tuple2<Boolean, DggPhoneInfo>>) booleanDggPhoneInfoTuple2 -> booleanDggPhoneInfoTuple2.f0)
//                .map((MapFunction<Tuple2<Boolean, DggPhoneInfo>, DggPhoneInfo>) booleanDggPhoneInfoTuple2 -> booleanDggPhoneInfoTuple2.f1).print();



//        kafkaStream1.print();
//        kafkaStream.addSink(new MysqlSink1());
        env.execute("flink开始执行");
    }


    public static void testVis(StreamExecutionEnvironment env){
        FlinkKafkaConsumer011<String> consumer1 = new FlinkKafkaConsumer011<>("ibwb_db_iboss_vis",
                new SimpleStringSchema(),
                FlinkUtil.getProperties());
        FlinkKafkaConsumerBase<String> base = consumer1.setStartFromEarliest();//从最早开始消费
        SingleOutputStreamOperator<Long> kafkaStream = env.addSource(base).filter((FilterFunction<String>) line -> line.matches("\\{.+\\}")).map(new Splitter());
        kafkaStream.writeAsText("E:\\test4");
    }
    private static class Splitter implements MapFunction<String,Long> {
        @Override
        public Long map(String line) {
            JSONObject jsonObject = JSONObject.parseObject(line);
//            Long offset = jsonObject.getLong("offset");
            Long timestamp = jsonObject.getLong("timestamp");
            return timestamp;
        }
    }

    public static void testOrf(StreamExecutionEnvironment env){
        FlinkKafkaConsumer011<String> consumer = new FlinkKafkaConsumer011<>("ibwb_db_iboss_orf",
                new SimpleStringSchema(),
                FlinkUtil.getProperties());
        FlinkKafkaConsumerBase<String> base = consumer.setStartFromEarliest();
        SingleOutputStreamOperator<DggOrfInfo> process = env.addSource(base)
                .filter((FilterFunction<String>) line -> line.matches("\\{.+\\}")).process(new DggSplitterInfo());
        DataStream<DggOrfInfo> sideOutput = process.getSideOutput(DggSplitterInfo.rejectedOrfTag);


    }
}
