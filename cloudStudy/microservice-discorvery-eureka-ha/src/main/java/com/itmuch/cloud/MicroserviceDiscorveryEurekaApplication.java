package com.itmuch.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MicroserviceDiscorveryEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceDiscorveryEurekaApplication.class, args);
    }

}
