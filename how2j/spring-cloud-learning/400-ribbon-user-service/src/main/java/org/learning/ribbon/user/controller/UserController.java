package org.learning.ribbon.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.learning.ribbon.user.dto.UserMoneyDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/user/")
@Slf4j
public class UserController {

    @GetMapping("account/money")
    public UserMoneyDto accountMoney(@RequestParam("userId") Long userId) {
        UserMoneyDto userMoneyDto = new UserMoneyDto();
        userMoneyDto.setAccount(userId.toString());
        Random random = new Random();
        int num = random.nextInt(9999999);
        userMoneyDto.setMoney(num);
        log.info("userId:{},查询用户余额:{}", userId, userMoneyDto);
        return userMoneyDto;
    }

}
