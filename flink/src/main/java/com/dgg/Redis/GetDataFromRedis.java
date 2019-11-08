package com.dgg.Redis;

import com.dgg.config.RedisConfig;
import redis.clients.jedis.*;


public class GetDataFromRedis {
    public static void main(String[] args) throws InterruptedException {

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(50);//最大连接数
        jedisPoolConfig.setMaxIdle(10);//最大空闲连接数
        jedisPoolConfig.setMaxWaitMillis(-1);//获取连接时的最大等待毫秒数，若超时则抛异常。-1代表不确定的毫秒数
        jedisPoolConfig.setTestOnBorrow(true);//获取连接时检测其有效性
        JedisCluster jedisCluster = new JedisCluster(RedisConfig.getRedisClusterConfig(),15000,100,
                jedisPoolConfig);//第二个参数：超时时间     第三个参数：最大尝试重连次数
        System.out.println(jedisCluster.get("ibwb_manager_" + "50235603_2019-09-10"));
        jedisCluster.close();
    }
}
