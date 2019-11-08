package org.learning.feign.order.client.fallback;

import lombok.extern.slf4j.Slf4j;
import org.learning.feign.order.client.UserClient3;
import org.learning.feign.order.dto.UserMoneyDto;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserClient3FallBack implements UserClient3 {

    @Override
    public UserMoneyDto accountMoney(Long userId) {
        log.error("用户服务异常");
        return null;
    }

}
