package com.lm.demo.diy1.client;

import com.lm.demo.diy1.server.MyProtocolBean;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Auther: lm
 * @Date: 2019/1/25 14:54
 * @Description:
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        MyProtocolBean myProtocolBean = new MyProtocolBean(5,2, "捶你啊啊啊啊啊啊你是哪个啊啊啊啊啊哈哈哈哈哈哈".length()*4, "捶你啊啊啊啊啊啊你是哪个啊啊啊啊啊哈哈哈哈哈哈");
        ctx.writeAndFlush(myProtocolBean);

    }
}