package com.lm.demo.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;


/**
 * @Auther: lm
 * @Date: 2018/11/7 18:28
 * @Description:
 */
public class UdpServer {
    public static void main(String[] args) throws Exception {
        new UdpServer().start();
    }
    void start(){
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST,true)
                    .handler(new UdpServerHandler());
            bootstrap.bind(7070).sync().channel().closeFuture().await();

        } catch (InterruptedException e) {
            System.out.println("udp服务在7070端口启动失败！");
        }finally {
            eventLoopGroup.shutdownGracefully();
        }

    }
}
