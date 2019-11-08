package com.example.rabbitmqtest;

import net.dgg.framework.tac.rabbit.annotation.DggEnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@DggEnableRabbit
public class StartApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(StartApplication.class, args);
    }

}
