package com.dgg.test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Classname RedisTest
 * @Description TODO
 * @Date 2019/5/29 19:59
 * @Created by dgg-yanshun
 */
public class RedisTest {
    public static void main(String[] args) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(50);//最大连接数
        jedisPoolConfig.setMaxIdle(10);//最大空闲连接数
        jedisPoolConfig.setMaxWaitMillis(-1);//获取连接时的最大等待毫秒数，若超时则抛异常。-1代表不确定的毫秒数
        jedisPoolConfig.setTestOnBorrow(true);//获取连接时检测其有效性
        JedisCluster jedisCluster = new JedisCluster(new HostAndPort("172.16.0.242",7004),15000,100,
                jedisPoolConfig);//第二个参数：超时时间     第三个参数：最大尝试重连次数
        System.out.println(jedisCluster.setex("test111",60,"222222222"));
        jedisCluster.close();
    }
}
