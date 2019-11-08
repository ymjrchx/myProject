package com.dgg.Redis;

import com.dgg.config.RedisConfig;
import com.dgg.util.DggDBCPUtil;
import org.apache.commons.lang.StringUtils;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Classname sinkUserInfo
 * @Description TODO
 * @Date 2019/6/13 13:42
 * @Created by dgg-yanshun
 */
public class sinkUserInfo {

    public  static JedisCluster jedisCluster = null;
    private static int i = 0;
    static{
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(50);//最大连接数
        jedisPoolConfig.setMaxIdle(10);//最大空闲连接数
        jedisPoolConfig.setMaxWaitMillis(-1);//获取连接时的最大等待毫秒数，若超时则抛异常。-1代表不确定的毫秒数
        jedisPoolConfig.setTestOnBorrow(true);//获取连接时检测其有效性
        jedisCluster = new JedisCluster(RedisConfig.getRedisClusterConfig(),15000,100,
                jedisPoolConfig);//第二个参数：超时时间     第三个参数：最大尝试重连次数
    }


    public static void main(String[] args) throws SQLException {
        Connection connection = DggDBCPUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
//            ps = connection.prepareStatement( "select id,login_name,seat_number,voip_username,org_id from `sys_user_info` where locked = 0");
            ps = connection.prepareStatement( "select id,login_name,seat_number,voip_username,org_id from `sys_user_info` where locked = 0");
            rs = ps.executeQuery();
            while(rs.next()){
                long id = rs.getLong("id");
                String loginName = rs.getString("login_name");
                String seatNumber = rs.getString("seat_number");
                String voipUsername = rs.getString("voip_username");
                String orgId = rs.getString("org_id");

                jedisCluster.set("ibwb_user_info_" + loginName, id + "," + orgId);
//                jedisCluster.del("ibwb_userinfo_" + loginName);
//                System.out.println(loginName + "已添加到redis");
                jedisCluster.set("ibwb_user_info_" + id, loginName + "," + orgId);
//                jedisCluster.del("ibwb_userinfo_" + id);
                if (StringUtils.isNotEmpty(voipUsername)) {
                    jedisCluster.set("ibwb_user_info_phone_" + voipUsername,  id + "," + loginName + "," + orgId);
//                    jedisCluster.del("ibwb_userinfo_phone_" + voipUsername);
//                    System.out.println(voipUsername + "已添加到redis");
                }
                if (StringUtils.isNotEmpty(seatNumber)) {
                    jedisCluster.set("ibwb_user_info_phone_" + seatNumber, id + "," + loginName + "," + orgId);
//                    jedisCluster.del("ibwb_userinfo_phone_" + seatNumber);
//                    System.out.println(seatNumber + "已添加到redis");
                }
                System.out.println(++i);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            connection.close();
            ps.close();
            rs.close();
        }
    }


}
