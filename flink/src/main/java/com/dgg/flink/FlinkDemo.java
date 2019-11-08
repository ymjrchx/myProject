package com.dgg.flink;

import com.alibaba.fastjson.JSONObject;
import com.dgg.Bean.DggOrfInfo;
import com.dgg.Bean.DggPhoneInfo;
import com.dgg.config.DggDateUtil;
import com.dgg.util.FlinkUtil;
import com.dgg.util.TimerGetUserInfo;
import com.mysql.cj.util.StringUtils;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumerBase;

import java.util.Map;

public class FlinkDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = FlinkUtil.getStreamExecutionEnvironment();
        FlinkKafkaConsumer011<String> consumer = new FlinkKafkaConsumer011<>("ibwb_db_iboss_orf",
                new SimpleStringSchema(),
                FlinkUtil.getProperties());
        FlinkKafkaConsumerBase<String> base = consumer.setStartFromLatest();
        env.addSource(base).print();
//        SingleOutputStreamOperator<DggOrfInfo> map = env.addSource(base)
//                .filter((FilterFunction<String>) line -> line.matches("\\{.+\\}")).map(new DggSplitterOrfInfo());
        env.execute();
    }

    private static class SplitterPhoneInfo implements MapFunction<String, DggPhoneInfo> {

        private Integer callsec;
        private String dataDate;
        @Override
        public DggPhoneInfo map(String line) {
            DggPhoneInfo result = new DggPhoneInfo();
            JSONObject jsonObject = JSONObject.parseObject(line);
            String tableName = jsonObject.getString("tableName");
            if(tableName.contains("zh")){
                //异常可能要处理，日期为空的情况
                callsec = jsonObject.getInteger("callsec");
                String callTime = jsonObject.getString("calltime");
                dataDate = DggDateUtil.stringFormatDateTimeString(callTime, DggDateUtil.DEFAULT_DATETIME_FORMATTER,"yyyy-MM-dd");
            }else{
                callsec = jsonObject.getInteger("billsec");
                String callTime = jsonObject.getString("calldate");
                dataDate = DggDateUtil.stringFormatDateTimeString(callTime,DggDateUtil.DEFAULT_TIMESTAMP_SECOND,"yyyy-MM-dd");
            }
            String src1 = jsonObject.getString("src");
            String dst1 = jsonObject.getString("dst");
            Long offset = jsonObject.getLong("offset");

            System.out.println(tableName+","+callsec+","+src1+","+dst1+","+offset);
            Long id = null;
            String loginName = null;
            Map<String, String> userInfo = TimerGetUserInfo.getUserInfo();
            String src = userInfo.getOrDefault(jsonObject.getString("src"), null);
            String dst = userInfo.getOrDefault(jsonObject.getString("dst"), null);
            if(!StringUtils.isNullOrEmpty(src)){
                String[] split = src.split(":");
                id = Long.valueOf(split[0]);
                loginName = split[1];
            }else{
                if(!StringUtils.isNullOrEmpty(dst)){
                    String[] split = dst.split(":");
                    id = Long.valueOf(split[0]);
                    loginName = split[1];
                }
            }
            result.setId(id);
            result.setLoginName(loginName);
            result.setCall_all_counts(callsec>0 ? 1 :0);
            result.setCall_30s_counts(callsec>30 ? 1 :0);
            result.setCall_60s_counts(callsec>60 ? 1 :0);
            result.setDataDate(dataDate);
            return result;
        }
    }

    private static class SplitterPhoneInfo1 implements MapFunction<String, String> {

        @Override
        public String map(String line) {
            JSONObject jsonObject = JSONObject.parseObject(line);
            String tableName = jsonObject.getString("tableName"); //获取表名
            Long createrId = jsonObject.getLong("creater_id");
            if("orf_performance_profit".equals(tableName)&& createrId == 7753101190567870464L){
                return line;
            }
            return null;
        }
    }
}
