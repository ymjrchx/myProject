import lombok.extern.slf4j.Slf4j;
import net.dgg.framework.tac.kafka.msg.model.PushReturnType;
import net.dgg.framework.tac.kafka.producer.DggKafkaProducer;
import net.tac.paas.test.Application;
import net.tac.paas.test.model.KafkaModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
public class PaasTestKafka {

    @Autowired
    private DggKafkaProducer kafkaProduce;

    @Test
    public void testKafka(){
        KafkaModel msg = new KafkaModel();
        msg.setName("张三");
        msg.setPhone("XXXXXXXXX");
        msg.setDesc("11111111111");
        //同步发送
        PushReturnType res = kafkaProduce.pushMsgSync("testTopic001", msg);
        log.info("***********************PushReturnType==={}", res);
        //异步发送
        msg.setName("李四");
        msg.setPhone("YYYYYYYYYY");
        msg.setDesc("2222222222222");
        kafkaProduce.pushMsgAsync("testTopic001", msg, (metadata, exception) -> {
            if(exception != null)
                log.error(exception.getMessage());
            else
                log.info("***********************pushMsgAsync success!!!");
        });
    }
}