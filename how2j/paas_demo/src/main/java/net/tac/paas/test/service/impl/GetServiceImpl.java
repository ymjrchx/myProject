package net.tac.paas.test.service.impl;

import net.dgg.framework.tac.stepcache.annotation.DggCacheMethod;
import net.dgg.framework.tac.stepcache.annotation.DggCacheable;
import net.tac.paas.test.model.TestModel;
import net.tac.paas.test.service.GetService;
import org.springframework.stereotype.Service;

@Service
public class GetServiceImpl implements GetService {

    @Override
    @DggCacheable(key = "iamthekey", method = DggCacheMethod.get, expTime=7200)
    public TestModel getModel() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TestModel t = new TestModel();
        t.setName("111");
        t.setPwd("222");
        t.setValue("33");
        t.setVlong(1111111111111L);
        return t;
    }
}