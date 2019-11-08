package net.tac.paas.test.controller;

import lombok.extern.slf4j.Slf4j;
import net.tac.paas.test.model.TestModel;
import net.tac.paas.test.service.GetService;
import net.tac.paas.test.service.StepCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Autowired
    private StepCacheService stepcacheService;

    @Autowired
    private GetService getService;

    //从缓存获取数据，若找不到则执行自定义的方法（自定义的方法将sleep3秒）
    @GetMapping("/get")
    public Mono<TestModel> get() {
        return Mono.just(getService.getModel());
    }

    //根据key前缀清除缓存的数据
    @GetMapping("/clearByFuzzy")
    public Mono<String> clearByFuzzy(){
        stepcacheService.clearByFuzzy("iamthe");
        return Mono.just("success");
    }

    //根据key清除缓存的数据
    @GetMapping("/clear")
    public Mono<String> clear(){
        stepcacheService.clearCache("iamthekey");
        return Mono.just("success");
    }

    //更新缓存的数据
    @GetMapping("/save")
    public Mono<String> save(){
        TestModel t = new TestModel();
        t.setName("newName2");
        t.setPwd("newPwd2");
        t.setValue("newValue2");
        t.setVlong(22222222222222L);
        stepcacheService.saveCache("iamthekey", t);
        return Mono.just("success");
    }
}