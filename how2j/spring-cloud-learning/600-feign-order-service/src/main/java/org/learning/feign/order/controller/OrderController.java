package org.learning.feign.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.learning.feign.order.base.Result;
import org.learning.feign.order.client.UserClient3;
import org.learning.feign.order.dto.UserMoneyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order/")
@Slf4j
public class OrderController {

//    @Autowired
//    private UserClient userClient;

//    @Autowired
//    private UserClient2 userClient2;

    @Autowired
    private UserClient3 userClient3;

    @GetMapping("place")
    public Result<UserMoneyDto> placeOrder() {
        Result<UserMoneyDto> res = new Result<>();
//        UserMoneyDto data = userClient.accountMoney(new Long(1));
//        res.setData(data);

        UserMoneyDto data = userClient3.accountMoney(new Long(1));
        res.setData(data);

        log.info("用户下单");
        return res;
    }

}
