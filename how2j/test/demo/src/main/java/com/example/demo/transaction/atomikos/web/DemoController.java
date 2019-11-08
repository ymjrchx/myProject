package com.example.demo.transaction.atomikos.web;

import com.example.demo.transaction.atomikos.service.DemoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @author chenxin
 * @date 2019/9/27 16:42
 */
@Controller
public class DemoController {
    @Resource
    private DemoService dsService;

    @RequestMapping("/testXaDatasource")
    public String testXaDatasource() {
        int result = 0;
        try {
            result = dsService.save1();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(result);
    }

    @RequestMapping("/testXaDatasource2")
    public String testXaDatasource2() {
        int result = 0;
        try {
            result = dsService.save2();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(result);
    }
}
