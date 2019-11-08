package demo.hello;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author chenxin
 * @date 2019/4/3 12:46
 */
@Component
@ConfigurationProperties(prefix = "cms")
public class HelloSender1 {


    private String maxRequestSize;
    private String maxRequestsize;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String msg) {

        System.out.println(maxRequestsize);
        System.out.println(maxRequestSize);
        System.out.println(rabbitTemplate);
        String sendMsg = "hello1" + new Date() + Thread.currentThread().toString();
        System.out.println("Sender1 : " + sendMsg);
        rabbitTemplate.convertAndSend("directExchange","hello", msg);
        rabbitTemplate.convertAndSend("allMessage", "helloww", "allMagee" + msg);
    }

    public String getMaxRequestSize() {
        return maxRequestSize;
    }

    public void setMaxRequestSize(String maxRequestSize) {
        this.maxRequestSize = maxRequestSize;
    }

    public String getMaxRequestsize() {
        return maxRequestsize;
    }

    public void setMaxRequestsize(String maxRequestsize) {
        this.maxRequestsize = maxRequestsize;
    }
}
