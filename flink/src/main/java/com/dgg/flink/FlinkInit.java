package com.dgg.flink;

import com.dgg.Bean.DggBusInfo;
import com.dgg.sink.DggSinkDataToMysql;
import com.dgg.util.FlinkUtil;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @Classname FlinkInit
 * @Description 初始化商机数据
 * @Date 2019/5/21 8:56
 * @Created by dgg-yanshun
 */
public class FlinkInit {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = FlinkUtil.getStreamExecutionEnvironment();
        DataStreamSource<DggBusInfo> busInfo = env.addSource(new DggGetBusInfoFromMysql());
        busInfo.print();
        busInfo.addSink(new DggSinkDataToMysql());
        env.execute();
    }
}
