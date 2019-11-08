package org.learning.ribbon.order.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RibbonClient(name = "${user.service.name}", configuration = TestConfiguration.FooConfiguration.class)
public class TestConfiguration {

    @Configuration
    protected static class FooConfiguration {
        @Bean
        @ConditionalOnMissingBean
        public IRule ribbonRule() {
            // 使用随机策略
            return new RandomRule();
            // 使用轮询策略
//            return new RoundRobinRule();
        }
    }

}
