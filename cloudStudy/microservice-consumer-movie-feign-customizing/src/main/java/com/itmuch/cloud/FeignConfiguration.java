package com.itmuch.cloud;

import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;

public class FeignConfiguration {
    @Bean
    public BasicAuthorizationInterceptor basicAuthorizationInterceptor(){
        return new BasicAuthorizationInterceptor("user","password");
    }
    @Bean
    public Contract feignContract(){
        return new feign.Contract.Default();
}
}
