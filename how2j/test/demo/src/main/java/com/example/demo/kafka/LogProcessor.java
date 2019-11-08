package com.example.demo.kafka;

import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;

import java.util.Set;

/**
 * @author chenxin
 * @date 2019/8/20 19:51
 */
public class LogProcessor implements Processor<byte[],byte[]> {

private ProcessorContext context;
    @Override
    public void init(ProcessorContext context) {
        this.context =context;
    }

    @Override
    public void process(byte[] key, byte[] value) {
        String input = new String(value);
        if(input.contains(">>>")){
            System.out.println("this source: "+input);
            input = input.split(">>>")[1].trim();
            context.forward("logProcessor".getBytes(),input.getBytes());

        }else {
            context.forward("logProcessor".getBytes(),input.getBytes());
        }

    }

    @Override
    public void close() {

    }
}
