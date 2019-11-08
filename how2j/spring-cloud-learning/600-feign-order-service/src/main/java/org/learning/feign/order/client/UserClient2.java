//package org.learning.feign.order.client;
//
//import config.FooConfiguration2;
//import feign.Param;
//import feign.RequestLine;
//import org.learning.feign.order.dto.UserMoneyDto;
//import org.springframework.cloud.openfeign.FeignClient;
//
//@FeignClient(name = "${user.service.name}", configuration = FooConfiguration2.class)
//public interface UserClient2 {
//
//    @RequestLine("GET /user/account/money?userId={userId}")
//    UserMoneyDto accountMoney(@Param("userId") Long userId);
//
//}
