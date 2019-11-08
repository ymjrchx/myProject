/**
 * FileName: AutoLoadBalanceClientFilter
 * Author:   tumq
 * Date:     2019/7/9 10:18
 * Description: 根据后端性能自动负载均衡
 */
package com.tc.gateway.config.loadbalance;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;
import java.net.URI;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.gateway.config.LoadBalancerProperties;
import org.springframework.cloud.gateway.filter.LoadBalancerClientFilter;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
/**
 * 〈一句话功能简述〉<br> 
 * 〈根据后端性能自动负载均衡〉
 *
 * @author Richard
 * @create 2019/7/9
 */
//@Component
public class AutoLoadBalanceClientFilter extends LoadBalancerClientFilter {

    public AutoLoadBalanceClientFilter(LoadBalancerClient loadBalancer, LoadBalancerProperties properties) {
        super(loadBalancer, properties);
    }

    @Override
    protected ServiceInstance choose(ServerWebExchange exchange) {
        //这里可以拿到web请求的上下文，可以从header中取出来自己定义的数据。
        String userId = exchange.getRequest().getHeaders().getFirst("userId");
        if (userId == null) {
            return super.choose(exchange);
        }
        if (this.loadBalancer instanceof RibbonLoadBalancerClient) {
            RibbonLoadBalancerClient client = (RibbonLoadBalancerClient) this.loadBalancer;
            String serviceId = ((URI) exchange.getAttribute(GATEWAY_REQUEST_URL_ATTR)).getHost();
            //这里使用userId做为选择服务实例的key
            return client.choose(serviceId, userId);
        }
        return super.choose(exchange);
    }



}