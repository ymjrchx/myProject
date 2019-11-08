package com.dgg.config;

import redis.clients.jedis.HostAndPort;

import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Set;

public class RedisConfig {
//    /**
//     * redis的集群信息
//     * @return
//     */
//    public static Set<HostAndPort> getRedisClusterConfig(){
//        Set<HostAndPort> clusterNodes = new HashSet<>();
//        clusterNodes.add(new HostAndPort("10.2.1.106",7001));
//        clusterNodes.add(new HostAndPort("10.2.1.106",7002));
//        clusterNodes.add(new HostAndPort("10.2.1.106",7003));
//        clusterNodes.add(new HostAndPort("10.2.1.107",7004));
//        clusterNodes.add(new HostAndPort("10.2.1.107",7005));
//        clusterNodes.add(new HostAndPort("10.2.1.107",7006));
//        return clusterNodes;
//    }
    public static Set<InetSocketAddress> getRedisClusterConfigForFlink(){
        Set<InetSocketAddress> clusterNodes = new HashSet<>();
        clusterNodes.add(new InetSocketAddress("192.168.254.241",7001));
        clusterNodes.add(new InetSocketAddress("192.168.254.241",7002));
        clusterNodes.add(new InetSocketAddress("192.168.254.241",7003));
        clusterNodes.add(new InetSocketAddress("192.168.254.242",7004));
        clusterNodes.add(new InetSocketAddress("192.168.254.242",7005));
        clusterNodes.add(new InetSocketAddress("192.168.254.242",7006));
        return clusterNodes;
    }


    /**
     * T环境 redis的集群信息
     * @return
     */
    public static Set<HostAndPort> getRedisClusterConfig(){
        Set<HostAndPort> clusterNodes = new HashSet<>();
        clusterNodes.add(new HostAndPort("192.168.254.117",7001));
        clusterNodes.add(new HostAndPort("192.168.254.117",7002));
        clusterNodes.add(new HostAndPort("192.168.254.117",7003));
        clusterNodes.add(new HostAndPort("192.168.254.118",7004));
        clusterNodes.add(new HostAndPort("192.168.254.118",7005));
        clusterNodes.add(new HostAndPort("192.168.254.118",7006));
        return clusterNodes;
    }


//    public static Set<HostAndPort> getRedisClusterConfig(){
//        Set<HostAndPort> clusterNodes = new HashSet<>();
//        clusterNodes.add(new HostAndPort("10.2.1.106",7001));
//        clusterNodes.add(new HostAndPort("10.2.1.106",7002));
//        clusterNodes.add(new HostAndPort("10.2.1.106",7003));
//        clusterNodes.add(new HostAndPort("10.2.1.107",7004));
//        clusterNodes.add(new HostAndPort("10.2.1.107",7005));
//        clusterNodes.add(new HostAndPort("10.2.1.107",7006));
//        return clusterNodes;
//    }

}
