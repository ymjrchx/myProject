package net.tac.paas.test.service;

public interface StepCacheService {

    void clearCache(String key);

    void clearByFuzzy(String key);

    <T> void saveCache(String key, T value);
}