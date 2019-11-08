package com.dgg.util;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Classname DggDBCPUtil
 * @Description dbcp数据库连接池，使用druid连接池会报错，暂时没找到原因
 * @Date 2019/4/22 13:28
 * @Created by dgg-yanshun
 */
public class DggDBCPUtil {
    private static Properties properties = new Properties();
    private static DataSource dataSource;
    static{
        try {
            properties.load(DggDBCPUtil.class.getClassLoader().getResourceAsStream("dbcp.properties"));
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void close(Connection conn, PreparedStatement ps, ResultSet rs){
        //关闭连接和释放资源
        try {
            if (conn != null) {
                conn.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
