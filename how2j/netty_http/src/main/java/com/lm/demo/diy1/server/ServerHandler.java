package com.lm.demo.diy1.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Auther: lm
 * @Date: 2019/1/25 14:52
 * @Description:
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MyProtocolBean myProtocolBean = (MyProtocolBean) msg;  //直接转化成协议消息实体
        int b=myProtocolBean.getQos();
        int c=myProtocolBean.getType();
        System.out.println(myProtocolBean.getContent());
//        System.out.println(myProtocolBean.getFlag());
//        System.out.println(myProtocolBean.getLength());
//        System.out.println(myProtocolBean.getType());
//    }
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }
}