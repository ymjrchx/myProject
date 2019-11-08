//package org.learning.feign.order.client;
//
//import org.learning.feign.order.dto.UserMoneyDto;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@FeignClient(name = "${user.service.name}")
//public interface UserClient {
//
//    @GetMapping("/user/account/money")
//    UserMoneyDto accountMoney(@RequestParam(name = "userId") Long userId);
//
//}
