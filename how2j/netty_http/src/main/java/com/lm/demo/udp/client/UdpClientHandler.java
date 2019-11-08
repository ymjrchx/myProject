package com.lm.demo.udp.client;


import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

/**
 * @Auther: lm
 * @Date: 2019/1/22 09:32
 * @Description:
 */
public class UdpClientHandler extends SimpleChannelInboundHandler<DatagramPacket>{




    protected void messageReceived(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception {
// TODO Auto-generated method stub
        String response=packet.content().toString(CharsetUtil.UTF_8);
        if(response.startsWith("结果：")){
            System.out.println(response);
            ctx.close();
        }
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause)throws Exception{
        ctx.close();
        cause.printStackTrace();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception {
        String response=packet.content().toString(CharsetUtil.UTF_8);

            System.out.println("结果："+response);
            ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(response,CharsetUtil.UTF_8),packet.sender()));


    }
}

