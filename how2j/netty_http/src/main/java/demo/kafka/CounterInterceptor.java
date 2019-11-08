package demo.kafka;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @author chenxin
 * @date 2019/8/20 19:15
 */
public class CounterInterceptor implements ProducerInterceptor<String,String> {
  private int errorCounter = 0;
  private int successCounter = 0;


    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        return record;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {

        if(exception == null){
            successCounter++;
        }else {
            errorCounter++;
        }
    }

    @Override
    public void close() {
        System.out.println("Successful sent: "+ successCounter);
        System.out.println("Failed sent: "+ errorCounter);

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
