package com.example.demo.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorSupplier;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @author chenxin
 * @date 2019/8/20 11:18
 */
public class KafkaTest {
    @Test
    public void oldProducer() {

        Properties properties = new Properties();
        properties.put("metadata.broker.list", "192.168.254.80:9091,192.168.254.80:9092,192.168.254.80:9093");

        properties.put("request.required.acks", "1");
        properties.put("serializer.class", "kafka.serializer.StringEncoder");
        // Producer<Integer,String> producer
    }

    @Test
    public void newProducer() {
        Properties props = new Properties();
        // Kafka服务端的主机名和端口号
        props.put("bootstrap.servers", "192.168.254.69:9091");
        // 等待所有副本节点的应答
        props.put("acks", "0");
        // 消息发送最大尝试次数
        props.put("retries", 0);
        // 一批消息处理大小
        props.put("batch.size", 16384);
        // 请求延时
        props.put("linger.ms", 1);
        // 发送缓存区内存大小
        props.put("buffer.memory", 33554432);
        // key序列化
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // value序列化
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        for (int i = 0; i < 50; i++) {
            producer.send(new ProducerRecord<String, String>("first", Integer.toString(i), "hello world-" + i));
        }

        producer.close();
    }

    @Test
    public void callBackProducer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.254.69:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(props);
        for (int i = 0; i < 50; i++) {
            kafkaProducer.send(new ProducerRecord<>("first", "hello" + i), new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if (metadata != null) {
                        System.err.println(metadata.partition() + "--" + metadata.offset());
                    }
                }
            });
        }
        kafkaProducer.close();
    }


    @Test
    public void partitionProducer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.254.69:9091");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("partitioner.class", "com.example.demo.kafka.CustomPartitioner");
        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        producer.send(new ProducerRecord<>("first", "1", "atguigu"));
        producer.close();


    }


    @Test
    public void customNewConsumer() {
        Properties props = new Properties();
        // 定义kakfa 服务的地址，不需要将所有broker指定上
        props.put("bootstrap.servers", "192.168.254.69:9092");
        // 制定consumer group
        props.put("group.id", "test");
        // 是否自动确认offset
        props.put("enable.auto.commit", "true");
        // 自动确认offset的时间间隔
        props.put("auto.commit.interval.ms", "1000");
        // key的序列化类
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // value的序列化类
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // 定义consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        // 消费者订阅的topic, 可同时订阅多个
        consumer.subscribe(Arrays.asList( "second", "third"));

        while (true) {
            // 读取数据，读取超时时间为100ms
            ConsumerRecords<String, String> records = consumer.poll(100);

            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
            }
        }
    }

    @Test
    public void interceptorProducer() {
        // 1 设置配置信息
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.254.69:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        List<String> interceptors = new ArrayList<>();
        interceptors.add("com.example.demo.kafka.TimeInterceptor");
        interceptors.add("com.example.demo.kafka.CounterInterceptor");
        props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, interceptors);
        String topic = "first";
        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        for (int i = 0; i < 1000; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, "message >>> " + i);
            producer.send(record);

        }

        producer.close();

    }


    @Test
    public void streamApplication()
    {
        String from = "first";
        String to = "second";
        Properties settings = new Properties();
        settings.put(StreamsConfig.APPLICATION_ID_CONFIG,"logFilter");
        settings.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.254.69:9092");
        StreamsConfig config = new StreamsConfig(settings);
        //构建拓扑
        Topology topology = new Topology();
        topology.addSource("SOURCE",from)
                .addProcessor("PROCESS", new ProcessorSupplier<byte[],byte[]>() {
                    @Override
                    public Processor get() {
                        return new LogProcessor();
                    }
                },"SOURCE")
                .addSink("SINK",to,"PROCESS");
        KafkaStreams streams = new KafkaStreams(topology,config);
        streams.start();
    }




    public void streamTest() throws InterruptedException {
        KafkaTest test = new KafkaTest();
        Runnable producer = new Runnable() {
            @Override
            public void run() {
                test.interceptorProducer();
            }
        };
        Runnable consumer = new Runnable() {
            @Override
            public void run() {
                test.customNewConsumer();
            }
        };

        Runnable processor = new Runnable() {
            @Override
            public void run() {
                test.streamApplication();
            }
        };

        Thread thread = new Thread(producer);
        Thread thread1 = new Thread(consumer);
        Thread thread2 = new Thread(processor);
        thread2.join();
        thread2.start();
        thread.start();
        thread1.start();
    }

    public static void main(String[] args) throws InterruptedException {
        KafkaTest kafkaTest = new KafkaTest();
        kafkaTest.streamTest();
    }

    @Test
    public void testa(){
        System.out.println(0.0 ==0);
        System.out.println(0.0 != 0);
        System.out.println(0.0 > 0);
    }

}
