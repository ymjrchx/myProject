package com.dgg.flink;

import com.dgg.Bean.DggUserInfo;
import com.dgg.config.RedisConfig;
import com.dgg.util.FlinkUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.typeinfo.BasicTypeInfo;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.io.jdbc.JDBCInputFormat;
import org.apache.flink.api.java.typeutils.RowTypeInfo;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.types.Row;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

/**
 * flink消费mysql的数据
 */
public class FlinkFromMysql {

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

    /**
     * 往redis里写用户数据
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = FlinkUtil.getStreamExecutionEnvironment();
        SingleOutputStreamOperator<Object> map = env.addSource(new DggGetUserInfoFromMysql()).map(new MapFunction<DggUserInfo, Object>() {
            @Override
            public Object map(DggUserInfo dggUserInfo) throws Exception {
                Long id = dggUserInfo.getId();
                String loginName = dggUserInfo.getLoginName();
                String seatNumber = dggUserInfo.getSeatNumber();
                String voipUsername = dggUserInfo.getVoipUsername();
                String orgId = dggUserInfo.getOrgId();
//                if("102046".equals(loginName)){
//                    System.out.println(dggUserInfo);
//                    jedisCluster.setex("ibwb_userinfo_" + loginName, 60 * 60 * 24 * 7 ,id + "," + orgId);
//                    System.out.println(loginName + "已添加到redis");
//                    jedisCluster.setex("ibwb_userinfo_" + id,60 * 60 * 24 * 7 , loginName + "," + orgId);
//                    if (StringUtils.isNotEmpty(voipUsername)) {
//                        String s = id + "," + loginName + "," + orgId;
//                        jedisCluster.setex("ibwb_userinfo_phone_" + voipUsername, 60 * 60 * 24 * 7, s);
//                        System.out.println(voipUsername + "已添加到redis");
//                    }
//                    if (StringUtils.isNotEmpty(seatNumber)) {
//                        String s = id + "," + loginName + "," + orgId;
//                        jedisCluster.setex("ibwb_userinfo_phone_" + seatNumber, 60 * 60 * 24 * 7, s);
//                        System.out.println(seatNumber + "已添加到redis");
//                    }
//                }
                jedisCluster.setex("ibwb_userinfo_" + loginName, 60 * 60 * 24 * 7 ,id + "," + orgId);
                System.out.println(loginName + "已添加到redis");
                jedisCluster.setex("ibwb_userinfo_" + id,60 * 60 * 24 * 7 , loginName + "," + orgId);
                if (StringUtils.isNotEmpty(voipUsername)) {
                    jedisCluster.setex("ibwb_userinfo_phone_" + voipUsername, 60 * 60 * 24 * 7, id + "," + loginName + "," + orgId);
                    System.out.println(voipUsername + "已添加到redis");
                }
                if (StringUtils.isNotEmpty(seatNumber)) {
                    jedisCluster.setex("ibwb_userinfo_phone_" + seatNumber, 60 * 60 * 24 * 7, id + "," + loginName + "," + orgId);
                    System.out.println(seatNumber + "已添加到redis");
                }
                return "11111";
            }
        });
        env.execute("flink开始执行");
    }

    public void test() throws Exception {
        StreamExecutionEnvironment env = FlinkUtil.getStreamExecutionEnvironment();

        TypeInformation<?>[] fieldTypes = new TypeInformation<?>[]{
                BasicTypeInfo.STRING_TYPE_INFO,
                BasicTypeInfo.STRING_TYPE_INFO,
                BasicTypeInfo.STRING_TYPE_INFO
        };
        RowTypeInfo rowTypeInfo = new RowTypeInfo(fieldTypes);

        JDBCInputFormat jdbcInputFormat = JDBCInputFormat.buildJDBCInputFormat()
                .setDrivername("com.mysql.jdbc.Driver")
                .setDBUrl("jdbc:mysql://10.0.0.22:5506/db_iboss_uc")
                .setUsername("mochao")
                .setPassword("mochao@962540")
                .setQuery("select id,login_name,seat_number,voip_username from dgg_wrk_user_conf where locked = 0 ")
                .setRowTypeInfo(rowTypeInfo)
                .finish();
        env.createInput(jdbcInputFormat).map(new MapFunction<Row, Object>() {
            @Override
            public Object map(Row row) throws Exception {

                return null;
            }
        });
        env.execute("flink开始执行");
    }

}
