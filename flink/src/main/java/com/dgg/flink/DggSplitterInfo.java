package com.dgg.flink;

import com.alibaba.fastjson.JSONObject;
import com.dgg.Bean.DggOrfInfo;
import com.dgg.config.DggDateUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;
import redis.clients.jedis.JedisCluster;

/**
 * @Classname DggSplitterInfo
 * @Description TODO
 * @Date 2019/5/9 15:00
 * @Created by dgg-yanshun
 */
public class DggSplitterInfo extends ProcessFunction<String, DggOrfInfo> {

    public static final OutputTag<DggOrfInfo> rejectedOrfTag = new OutputTag<DggOrfInfo>("rejected") {};

    @Override
    public void processElement(String line, Context context, Collector<DggOrfInfo> collector) throws Exception {
        DggOrfInfo dggOrfInfo = new DggOrfInfo();
        JSONObject jsonObject = JSONObject.parseObject(line);
        String tableName = jsonObject.getString("tableName"); //获取表名
        String eventType = jsonObject.getString("eventType");//获取数据操作类型
        dggOrfInfo.setEventType(eventType);
        dggOrfInfo.setTableName(tableName);
        JedisCluster jedisCluster = FlinkFromMysql.jedisCluster;
        if("orf_performance_profit".equals(tableName)){
            dggOrfInfo.setUserId(jsonObject.getLong("sign_user_id"));
            dggOrfInfo.setPerformanceAmount(jsonObject.getDouble("performance_amount"));
            String performanceTime = jsonObject.getString("performance_time");
            if(StringUtils.isNotEmpty(performanceTime)){
                dggOrfInfo.setDataDate(DggDateUtil.stringFormatDateTimeString(performanceTime,DggDateUtil.DEFAULT_DATETIME_FORMATTER, DggDateUtil.DEFAULT_DATE_FORMATTER));
            }
            dggOrfInfo.setIsComplete(jsonObject.getInteger("is_complete"));
            dggOrfInfo.setLoginName(jedisCluster.get("ibwb_userinfo_" + dggOrfInfo.getUserId()));
            context.output(rejectedOrfTag ,dggOrfInfo);
        }else{
            dggOrfInfo.setUserId(jsonObject.getLong("business_user_id"));
            String placeOrderTime = jsonObject.getString("place_order_time");
            dggOrfInfo.setDataDate(DggDateUtil.stringFormatDateTimeString(placeOrderTime, DggDateUtil.DEFAULT_DATETIME_FORMATTER, DggDateUtil.DEFAULT_DATE_FORMATTER));
            dggOrfInfo.setStatus(jsonObject.getString("status"));
            dggOrfInfo.setLoginName(jedisCluster.get("ibwb_userinfo_" + dggOrfInfo.getUserId()));
            collector.collect(dggOrfInfo);
        }
    }
}
