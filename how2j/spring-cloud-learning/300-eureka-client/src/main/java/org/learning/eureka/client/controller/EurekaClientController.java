package org.learning.eureka.client.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eureka/client/")
@Slf4j
public class EurekaClientController {

    @Autowired
    private EurekaClient eurekaClient;

    @Value("${spring.application.name}")
    private String appName;

    @GetMapping("/info")
    public InstanceInfo info() {
        log.info("appName:{}", appName);
        log.info("regiones:{}", eurekaClient.getAllKnownRegions());
        return eurekaClient.getNextServerFromEureka(appName, false);
    }

}
