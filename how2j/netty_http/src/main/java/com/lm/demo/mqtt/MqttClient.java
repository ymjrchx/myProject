package com.lm.demo.mqtt;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.mqtt.MqttDecoder;
import io.netty.handler.codec.mqtt.MqttEncoder;

import java.net.InetSocketAddress;

/**
 * @author chenxin
 * @date 2019/8/5 13:34
 */
public class MqttClient {
    public static void main(String[] args) throws InterruptedException {
        new MqttClient().start();

    }
    private void start() throws InterruptedException {
        MqttClientHandler clientHandler = new MqttClientHandler();
        EventLoopGroup eventExecutors = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        ChannelFuture sync = bootstrap.group(eventExecutors)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast("decoder", new MqttDecoder());
                        pipeline.addLast("encoder", MqttEncoder.INSTANCE);
                        pipeline.addLast("handler", clientHandler);

                    }
                }).connect(new InetSocketAddress("172.16.74.106", 18384)).sync();
        sync.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                System.out.println("succccc");
            }
        });


    }
}
