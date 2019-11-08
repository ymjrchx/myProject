package com.dgg.flink;

import com.dgg.util.DggDBCPUtil;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname JDBCReader1
 * @Description TODO
 * @Date 2019/4/28 9:33
 * @Created by dgg-yanshun
 */
public class JDBCReader1 extends RichSourceFunction<Map<String,String>> {
    private Connection connection = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private volatile boolean isRunning = true;
    public static Map<String,String> testMap = null;

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        connection = DggDBCPUtil.getConnection();
        ps = this.connection.prepareStatement("select * from test");
    }


    @Override
    public void run(SourceContext<Map<String, String>> sourceContext)  {
        try {
            while (isRunning) {
                rs = ps.executeQuery();
                Map<String,String> resultMap = new HashMap<>();
                while(rs.next()){
                    String name = rs.getString("name");
                    String id = rs.getString("id");
                    String testTime = rs.getString("test_time");
                    resultMap.put(id,name+":"+testTime);
                }
                testMap = resultMap;
                sourceContext.collect(resultMap);
                resultMap.clear();
                Thread.sleep(60000*60);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cancel() {
        try {
            super.close();
            if (connection != null) {
                connection.close();
            }
            if (ps != null) {
                ps.close();
            }
            if(rs!=null){
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        isRunning = false;
    }
}
