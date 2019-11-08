package com.lm.demo.div_v2.service.impl;

import com.lm.demo.div_v2.entity.PublishAckMessage;
import com.lm.demo.div_v2.entity.PublishMessage;
import com.lm.demo.div_v2.enums.MessageType;
import com.lm.demo.div_v2.server.DiyMessageBean;
import com.lm.demo.div_v2.service.MessageService;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: lm
 * @Date: 2019/5/30 16:41
 * @Description:
 */
public class PublishServiceImpl implements MessageService {



    @Override
    public void msgDispose(DiyMessageBean diyMessageBean, ChannelHandlerContext sender, ConcurrentHashMap<String, Channel> connectMap) {
        PublishMessage publishMessage= (PublishMessage) diyMessageBean.getContent();
        //回执消息
        Channel recevier=connectMap.get(publishMessage.getReceiver());
        publishAckMessage(sender,diyMessageBean);


        System.out.println("用户 "+publishMessage.getSender()+"发送了消息"+publishMessage.getContent()+"给"+publishMessage.getReceiver());
        if (recevier!=null){
            System.out.println("开始发送消息！");
            diyMessageBean.setUserName(publishMessage.getReceiver());
            recevier.writeAndFlush(diyMessageBean).addListener(new ChannelFutureListener(){
              @Override
              public void operationComplete(ChannelFuture channelFuture) throws Exception {
                  if (channelFuture.isSuccess()){
                      System.out.println("发送消息成功！！");
                  }else {
                      System.out.println("发送消息失败！！");
                  }
              }
          });
//            PublishMessage message=new PublishMessage();
//            DiyMessageBean push =new DiyMessageBean(1,MessageType.PUBLISH.getValue(),0,publishMessage.getReceiver(),publishMessage);
//
//            String messageContent =publishMessage.getContent();
//            message.setSender(diyMessageBean.getUserName());
//            message.setReceiver(publishMessage.getReceiver());
//            message.setContent(messageContent);
//            message.setTime(new Date().toString());
//            sender.writeAndFlush(push).addListener(new ChannelFutureListener(){
//                @Override
//                public void operationComplete(ChannelFuture channelFuture) throws Exception {
//                    if (channelFuture.isSuccess()){
//                        System.out.println("发送成功！！");
//                    }else {
//                        System.out.println("发送失败！！");
//                    }
//                }
//            });

        }else {
            publishMessage.setContent("对方暂时不在线");
            publishMessage.setReceiver(publishMessage.getSender());
            diyMessageBean.setContent(publishMessage);
            diyMessageBean.setType(MessageType.PUBACK.getValue());
            sender.writeAndFlush(diyMessageBean);        }
    }


    private  void  publishAckMessage(ChannelHandlerContext ctx,DiyMessageBean ben){
        DiyMessageBean diyMessageBean =new DiyMessageBean();
        PublishAckMessage ackMessage=new PublishAckMessage();
        ackMessage.setCode(0);
        diyMessageBean.setType(MessageType.PUBACK.getValue());
        diyMessageBean.setUserName(ben.getUserName());
        diyMessageBean.setQos(ben.getQos());
        diyMessageBean.setPing(ben.getPing());
        diyMessageBean.setContent(ackMessage);
        ctx.writeAndFlush(diyMessageBean);
    }

}
