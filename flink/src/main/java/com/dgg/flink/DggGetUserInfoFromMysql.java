package com.dgg.flink;

import com.dgg.Bean.DggUserInfo;
import com.dgg.util.DggDBCPUtil;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 * 为了能够一个小时获取一次mysql里最新的用户信息，使用flink实现的方式不能做到（每次都会报线程打断错误），所以自己实现
 */
public class DggGetUserInfoFromMysql extends RichSourceFunction<DggUserInfo> {

    private Logger logger = LoggerFactory.getLogger(DggGetUserInfoFromMysql.class);

    private Connection connection = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        connection = DggDBCPUtil.getConnection();
        ps = this.connection.prepareStatement( "select id,login_name,seat_number,voip_username,level_5 from dgg_wrk_user_conf where locked = 0 ");
    }

    @Override
    public void run(SourceContext<DggUserInfo> sourceContext) {
        DggUserInfo userInfo = new DggUserInfo();
        try {
            rs = ps.executeQuery();
            while(rs.next()){
                userInfo.setId(rs.getLong("id"));
                userInfo.setLoginName(rs.getString("login_name"));
                userInfo.setSeatNumber(rs.getString("seat_number"));
                userInfo.setVoipUsername(rs.getString("voip_username"));
                userInfo.setOrgId(rs.getString("level_5"));
                sourceContext.collect(userInfo);
            }
        } catch (Exception e) {
            logger.error("获取用户信息表错误：" + e.getMessage());
        }finally {
            cancel();
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
    }
}
