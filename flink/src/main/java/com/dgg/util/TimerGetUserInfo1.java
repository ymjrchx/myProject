package com.dgg.util;

import com.mysql.cj.util.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Classname Timer
 * @Description TODO
 * @Date 2019/4/24 19:19
 * @Created by dgg-yanshun
 */
public class TimerGetUserInfo1 {

    public static Map<String,String> testMap1 = null;

    static{
        while(testMap1==null){
            init();
        }
        test1();
    }


    public static void test1() {
        System.out.println("调方法");
        Runnable runnable = () -> {
            Map<String,String> resultMap = new HashMap<>();
            Connection conn;
            try {
                conn = DggDBCPUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement("select * from test");
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    String name = rs.getString("name");
                    String id = rs.getString("id");
                    String testTime = rs.getString("test_time");
                    resultMap.put(id,name+":"+testTime);
                }
                testMap1 = resultMap;
                conn.close();
                ps.close();
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        };
        ScheduledExecutorService service = Executors
                .newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(runnable,0, 5, TimeUnit.SECONDS);
    }


    public static void init(){
        Connection conn ;
        try {
            Map<String,String> resultMap = new HashMap<>();
            conn = DggDBCPUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement("select * from test");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String name = rs.getString("name");
                String id = rs.getString("id");
                String testTime = rs.getString("test_time");
                resultMap.put(id,name+":"+testTime);
            }
            testMap1 = resultMap;
            conn.close();
            ps.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    public static void setUserInfo(Map<String,String> testMap){
//        if(testMap!=null){
//            testMap1 = testMap;
//        }
//    }
//    public static Map<String,String> getUserInfo(){
//        return testMap1;
//    }

}
