package com.dgg.flink;

import com.alibaba.fastjson.JSONObject;
import com.dgg.Bean.DggOrfInfo;
import com.dgg.config.DggDateUtil;
import org.apache.flink.api.common.functions.MapFunction;

/**
 * @Classname DggSplitterBusInfo
 * @Description 拆分工作数据，获取时间戳和表名
 * @Date 2019/5/7 16:30
 * @Created by dgg-yanshun
 */
public class DggSplitterOrfInfo implements MapFunction<String, DggOrfInfo> {


    @Override
    public DggOrfInfo map(String line) {
        DggOrfInfo dggOrfInfo = new DggOrfInfo();
        JSONObject jsonObject = JSONObject.parseObject(line);
        String tableName = jsonObject.getString("tableName"); //获取表名
        String eventType = jsonObject.getString("eventType");//获取数据操作类型
        //如果表名为orf_performance_profit，并且数据状态为insert，就不计算
        if(!("orf_performance_profit".equals(tableName) && "INSERT".equals(eventType))){
            dggOrfInfo.setId(jsonObject.getLong("id"));
            dggOrfInfo.setEventType(eventType);
            dggOrfInfo.setTableName(tableName);
            if("orf_order".equals(tableName)){
                dggOrfInfo.setUserId(jsonObject.getLong("business_user_id"));
                String placeOrderTime = jsonObject.getString("place_order_time");
                dggOrfInfo.setDataDate(DggDateUtil.stringFormatDateTimeString(placeOrderTime, DggDateUtil.DEFAULT_DATETIME_FORMATTER, DggDateUtil.DEFAULT_DATE_FORMATTER));
                dggOrfInfo.setStatus(jsonObject.getString("status"));
            }else{
                dggOrfInfo.setUserId(jsonObject.getLong("sign_user_id"));
                dggOrfInfo.setPerformanceAmount(jsonObject.getDouble("performance_amount"));
                String performanceTime = jsonObject.getString("performance_time");
                dggOrfInfo.setDataDate(DggDateUtil.stringFormatDateTimeString(performanceTime,DggDateUtil.DEFAULT_DATETIME_FORMATTER, DggDateUtil.DEFAULT_DATE_FORMATTER));
                dggOrfInfo.setIsComplete(jsonObject.getInteger("is_complete"));
            }
            return dggOrfInfo;
        }
        return null;
    }
}
