package com.dgg.test;

import com.mysql.cj.util.StringUtils;

import java.sql.*;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Classname Timer
 * @Description TODO
 * @Date 2019/4/24 19:19
 * @Created by dgg-yanshun
 */
public class Timer {

    public static Map<String,String> testMap1;
    static{
        while(testMap1==null){
            init();
        }
        test1();
    }


    public static void test1() {
        System.out.println("调方法");
        Runnable runnable = () -> {
            Map<String,String> testMap1 = new HashMap<>();
            Connection conn = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://172.16.0.25:5506/iboss_workbench?useUnicode=true&characterEncoding=UTF-8","iboss_workbench","iboss_workbench");
                PreparedStatement ps = conn.prepareStatement("select id,login_name,seat_number from sys_user_info where locked = 0 and (seat_number is not null or seat_number!='')");
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    String loginName = rs.getString("login_name");
                    Long id = rs.getLong("id");
                    String seatNumber = rs.getString("seat_number");
                    if(!(StringUtils.isNullOrEmpty(loginName) || StringUtils.isNullOrEmpty(seatNumber))){
                        testMap1.put(seatNumber,id+":"+loginName);
                    }
                }
                setUserInfo(testMap1);
                conn.close();
                ps.close();
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        };
        ScheduledExecutorService service = Executors
                .newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(runnable,1, 1, TimeUnit.HOURS);
    }


    public static void init(){
        Map<String,String> testMap1 = new HashMap<>();
        Connection conn ;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://172.16.0.25:5506/iboss_workbench?useUnicode=true&characterEncoding=UTF-8","iboss_workbench","iboss_workbench");
            PreparedStatement ps = conn.prepareStatement("select id,login_name,seat_number from sys_user_info where locked = 0 and (seat_number is not null or seat_number!='')");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String loginName = rs.getString("login_name");
                Long id = rs.getLong("id");
                String seatNumber = rs.getString("seat_number");
                if(!(StringUtils.isNullOrEmpty(loginName) || StringUtils.isNullOrEmpty(seatNumber))){
                    testMap1.put(seatNumber,id+":"+loginName);
                }
            }
            setUserInfo(testMap1);
            conn.close();
            ps.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void setUserInfo(Map<String,String> testMap){
        if(testMap!=null){
            testMap1 = testMap;
        }
    }

}
