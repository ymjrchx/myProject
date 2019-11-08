package com.example.rabbitmqtest.constant;

/**
 * @Author: dgg-linhongda
 * @Date: 2019/10/15 0015
 * @Description:
 */
public class DemonstrationConstant {

    /******************************点对点*******************************/
    public static final String directExchange = "direct_test_exchange";
    public static final String directQueue = "direct_test_queue";

    /******************************全局广播*******************************/
    public static final String fanoutExchange = "fanout_test_exchange";
    public static final String fanoutQueue01 = "fanout_test_queue01";
    public static final String fanoutQueue02 = "fanout_test_queue02";


    /******************************延迟队列*******************************/
    public static final String delayExchange = "delay_test_exchange";
    public static final String delayQueue = "delay_test_queue";
    public static final String delayDeadExchange = "delay_test_dead_exchage";
    public static final String delayDeadQueue = "delay_test_dead_queue";


    /******************************兼容旧版本*******************************/
    public static final String compatibleOldQueue = "net.dgg.platform.consumer.TestConsumer01";

}
