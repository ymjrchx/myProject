package com.dgg.test;


import org.apache.log4j.Logger;

/**
 * @Classname LogTest
 * @Description TODO
 * @Date 2019/5/28 10:25
 * @Created by dgg-yanshun
 */
public class LogTest {

    public static Logger logger= Logger.getLogger(LogTest.class);
    public static void main(String[] args) {
        logger.info("打印日志");
        logger.error("打印错误");
    }
}
