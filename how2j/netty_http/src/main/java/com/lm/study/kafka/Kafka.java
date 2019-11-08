package com.lm.study.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.*;
import org.apache.storm.kafka.*;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.UUID;

/**
 * @author chenxin
 * @date 2019/8/1 16:54
 */
public class Kafka {
    public void send(){
        Callback callback = new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                System.out.println();
            }
        };
        Properties properties = new Properties();
        KafkaProducer kafkaProducer = new KafkaProducer(properties);
        kafkaProducer.send(new ProducerRecord("topic",1,"key".getBytes(),"value".getBytes()),callback);
        kafkaProducer.flush();

    }

    public void simpleProducer(){
        String topicName = "test";
        Properties props = new Properties();
        props.put("bootstrap.servers","localhost:9092");
        props.put("acks","all");
        props.put("retries",0);
        props.put("batch.size",16384);
        props.put("buffer.memory",3333333);
        props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        Producer<String,String> producer = new KafkaProducer<String, String>(props);
        for (int i = 0; i < 10 ; i++) {
            producer.send(new ProducerRecord<String, String>(topicName,Integer.toString(i),Integer.toString(i)));
            producer.close();

        }


    }



    public void simpleConsumer(){
        String topicNmae = "test";
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer",
                "org.apache.kafka.common.serializa-tion.StringDeserializer");
        props.put("value.deserializer",
                "org.apache.kafka.common.serializa-tion.StringDeserializer");
        KafkaConsumer<String,String> consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe(Arrays.asList(topicNmae));
        int i = 0;
        while (true){
            ConsumerRecords<String,String> records = consumer.poll(Duration.ofSeconds(100));
            for(ConsumerRecord<String,String> record : records){
                System.out.printf("offset = %d, key=%s, value=%s\n",
                        record.offset(),record.key(),record.value());
            }
        }
    }

    public void storm(){
      /*  BrokerHosts hosts = new ZkHosts("");
        SpoutConfig spoutConfig = new SpoutConfig(hosts,"topicName","/"+"topicName",
                UUID.randomUUID().toString());
        spoutConfig.scheme = new KeyValueSchemeAsMultiScheme(new StringScheme());
        KafkaSpout kafkaSpout = new KafkaSpout();

*/

    }








    public void receive(){

    }












































}
