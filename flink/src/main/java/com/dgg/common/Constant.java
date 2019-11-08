package com.dgg.common;

import java.util.Arrays;
import java.util.List;

public class Constant {
    private Constant() {}
    public static final String GROUP_ID = "ibwb_dataToday_tel";
    public static final String GROUP_ID_1 = "phoneTest";
    public static final List<String> GROUP_ID_LIST = Arrays.asList("test","test1");
    public static final String KAFKA_SERVERS = "172.16.0.217:9092,172.16.0.218:9092,172.16.0.219:9092";
//    public static final String KAFKA_SERVERS = "10.0.0.111:9092,10.0.0.112:9092,10.0.0.113:9092";//kafka的生产集群地址
    public static final String MYSQL_USERNAME = "root";//用户名
    public static final String MYSQL_PASSWORD = "root123456";//密码
    public static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";//连接驱动
    public static final String MYSQL_URL = "jdbc:mysql://172.16.0.205:3306/test?useUnicode=true&characterEncoding=UTF-8"
            ;//访问地址

    public static final String SQL_UPDATE_PHONE_INFO =
            " insert into dgg_wrk_jobdata(id, login_name, call_30s_counts, call_60s_counts, call_all_counts, data_Date) "+
                    " values(?,?,?,?,?,?) on DUPLICATE key " +
                    " update call_30s_counts = call_30s_counts + ?,call_60s_counts = call_60s_counts + ?, call_all_counts = call_all_counts + ?";//更新


    public static void main(String[] args) {
        System.out.println(GROUP_ID_LIST);
    }
}
