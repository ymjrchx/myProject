package net.tac.paas.test;

import net.dgg.framework.tac.elasticsearch.annotation.DggEnableES;
import net.dgg.framework.tac.kafka.annotation.DggEnableKafka;
import net.dgg.framework.tac.mongo.annotation.DggEnableMongo;
import net.dgg.framework.tac.redis.annotation.DggEnableRedis;
import net.dgg.framework.tac.stepcache.annotation.DggEnableStepcaching;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@DggEnableMongo
@DggEnableRedis
@DggEnableES
@DggEnableKafka
@DggEnableStepcaching
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}