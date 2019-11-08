package org.learning.ribbon.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.learning.ribbon.order.base.Result;
import org.learning.ribbon.order.dto.UserMoneyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order/")
@Slf4j
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${user.service.url}")
    private String userServiceUrl;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Value("${user.service.name}")
    private String userServiceName;

    @GetMapping("place")
    public Result<UserMoneyDto> placeOrder() {
        log.info("用户下单");
        String reqUrl = userServiceUrl + "/user/account/money?userId=123";
        UserMoneyDto data = restTemplate.getForObject(reqUrl, UserMoneyDto.class);
        Result<UserMoneyDto> res = new Result<>();
        res.setCode(200);
        res.setMessage("请求成功");
        res.setData(data);
        return res;
    }

    @GetMapping("choose")
    public ServiceInstance choose() {
        return loadBalancerClient.choose(userServiceName);
    }

}
