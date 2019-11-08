package com.dgg.util;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @Classname DggRedisUtil
 * @Description 连接redis的配置
 * @Date 2019/5/9 9:46
 * @Created by dgg-yanshun
 */
public class DggRedisUtil {

    private static JedisCluster jedisCluster;

    static{
        setJedisCluster();
    }

    public static Set<HostAndPort> getRedisClusterConfig(){
        Set<HostAndPort> clusterNodes = new HashSet<>();
        clusterNodes.add(new HostAndPort("192.168.254.241",7001));
        clusterNodes.add(new HostAndPort("192.168.254.241",7002));
        clusterNodes.add(new HostAndPort("192.168.254.241",7003));
        clusterNodes.add(new HostAndPort("192.168.254.242",7004));
        clusterNodes.add(new HostAndPort("192.168.254.242",7005));
        clusterNodes.add(new HostAndPort("192.168.254.242",7006));
        return clusterNodes;
    }

    public synchronized static void setJedisCluster(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(50);//最大连接数
        jedisPoolConfig.setMaxIdle(10);//最大空闲连接数
        jedisPoolConfig.setMaxWaitMillis(-1);//获取连接时的最大等待毫秒数，若超时则抛异常。-1代表不确定的毫秒数
        jedisPoolConfig.setTestOnBorrow(true);//获取连接时检测其有效性
        jedisCluster = new JedisCluster(getRedisClusterConfig(),15000,100,
                jedisPoolConfig);//第二个参数：超时时间     第三个参数：最大尝试重连次数
    }


    public static JedisCluster getJedisCluster(){
        if(Objects.isNull(jedisCluster)){
            setJedisCluster();
        }
        return jedisCluster;
    }

    /**
     * 把实时数据发送到redis
     * @param loginName 工号
     * @param json 实时数据
     */
    public static void sinkJsonToRedis(String loginName, String json){
        if(Objects.isNull(jedisCluster)){
            setJedisCluster();
        }
        //把json数据存入redis,保存30天，如果30天都没有产生新的数据，估计离职了
        jedisCluster.setex("ibwb_realtime_" + loginName, 60 * 60 * 24 * 30, json);
    }

    /**
     * 把订单结果发送到redis
     * @param loginName
     * @param json
     */
    public static void sinkOrderJsonToRedis(String loginName, String json){
        if(Objects.isNull(jedisCluster)){
            setJedisCluster();
        }
        //把json数据存入redis,保存30天，如果30天都没有产生新的数据，估计离职了
        jedisCluster.setex("ibwb_orf_" + loginName, 60 * 60 * 24, json);
        System.out.println("存入成功");
    }

    /**
     * 把业绩结果发送到redis
     * @param loginName
     * @param json
     */
    public static void sinkProJsonToRedis(String loginName, String json){
        if(Objects.isNull(jedisCluster)){
            setJedisCluster();
        }
        //把json数据存入redis,保存30天，如果30天都没有产生新的数据，估计离职了
        jedisCluster.setex("ibwb_pro_" + loginName, 60 * 60 * 24, json);
        System.out.println("存入成功:" + json + ",time:" + new Date());
    }

    /**
     * 通过电话号获取用户信息
     * @param src 外呼号或者被呼号
     * @return 返回用户信息，以逗号分隔
     */
    public static String getUserInfoFromRedis(String src){
        String userInfo = jedisCluster.get("ibwb_userinfo_phone_" + src);
        return userInfo;
    }

    /**
     * 通过用户id获取用户工号
     * @param userId 用户id
     * @return 用户工号
     */
    public static String getloginNameFromRedis(Long userId){
        if(userId == null){
            return null;
        }else{
            String loginName = jedisCluster.get("ibwb_userinfo_" + userId);
            return loginName;
        }
    }
    public static String getIdFromRedis(String loginName){
        String id = jedisCluster.get("ibwb_userinfo_" + loginName);
        return id;
    }

    /**
     * 根据id获取订单的状态
     * @param id
     * @return
     */
    public static String getOrderStatus(Long id){
        String status = jedisCluster.get("ibwb_orf_order_" + id);
        return status;
    }

}
