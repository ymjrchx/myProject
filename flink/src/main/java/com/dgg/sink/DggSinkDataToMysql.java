package com.dgg.sink;


import com.dgg.Bean.DggBusInfo;
import com.dgg.common.Constant;
import com.dgg.util.DggDBCPUtil;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;

public class DggSinkDataToMysql extends RichSinkFunction<DggBusInfo> {

    private PreparedStatement ps;
    private Connection connection;

    public static final String SQL_INSERT_EMP_INFO =
            "replace into dgg_business_tmp" +
                    " (id,create_time,update_time,no,follower_id,customer_name,customer_no,customer_phone," +
                    " next_follow_time,business_status,will_drop_time,follower_login_name,follower_organization_id,table_name)" +
                    " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
    }

    public void invoke(DggBusInfo dggBusInfo, Context context) throws Exception {
        //组装数据，执行插入操作
        connection = DggDBCPUtil.getConnection();
        ps = connection.prepareStatement(SQL_INSERT_EMP_INFO);
        ps.setLong(1,dggBusInfo.getId());
        ps.setTimestamp(2,dggBusInfo.getCreateTime());
        ps.setTimestamp(3,dggBusInfo.getUpdateTime());
        ps.setString(4,dggBusInfo.getNo());
        ps.setObject(5,dggBusInfo.getUserId(), Types.BIGINT);
        ps.setString(6,dggBusInfo.getCustomerName());
        ps.setString(7,dggBusInfo.getCustomerNo());
        ps.setString(8,dggBusInfo.getCustomerPhone());
        ps.setTimestamp(9,dggBusInfo.getNextFollowTime());
        ps.setString(10,dggBusInfo.getStatus());
        ps.setTimestamp(11,dggBusInfo.getWillDropTime());
        ps.setString(12,dggBusInfo.getLoginName());
        ps.setObject(13,dggBusInfo.getOrgId(),Types.BIGINT);
        ps.setString(14,dggBusInfo.getTableName());
        ps.executeUpdate();
        DggDBCPUtil.close(connection,ps,null);
    }
}
