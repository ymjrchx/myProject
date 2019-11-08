/**
 * FileName: FrontLoginController
 * Author:   tumq
 * Date:     2019/7/3 11:24
 * Description:
 */
package net.dgg.schoolfrontserver.webapi;

import model.vo.LoginObj;
import net.dgg.schoolclient.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Richard
 * @create 2019/7/3
 */
@RestController
@RequestMapping("/teacher")
public class FrontLoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/loginone")
    public Mono<Map<String,Object>> loginOne(@RequestParam  String userName, @RequestParam  String password){
        return Mono.just(userService.loginByFrom(userName,password));
    }

    @RequestMapping("/logintwo")
    public Map<String,Object> loginOne(@RequestBody LoginObj loginObj){
        return userService.loginByJson(loginObj);
    }
}