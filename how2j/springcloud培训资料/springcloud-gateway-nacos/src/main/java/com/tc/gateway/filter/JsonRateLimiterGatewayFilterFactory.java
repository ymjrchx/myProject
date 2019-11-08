/**
 * FileName: JSONRateLimiterGatewayFilterFactory
 * Author:   tumq
 * Date:     2019/7/18 14:02
 * Description:
 */
package com.tc.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.RequestRateLimiterGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RateLimiter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.HttpStatusHolder;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Flux;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.setResponseStatus;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Richard
 * @create 2019/7/18
 */
public class JsonRateLimiterGatewayFilterFactory extends RequestRateLimiterGatewayFilterFactory {

    private static final String EMPTY_KEY = "____EMPTY_KEY__";





    public JsonRateLimiterGatewayFilterFactory(RateLimiter defaultRateLimiter, KeyResolver defaultKeyResolver) {
        super(defaultRateLimiter, defaultKeyResolver);
    }

    @Override
    public GatewayFilter apply(Config config) {
        KeyResolver resolver = getOrDefault(config.getKeyResolver(), getDefaultKeyResolver());
        RateLimiter<Object> limiter = getOrDefault(config.getRateLimiter(),
                getDefaultRateLimiter());
        boolean denyEmpty = getOrDefault(config.getDenyEmptyKey(), this.isDenyEmptyKey());
        HttpStatusHolder emptyKeyStatus = HttpStatusHolder
                .parse(getOrDefault(config.getEmptyKeyStatus(), this.getEmptyKeyStatusCode()));

        return (exchange, chain) -> {
            Route route = exchange
                    .getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);

            return resolver.resolve(exchange).defaultIfEmpty(EMPTY_KEY).flatMap(key -> {
                if (EMPTY_KEY.equals(key)) {
                    if (denyEmpty) {
                        setResponseStatus(exchange, emptyKeyStatus);
                        return exchange.getResponse().setComplete();
                    }
                    return chain.filter(exchange);
                }
                return limiter.isAllowed(route.getId(), key).flatMap(response -> {

                    for (Map.Entry<String, String> header : response.getHeaders()
                            .entrySet()) {
                        exchange.getResponse().getHeaders().add(header.getKey(),
                                header.getValue());
                    }

                    if (response.isAllowed()) {
                        return chain.filter(exchange);
                    }
                    ServerHttpResponse backResponse = exchange.getResponse();
                    backResponse.setStatusCode(HttpStatus.OK);
                    byte[] bytes = "{\"code\":\"429\",\"msg\":\"流量管控!!!\"}".getBytes(StandardCharsets.UTF_8);
                    DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
                    backResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
                    return  backResponse.writeWith(Flux.just(buffer));
                });
            });
        };
    }

    private <T> T getOrDefault(T configValue, T defaultValue) {
        return (configValue != null) ? configValue : defaultValue;
    }
}