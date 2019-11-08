package net.tac.paas.test.consumer;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import net.dgg.framework.tac.kafka.annotation.DggKafkaListener;
import net.tac.paas.test.model.KafkaModel;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsume {

    @DggKafkaListener(topicName="testTopic001", groupId="testGroup001", consumerNum=1, threadNum=5)
    public void receive(KafkaModel msg) {
        log.info("**********************Kafka成功接收：{}", JSON.toJSONString(msg));
    }
}