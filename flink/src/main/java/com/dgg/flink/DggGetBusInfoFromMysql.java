package com.dgg.flink;

import com.dgg.Bean.DggBusInfo;
import com.dgg.Bean.DggUserInfo;
import com.dgg.util.DggDBCPIbossUtil;
import com.dgg.util.DggDBCPUtil;
import com.dgg.util.DggRedisUtil;
import org.apache.commons.lang3.StringUtils;
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
public class DggGetBusInfoFromMysql extends RichSourceFunction<DggBusInfo> {

    private Logger logger = LoggerFactory.getLogger(DggGetBusInfoFromMysql.class);

    private Connection connection = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        connection = DggDBCPIbossUtil.getConnection();
        ps = this.connection.prepareStatement( "select id,follower_id,business_status,no,customer_no,customer_name,customer_phone,will_drop_time,next_follow_time,follower_organization_id,create_time,update_time from bus_business_emp ");
    }

    @Override
    public void run(SourceContext<DggBusInfo> sourceContext) {
        DggBusInfo dggBusInfo = new DggBusInfo();
        try {
            rs = ps.executeQuery();
            while(rs.next()){
                dggBusInfo.setId(rs.getLong("id"));
                dggBusInfo.setCreateTime(rs.getTimestamp("create_time"));
                dggBusInfo.setUpdateTime(rs.getTimestamp("update_time"));
                dggBusInfo.setStatus(rs.getString("business_status"));
                long userId = rs.getLong("follower_id");
                String userInfo = DggRedisUtil.getloginNameFromRedis(userId);
                if(StringUtils.isNotEmpty(userInfo)){
                    String[] split = userInfo.split(",",-1);
                    dggBusInfo.setLoginName(split[0]);
                }
                dggBusInfo.setUserId(rs.getLong("follower_id"));
                dggBusInfo.setNo(rs.getString("no"));
                dggBusInfo.setCustomerNo(rs.getString("customer_no"));
                dggBusInfo.setCustomerName(rs.getString("customer_name"));
                dggBusInfo.setCustomerPhone(rs.getString("customer_phone"));
                dggBusInfo.setWillDropTime(rs.getTimestamp("will_drop_time"));
                dggBusInfo.setNextFollowTime(rs.getTimestamp("next_follow_time"));
                dggBusInfo.setOrgId(rs.getLong("follower_organization_id"));
                dggBusInfo.setTableName("bus_business_emp");
                sourceContext.collect(dggBusInfo);
            }
        } catch (Exception e) {
            logger.error("获取商机表错误：" + e.getMessage());
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
