package com.example.rabbitmqtest;

import com.example.rabbitmqtest.constant.DemonstrationConstant;
import net.dgg.framework.tac.rabbit.dto.DggProducerParams;
import net.dgg.framework.tac.rabbit.dto.DggRabbitDTO;
import net.dgg.framework.tac.rabbit.producer.DggRabbitProducer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @Author: dgg-linhongda
 * @Date: 2019/10/15 0015
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartApplication.class)
public class RabbitmqProducerTest {

    /**
     * 注入生产者
     */
    @Autowired
    private DggRabbitProducer producer;

    /**
     * 点对点
     */
    @Test
    public void directExchangeEmptyTest(){
        DggRabbitDTO<String> rabbitDTO = new DggRabbitDTO();
        rabbitDTO.setMessageId(UUID.randomUUID().toString());
        rabbitDTO.setMessageDesc("点对点消息的描述信息");
        rabbitDTO.setDateTime(LocalDateTime.now());
        rabbitDTO.setData("oldMessageTest: jiuxiaoxi!");

        DggProducerParams producerParams = DggProducerParams.builder()
                .exchangeName("")
                .queueName("default_test_queue")
                .data(rabbitDTO)
                .build();

        boolean result = producer.sendOldVersionMessge(producerParams);
        Assert.assertTrue("消息发送失败!", result);

    }

    /**
     * 点对点
     */
    @Test
    public void directMessageTest(){
        DggRabbitDTO<String> rabbitDTO = new DggRabbitDTO();
        rabbitDTO.setMessageId(UUID.randomUUID().toString());
        rabbitDTO.setMessageDesc("点对点消息的描述信息");
        rabbitDTO.setDateTime(LocalDateTime.now());
        rabbitDTO.setData("directMessageTest: 点对点消息!");

        DggProducerParams producerParams = DggProducerParams.builder()
                .exchangeName(DemonstrationConstant.directExchange)
                .queueName(DemonstrationConstant.directQueue)
                .data(rabbitDTO)
                .build();

        boolean result = producer.sendDirectMessge(producerParams);
        Assert.assertTrue("消息发送失败!", result);

    }


    /**
     * 全局广播
     */
    @Test
    public void fanoutMessageTest(){
        DggRabbitDTO<String> rabbitDTO = new DggRabbitDTO();
        rabbitDTO.setMessageId(UUID.randomUUID().toString());
        rabbitDTO.setMessageDesc("全局广播描述信息");
        rabbitDTO.setDateTime(LocalDateTime.now());
        rabbitDTO.setData("fanoutMessageTest: 全局消息!");

        DggProducerParams producerParams = DggProducerParams.builder()
                .exchangeName(DemonstrationConstant.fanoutExchange)
                .data(rabbitDTO)
                .build();

        boolean result = producer.sendFanoutMessage(producerParams);
        Assert.assertTrue("消息发送失败!", result);
    }


    /**
     * 延迟消息
     */
    @Test
    public void delayMessageTest(){
        DggRabbitDTO<String> rabbitDTO = new DggRabbitDTO();
        rabbitDTO.setMessageId(UUID.randomUUID().toString());
        rabbitDTO.setMessageDesc("延迟消息描述信息");
        rabbitDTO.setDateTime(LocalDateTime.now());
        rabbitDTO.setData("delayMessageTest: 30秒延迟消息!");

        DggProducerParams producerParams = DggProducerParams.builder()
                .exchangeName(DemonstrationConstant.delayExchange)
                .queueName(DemonstrationConstant.delayQueue)
                .data(rabbitDTO)
                .deadExchangeName(DemonstrationConstant.delayDeadExchange)
                .deadQueueName(DemonstrationConstant.delayDeadQueue)
                // 延时30秒发到 死信队列 delay_test_dead_queue 中
                .delayedTime(30)
                .build();
        boolean flag = producer.sendDelayedMessge(producerParams);
        Assert.assertTrue("消息发送失败!",flag);
    }

    /**
     * 兼容消息
     */
    @Test
    public void oldMessageTest(){
        DggRabbitDTO<String> rabbitDTO = new DggRabbitDTO();
        rabbitDTO.setMessageId(UUID.randomUUID().toString());
        rabbitDTO.setMessageDesc("兼容旧版描述信息");
        rabbitDTO.setDateTime(LocalDateTime.now());
        rabbitDTO.setData("oldMessageTest: 兼容性消息!");

        DggProducerParams producerParams = DggProducerParams.builder()
                .queueName(DemonstrationConstant.compatibleOldQueue)
                .data(rabbitDTO)
                .build();
        boolean flag = producer.sendOldVersionMessge(producerParams);
        Assert.assertTrue("消息发送失败!",flag);
    }
}
