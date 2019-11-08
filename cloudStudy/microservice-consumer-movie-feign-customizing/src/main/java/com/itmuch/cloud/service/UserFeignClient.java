package com.itmuch.cloud.service;

import com.itmuch.cloud.FeignConfiguration;
import com.itmuch.cloud.entity.User;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "microservice-provider-user",configuration = FeignConfiguration.class)
public interface UserFeignClient {

    @RequestLine("GET/{id}")
    public User findById(@Param("id") Long id);
}
