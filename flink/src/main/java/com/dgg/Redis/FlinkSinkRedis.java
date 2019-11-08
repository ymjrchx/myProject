package com.dgg.Redis;

import com.dgg.common.Constant;
import com.dgg.config.RedisConfig;
import com.dgg.util.FlinkUtil;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;
import org.apache.flink.streaming.connectors.redis.RedisSink;
import org.apache.flink.streaming.connectors.redis.common.config.FlinkJedisClusterConfig;


public class FlinkSinkRedis {
    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = FlinkUtil.getStreamExecutionEnvironment();
        FlinkKafkaConsumer011<String> consumer = new FlinkKafkaConsumer011<>(Constant.GROUP_ID,
                new SimpleStringSchema(),
                FlinkUtil.getProperties());
        DataStream<Tuple2<String,String>> kafkaStream = env.addSource(consumer).map((MapFunction<String, Tuple2<String, String>>) line -> {
            String[] split = line.split(",");
            return new Tuple2<>(split[0],split[1]);
        }).returns(Types.TUPLE(Types.STRING,Types.STRING));
        kafkaStream.print();
        //实例化Flink和Redis关联类FlinkJedisPoolConfig，设置Redis端口
        //1.flink连接单机redis
//        FlinkJedisPoolConfig conf = new FlinkJedisPoolConfig.Builder().setHost("localhost").setHost("7001").build();

        FlinkJedisClusterConfig conf = new FlinkJedisClusterConfig.Builder().setNodes(RedisConfig.getRedisClusterConfigForFlink())
                .setMaxTotal(50)//最大连接数
                .setMinIdle(10)//最大空闲连接数
                .setTimeout(15000)//超时时间
                .setMaxRedirections(6)//重试次数
                .build();
        kafkaStream.addSink(new RedisSink<>(conf,new RedisExampleMapper()));
//        System.out.println("写入成功");
        env.execute();
    }

}
