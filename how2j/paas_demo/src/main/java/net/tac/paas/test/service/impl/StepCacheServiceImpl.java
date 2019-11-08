package net.tac.paas.test.service.impl;

import net.dgg.framework.tac.stepcache.annotation.DggCacheMethod;
import net.dgg.framework.tac.stepcache.annotation.DggCacheable;
import net.tac.paas.test.service.StepCacheService;
import org.springframework.stereotype.Service;

//二级缓存用Service，提供删除和更新操作
@Service
public class StepCacheServiceImpl implements StepCacheService {

    @Override
    @DggCacheable(method = DggCacheMethod.clear)
    public void clearCache(String key) {
    }

    @Override
    @DggCacheable(method = DggCacheMethod.clearByFuzzy)
    public void clearByFuzzy(String key) {
    }

    @Override
    @DggCacheable(method = DggCacheMethod.save)
    public <T> void saveCache(String key, T value) {
    }
}
