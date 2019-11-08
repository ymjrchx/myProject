package com.lm.demo.div_v2.client;

import com.lm.demo.div_v2.entity.ConnectAckMessage;
import com.lm.demo.div_v2.entity.PublishAckMessage;
import com.lm.demo.div_v2.entity.PublishMessage;
import com.lm.demo.div_v2.enums.MessageType;
import com.lm.demo.div_v2.server.DiyMessageBean;
import com.lm.demo.div_v2.service.impl.ConnectServiceImpl;
import com.lm.demo.div_v2.service.impl.PublishServiceImpl;
import io.netty.channel.*;

import java.util.Date;
import java.util.Scanner;

/**
 * @Auther: lm
 * @Date: 2019/1/25 14:54
 * @Description:
 */

@ChannelHandler.Sharable
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        DiyMessageBean diyMessageBean= (DiyMessageBean) msg;
        super.channelRead(ctx, msg);
        switch (MessageType.get(diyMessageBean.getType())){
            case CONNACK:
                ConnectAckMessage connectAckMessage= (ConnectAckMessage) diyMessageBean.getContent();
                System.out.println("状态："+connectAckMessage.getCode()+" 信息："+connectAckMessage.getContent() );
                break;
            case PUBACK:
                System.out.println("服务器收到发送的消息");
                break;
            case PUBLISH:
                PublishMessage publishMessage= (PublishMessage) diyMessageBean.getContent();
                System.out.println(publishMessage.getSender()+"给你发送了消息： "+ publishMessage.getContent());
                break;
        }

        }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

//        DiyMessageBean diyMessageBean =new DiyMessageBean(1,3,0,"test","哈哈哈");
//        ctx.writeAndFlush(diyMessageBean);



    }
}