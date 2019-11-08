package com.example.demo;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenxin
 * @date 2019/4/3 10:29
 */
@Configuration
public class RabbitTest {

    final static String queueName = "hello";
    @Bean
    public Queue allMessage(){
        return new Queue("allMessage");
    }

    @Bean
    public Queue helloQueue(){
        return new Queue("helloQueue");
    }

    @Bean
    public Queue helloQueue1(){
        return new Queue("helloQueue1");
    }

    @Bean
    public Queue userQueue(){
        return new Queue("user");
    }

    //topic Exchange
    @Bean
    public Queue queueMessage(){
        return new Queue("topic.message");
    }
    @Bean
    public Queue queueMessages(){
        return new Queue("topic.messages");
    }

    //Fanout Exchange的队列
    @Bean
    public Queue AMessage(){
        return new Queue("fanout.A");
    }

    @Bean
    public Queue BMessage(){
        return new Queue("fanout.B");
    }

    @Bean
    public Queue CMessage(){
        return new Queue("fanout.C");
    }

    // Exchange

    @Bean
    TopicExchange exchange(){
        return new TopicExchange("exchange");
    }

    @Bean
    DirectExchange directExchange(){
        return new DirectExchange("directExchange");
    }

    @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    TopicExchange allExChange(){
        return new TopicExchange("allMessage");
    }



    @Bean
    Binding bindingAllExChangeMessage(Queue allMessage,TopicExchange allExChange){
        return BindingBuilder.bind(allMessage).to(allExChange).with("#");
    }

    /**
     * 将队列topic.message与exchange绑定，binding_key为topic.message,就是完成匹配
     * @param queueMessage
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessage(Queue queueMessage,TopicExchange exchange){
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }



    @Bean
    Binding bindingExchangeMessages(Queue queueMessages,TopicExchange exchange){
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
    }


    @Bean
    Binding bindingExchangeA(Queue AMessage,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(AMessage).to(fanoutExchange);
    }



    @Bean
    Binding bindingExchangeB(Queue BMessage,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(BMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeC(Queue CMessage,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(CMessage).to(fanoutExchange);
    }


    @Bean
    Binding bindingDefaultExChange(Queue helloQueue, DirectExchange directExchange){
        return BindingBuilder.bind(helloQueue).to(directExchange).with("hello");
    }

    @Bean
    Binding bindingDefaultExChange1(Queue helloQueue1, DirectExchange directExchange){
        return BindingBuilder.bind(helloQueue1).to(directExchange).with("hello");
    }
}
