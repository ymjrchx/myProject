package com.dgg.test;

import com.dgg.util.DggDBCPUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname Test1
 * @Description TODO
 * @Date 2019/4/28 9:57
 * @Created by dgg-yanshun
 */
public class Test1 {
    private Connection connection = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private volatile boolean isRunning = true;
    public static Map<String,String> testMap = null;

    public void test() throws Exception {
        while (isRunning) {
            connection = DggDBCPUtil.getConnection();
            ps = this.connection.prepareStatement("select * from test");
            rs = ps.executeQuery();
            Map<String,String> resultMap = new HashMap<>();
            while(rs.next()){
                String name = rs.getString("name");
                String id = rs.getString("id");
                String testTime = rs.getString("test_time");
                resultMap.put(id,name+":"+testTime);
            }
            testMap = resultMap;
            System.out.println(resultMap);
            resultMap.clear();
            connection.close();
            ps.close();
            rs.close();
            Thread.sleep(5000);
        }
    }

    public static void main(String[] args) throws Exception {
        new Test1().test();
        System.out.println(testMap);
    }
}
