package com.lm.demo.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * @Auther: lm
 * @Date: 2018/11/7 14:45
 * @Description:
 */
public class HttpServer {
    private final int port =8585;
    public static void main(String[] args) throws Exception {
        new HttpServer().start();
    }
    public void start() throws Exception {
        ServerBootstrap b = new ServerBootstrap();
        NioEventLoopGroup boss = new NioEventLoopGroup();//接收请求
        NioEventLoopGroup work = new NioEventLoopGroup();//处理接收的请求
        b.group(boss,work)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch)
                            throws Exception {
                        System.out.println("initChannel ch:" + ch);
                        ch.pipeline()
                                .addLast("decoder", new HttpRequestDecoder())   // 1 http请求解码
                                .addLast("encoder", new HttpResponseEncoder())  // 2 http响应编码
                               .addLast("aggregator", new HttpObjectAggregator(512 * 1024))    // 3 聚合器
                                .addLast("handler", new HttpProcessHandle());        // 4 处理类
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 128) // determining the number of connections queued
                .childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE);
        b.bind(port).sync();
    }
}
