/**
 * FileName: UserServiceFallBack
 * Author:   tumq
 * Date:     2019/7/3 11:06
 * Description:
 */
package net.dgg.schoolclient.service;

import feign.hystrix.FallbackFactory;
import model.vo.LoginObj;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Richard
 * @create 2019/7/3
 */
@Component
public class UserServiceFallBack  implements FallbackFactory<UserService> {
    @Override
    public UserService create(Throwable throwable) {


        return new UserService() {
            @Override
            public Map<String, Object> loginByFrom(String userName, String password) {
                Map<String,Object> result=new HashMap<>();
                result.put("code","429");
                result.put("msg","服务正忙,请稍后再试!");
                return result;
            }

            @Override
            public Map<String, Object> loginByJson(LoginObj loginObj) {
                Map<String,Object> result=new HashMap<>();
                result.put("code","429");
                result.put("msg","服务正忙,请稍后再试!");
                return result;
            }

        };
    }

}