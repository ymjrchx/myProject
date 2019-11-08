/**
 * FileName: UserService
 * Author:   tumq
 * Date:     2019/7/3 11:05
 * Description:
 */
package net.dgg.schoolclient.service;

import model.vo.LoginObj;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Richard
 * @create 2019/7/3
 */
@FeignClient(value = "school-server",fallbackFactory = UserServiceFallBack.class)
public interface UserService {

    @RequestMapping("/user/loginbyfrom")
    public Map<String,Object> loginByFrom(@RequestParam(value = "userName", required = true) String userName,@RequestParam(value = "password", required = true) String password);

    @RequestMapping("/user/loginbyjson")
    public Map<String,Object> loginByJson(@RequestBody LoginObj loginObj);

}