package net.dgg.schoolfrontserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"net.dgg"})
@SpringBootApplication(scanBasePackages = {"net.dgg"})
public class SchoolfrontserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolfrontserverApplication.class, args);
	}

}
