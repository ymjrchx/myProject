/**
 * FileName: FallbackController
 * Author:   tumq
 * Date:     2019/7/9 11:34
 * Description:
 */
package com.tc.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Richard
 * @create 2019/7/9
 */
@RestController
public class FallbackController {
    @RequestMapping(value = "/fallbackcontroller")
    public Map<String, String> fallBackController() {
        Map<String, String> res = new HashMap();
        res.put("code", "501");
        res.put("data", "服务正忙");
        return res;
    }

}