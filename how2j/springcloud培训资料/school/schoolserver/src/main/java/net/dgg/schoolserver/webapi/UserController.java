/**
 * FileName: UserController
 * Author:   tumq
 * Date:     2019/7/3 10:41
 * Description:
 */
package net.dgg.schoolserver.webapi;

import com.alibaba.fastjson.JSONObject;
import model.vo.LoginObj;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
@RequestMapping("/user")
@RefreshScope
public class UserController {
    Logger logger= LoggerFactory.getLogger(UserController.class);

    @Value("${school.index:1}")
    private String instance;

    @Value("${confRf:1}")
    private String confRf;


    @RequestMapping("/loginbyfrom")
    public Map<String,Object> loginByFrom(@RequestParam(value = "userName", required = true) String userName,@RequestParam(value = "password", required = true) String password){
        Map<String,Object> result=new HashMap<>(3);
        result.put("code",200);
        result.put("msg1","欢迎你,"+userName+"我的实例编号为:"+instance);
        result.put("msg2","欢迎你,"+userName+"我的config为:"+confRf);
        logger.info("result{}", JSONObject.toJSONString(result));
        return result;
    }

    @RequestMapping("/loginbyjson")
    public Map<String,Object> loginByJson(@RequestBody LoginObj loginObj){
        Map<String,Object> result=new HashMap<>(3);
        result.put("code",200);
        result.put("msg","欢迎你,"+loginObj.getUserName()+"我的实例编号为:"+instance);
        logger.info("result", JSONObject.toJSONString(result));
        return result;
    }


}