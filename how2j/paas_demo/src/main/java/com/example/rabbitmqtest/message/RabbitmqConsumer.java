package com.example.rabbitmqtest.message;

import com.example.rabbitmqtest.constant.DemonstrationConstant;
import net.dgg.framework.tac.rabbit.annotation.DggRabbitListen;
import net.dgg.framework.tac.rabbit.constant.DggRabbitType;
import net.dgg.framework.tac.rabbit.dto.DggRabbitDTO;
import net.dgg.framework.tac.rabbit.exception.DggRabbitException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: dgg-linhongda
 * @Date: 2019/10/15 0015
 * @Description:
 */
@Component
public class RabbitmqConsumer {

    private static final Logger logger = LoggerFactory.getLogger(RabbitmqConsumer.class);

    /*************************************   点对点消息  ********************************/
//    @DggRabbitListen(
//            exchange = "",
//            queueName = "default_test_queue"
//    )
//    protected void handleDirectMessage(DggRabbitDTO data) throws DggRabbitException {
//        logger.info("点对点 -- 接收到的数据为: {}", data.getData());
//    }

    /*************************************   点对点消息  ********************************/
    @DggRabbitListen(
            exchange = DemonstrationConstant.directExchange,
            queueName = DemonstrationConstant.directQueue
    )
    protected void handleDirectMessage(DggRabbitDTO data) throws DggRabbitException {
        logger.info("点对点 -- 接收到的数据为: {}", data.getData());
//        throw new DggRabbitException("容易造成产线事, 后谨慎使用, 果自负!!!");
    }


    /*************************************  全局广播  ********************************/
    @DggRabbitListen(
            exchange = DemonstrationConstant.fanoutExchange,
            queueName = DemonstrationConstant.fanoutQueue01,
            rabbitType = DggRabbitType.FANOUT
    )
    protected void handleFanoutMessage01(DggRabbitDTO data) throws DggRabbitException {
        logger.info("全局广播01 -- 接收到的数据为: {}", data.getData());
    }

    @DggRabbitListen(
            exchange = DemonstrationConstant.fanoutExchange,
            queueName = DemonstrationConstant.fanoutQueue02,
            rabbitType = DggRabbitType.FANOUT
    )
    protected void handleFanoutMessage02(DggRabbitDTO data) throws DggRabbitException {
        logger.info("全局广播02 -- 接收到的数据为: {}", data.getData());
    }

//    /*************************************  延迟消息  ********************************/
    @DggRabbitListen(
            exchange = DemonstrationConstant.delayDeadExchange,
            queueName = DemonstrationConstant.delayDeadQueue,
            whetherDeadQueue = true
    )
    protected void handleDelayedQueue(DggRabbitDTO data) throws DggRabbitException {
        logger.info("延迟 -- 接收到的数据为: {}", data.getData());
    }
//
//    /*************************************  兼容性消息  ********************************/
    @DggRabbitListen(
        exchange = "occupying usage",
        queueName = "net.dgg.platform.consumer.TestConsumer01",
        whetherOldDeadQueue = true
    )
    protected void handleOldMessage(DggRabbitDTO data){
        logger.info("兼容旧版 -- 接收到的数据为: {}", data.getData());
    }
}
