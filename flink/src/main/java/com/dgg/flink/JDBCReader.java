package com.dgg.flink;



import com.dgg.util.DggDBCPUtil;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @Classname JDBCReader
 * @Description TODO
 * @Date 2019/4/26 16:20
 * @Created by dgg-yanshun
 */
public class JDBCReader extends RichSourceFunction<Tuple3<String,String,String>> {
    private Connection connection = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private volatile boolean isRunning = true;

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        connection = DggDBCPUtil.getConnection();
        ps = this.connection.prepareStatement("select * from test");
    }


    @Override
    public void run(SourceContext<Tuple3<String,String,String>> sourceContext) {
        try {
            while (isRunning) {
                rs = ps.executeQuery();
                while(rs.next()){
                    Tuple3<String,String,String> userinfo = new Tuple3<>();
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    String testTime = rs.getString("test_time");
                    userinfo.f0 = id;
                    userinfo.f1 = name;
                    userinfo.f2 = testTime;
                    sourceContext.collect(userinfo);
                }
                Thread.sleep(60000);
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
