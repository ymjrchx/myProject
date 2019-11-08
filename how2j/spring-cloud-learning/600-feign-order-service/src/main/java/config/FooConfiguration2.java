package config;

import feign.Contract;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FooConfiguration2 {

    @Bean
    public Contract feignContract() {
        // Contract feignContract: SpringMvcContract
        // feign 默认使用的是springmvc契约
        return new Contract.Default();
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

}
