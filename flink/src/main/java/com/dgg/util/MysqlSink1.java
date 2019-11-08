package com.dgg.util;

import com.dgg.common.Constant;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class MysqlSink1 extends RichSinkFunction<String> {

    private Connection connection;
    private PreparedStatement preparedStatement;

    public void invoke(String value) throws Exception {
        Class.forName(Constant.MYSQL_DRIVER);
        connection = DriverManager.getConnection(Constant.MYSQL_URL, Constant.MYSQL_USERNAME, Constant.MYSQL_PASSWORD);
        String sql = "insert into orders(order_price) values(?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, value);
        preparedStatement.executeUpdate();
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

}
