package net.dgg.schoolfrontfeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class SchoolfrontfeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolfrontfeignApplication.class, args);
    }

}
