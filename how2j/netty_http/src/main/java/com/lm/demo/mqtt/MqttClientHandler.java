package com.lm.demo.mqtt;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.mqtt.MqttFixedHeader;
import io.netty.handler.codec.mqtt.MqttMessage;
import io.netty.handler.codec.mqtt.MqttMessageType;
import io.netty.util.ReferenceCountUtil;

import static io.netty.channel.ChannelFutureListener.CLOSE_ON_FAILURE;
import static io.netty.handler.codec.mqtt.MqttQoS.AT_MOST_ONCE;

/**
 * @author chenxin
 * @date 2019/8/5 13:40
 */
@ChannelHandler.Sharable
public class MqttClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("channelRead0");
        System.out.println(msg);
        ctx.writeAndFlush(msg + "client dddd");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelReadComplete");
        ctx.flush();

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("exceptionCaught");
        cause.printStackTrace();
        if(ctx != null){
            ctx.close();
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive");
        MqttFixedHeader pingHeader = new MqttFixedHeader(
                MqttMessageType.PINGRESP,
                false,
                AT_MOST_ONCE,
                false,
                0);
        MqttMessage pingResp = new MqttMessage(pingHeader);
        ctx.writeAndFlush(pingResp);

    }

}
