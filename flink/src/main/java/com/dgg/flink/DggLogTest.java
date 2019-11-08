package com.dgg.flink;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Classname DggLogTest
 * @Description 测试日志
 * @Date 2019/6/13 16:39
 * @Created by dgg-yanshun
 */
public class DggLogTest {

    private static Logger logger = LoggerFactory.getLogger(DggLogTest.class);

    public static void main(String[] args) {
        logger.error("11111");
        logger.info("222222");
    }
}
