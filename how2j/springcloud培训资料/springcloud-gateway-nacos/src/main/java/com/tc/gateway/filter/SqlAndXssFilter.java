/**
 * FileName: SqlAndXssFilter
 * Author:   tumq
 * Date:     2019/7/9 14:03
 * Description: sql注入及xss攻击过滤
 */
package com.tc.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.support.BodyInserterContext;
import org.springframework.cloud.gateway.support.CachedBodyOutputMessage;
import org.springframework.cloud.gateway.support.DefaultServerRequest;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

/**
 * 〈一句话功能简述〉<br>
 * 〈sql注入及xss攻击过滤〉
 *
 * @author Richard
 * @create 2019/7/9
 */
@Component
public class SqlAndXssFilter implements GlobalFilter, Ordered {
    private static final String CACHE_REQUEST_BODY_OBJECT_KEY = "cachedRequestBodyObject";
    private Logger logger = LoggerFactory.getLogger(SqlAndXssFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpResponse response = exchange.getResponse();
        ServerHttpRequest request = exchange.getRequest();
        Object requestBody = exchange.getAttribute(CACHE_REQUEST_BODY_OBJECT_KEY);
        //只记录 http 请求(包含 https)
        String schema = request.getURI().getScheme();
        if ((!"http".equals(schema) && !"https".equals(schema))) {
            return chain.filter(exchange);
        }
        if(requestBody==null){
            return chain.filter(exchange);
        }


        final StringBuffer body = new StringBuffer();
        if (!request.getQueryParams().isEmpty()) {
            body.append(JSONObject.toJSONString(request.getQueryParams()));
        }

        if (StringUtils.isEmpty(body.toString())) {

            System.out.println(requestBody);
            body.append(JSONObject.toJSONString(requestBody));

        }
        Map<String, Object> stringObjectMap = cleanXSS(body.toString(), exchange);
        JSONObject json = JSONObject.parseObject(JSONObject.toJSONString(stringObjectMap));
        String newBody = json.toString();
        //	如果存在sql注入,直接拦截请求
        if (newBody.contains("forbid")) {
            response.setStatusCode(HttpStatus.OK);
            byte[] bytes = "{\"status\":\"-1\",\"msg\":\"非法请求!!\"}".getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
            response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");

            return  response.writeWith(Flux.just(buffer));
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }


    private Map<String, Object> cleanXSS(String value, ServerWebExchange exchange) {
        Map<String, Object> mapjson = new HashMap<>(10);
        try {
            value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
            value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
            value = value.replaceAll("'", "& #39;");
            value = value.replaceAll("eval\\((.*)\\)", "");
            value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
            value = value.replaceAll("script", "");
            value = value.replaceAll("[*]", "[" + "*]");
            value = value.replaceAll("[+]", "[" + "+]");
            value = value.replaceAll("[?]", "[" + "?]");

            String badStr = "'|and|exec|execute|insert|select|delete|update|count|drop|%|chr|mid|master|truncate|" +
                    "char|declare|sitename|net user|xp_cmdshell|;|or|+|,|like'|and|exec|execute|insert|create|drop|" +
                    "table|from|grant|use|group_concat|column_name|" +
                    "information_schema.columns|table_schema|union|where|select|delete|update|order|by|count|" +
                    "chr|mid|master|truncate|char|declare|or|;|--|,|like|//|/|%|#";

            JSONObject json = JSONObject.parseObject(value);
            String[] badStrs = badStr.split("\\|");
            Map<String, Object> map = json;

            if (json != null) {
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    String paramValue = entry.getValue().toString();
                    for (String keyword : badStrs) {
                        if (paramValue.length() > keyword.length() && (paramValue.contains(" " + keyword) || paramValue.contains(keyword + " ") || paramValue.contains(" " + keyword + " "))) {
                            paramValue = "forbid";
                            mapjson.put(entry.getKey(), paramValue);

                            String currentUrl = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
                            logger.error(currentUrl + "已被过滤，因为参数中包含不允许sql的关键词(" + keyword + ")" + ";参数：" + value + ";过滤后的参数：" + paramValue);
                            break;
                        } else {
                            mapjson.put(entry.getKey(), entry.getValue());
                        }
                    }
                }
            }
            return mapjson;
        } catch (Throwable e) {
            logger.error("网关错误", e);
            String paramValue = "forbid";
            mapjson.put(paramValue, paramValue);
            return mapjson;
        }
    }


}