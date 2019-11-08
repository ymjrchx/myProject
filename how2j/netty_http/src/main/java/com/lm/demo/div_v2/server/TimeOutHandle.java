package com.lm.demo.div_v2.server;


import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by admin on 2017/10/23.
 */
public class TimeOutHandle extends IdleStateHandler {

    private String account;
    private ConcurrentHashMap<String,Channel> connectMap;
    public TimeOutHandle(int readerIdleTimeSeconds, int writerIdleTimeSeconds, int allIdleTimeSeconds, String account, ConcurrentHashMap concurrentMap) {
        super(readerIdleTimeSeconds, writerIdleTimeSeconds, allIdleTimeSeconds);
        this.account=account;
        this.connectMap=concurrentMap;

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        connectMap.remove(account);
        System.out.print("没有再次收到心跳,客服端链接被关闭");
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("收到心跳连接: " + msg);
    }

}
