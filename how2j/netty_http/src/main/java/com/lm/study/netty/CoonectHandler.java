package com.lm.study.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author chenxin
 * @date 2019/7/28 10:53
 */
public class CoonectHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx){
        System.out.println("Client " + ctx.channel().remoteAddress() +" connected");


    }

























}
