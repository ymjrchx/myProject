/**
 * FileName: ServerConfig
 * Author:   tumq
 * Date:     2019/7/5 16:24
 * Description:
 */
package com.tc.gateway.config;

import com.tc.gateway.filter.JsonRateLimiterGatewayFilterFactory;
import com.tc.gateway.filter.ReadBodyRoutePredicateFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cloud.gateway.filter.factory.RequestRateLimiterGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.function.Predicate;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Richard
 * @create 2019/7/5
 */
@Slf4j
@Configuration
public class ServerConfig {
    @Qualifier
    @Bean
    public KeyResolver ipKeyResolver() {
        return new KeyResolver() {
            @Override
            public Mono<String> resolve(ServerWebExchange exchange) {
                return  Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
            }
        };
    }

    @Qualifier
    @Bean
    public KeyResolver userKeyResolver() {
        return new KeyResolver() {
            @Override
            public Mono<String> resolve(ServerWebExchange exchange) {
                String userName = exchange.getRequest().getQueryParams().getFirst("userName");
                if (StringUtils.isEmpty(userName)) {
                    userName = exchange.getRequest().getHeaders().getFirst("token");
                }
                if (StringUtils.isEmpty(userName)) {
                    return Mono.just("1");
                }
                return Mono.just(userName);
            }
        };
    }

    @Bean
    @Primary
    public KeyResolver apiKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getPath().value());
    }


   /* @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder serviceProvider = builder.
                routes().route("gateway-sample",
                r -> r.readBody(Object.class, requestBody -> {
                    log.info("requestBody is {}", requestBody);
                    return true;
                }).and()
                  .path("/**")
                  .uri("http://127.0.0.1:5001").order(-1));
        RouteLocator routeLocator = serviceProvider.build();
        return routeLocator;
    }*/

    @Bean
    public ReadBodyRoutePredicateFactory readBodyRoutePredicateFactory(){
        return new ReadBodyRoutePredicateFactory();
    }

    @Bean
    public Predicate readBodyPredicate(){
        Predicate p= o -> true;
        return p;
    }

    @Bean
    public Class readBodyClass(){
        return Object.class;
    }


    @Bean
    public JsonRateLimiterGatewayFilterFactory jsonRateLimiterGatewayFilterFactory(
            RateLimiter rateLimiter, KeyResolver resolver) {
        return new JsonRateLimiterGatewayFilterFactory(rateLimiter, resolver);
    }


}