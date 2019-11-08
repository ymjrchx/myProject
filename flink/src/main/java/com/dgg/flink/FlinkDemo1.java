package com.dgg.flink;

import com.dgg.util.FlinkUtil;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.table.api.java.StreamTableEnvironment;
import org.apache.flink.types.Row;

import java.util.List;
import java.util.Map;

/**
 * @Classname FlinkDemo1
 * @Description TODO
 * @Date 2019/4/26 16:50
 * @Created by dgg-yanshun
 */
public class FlinkDemo1 {

    public static Map<String,String> testMap = null;

    //测试过  没问题  可以改了
//    public static void main(String[] args) throws Exception {
//        StreamExecutionEnvironment env = FlinkUtil.getStreamExecutionEnvironment();
//        StreamTableEnvironment tEnv = TableEnvironment.getTableEnvironment(env);//flink table 注册类
//        FlinkKafkaConsumer011<String> consumer = new FlinkKafkaConsumer011<>("test1",
//                new SimpleStringSchema(),
//                FlinkUtil.getProperties());
//        SingleOutputStreamOperator<Tuple2<String, String>> map = env.addSource(consumer).map(new MapFunction<String, Tuple2<String, String>>() {
//            Tuple2<String, String> result = new Tuple2<>();
//            @Override
//            public Tuple2<String, String> map(String line) {
//                String[] split = line.split(",");
//                result.f0 = split[0];
//                result.f1 = split[1];
//                return result;
//            }
//        });
//        tEnv.registerDataStream("phone",map,"id,name");
//        DataStreamSource<Tuple3<String, String, String>> source = env.addSource(new JDBCReader());
//        source.print();
//        tEnv.registerDataStream("test",source,"id,name,testtime");
//        Table table = tEnv.sqlQuery("select t1.* from test t1,phone t2 where t1.id = t2.id");
//        tEnv.toRetractStream(table,Row.class).print();
//        env.execute();
//    }、


    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = FlinkUtil.getStreamExecutionEnvironment();
        SingleOutputStreamOperator<Object> map = env.addSource(new JDBCReader1()).map((MapFunction<Map<String, String>, Object>) stringStringMap -> {
            testMap = stringStringMap;
            return null;
        });
        map.print();
        env.execute();
    }
}
