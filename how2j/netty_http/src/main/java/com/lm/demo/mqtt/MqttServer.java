package com.lm.demo.mqtt;

import com.lm.demo.http.HttpServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.mqtt.MqttDecoder;
import io.netty.handler.codec.mqtt.MqttEncoder;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @Auther: lm
 * @Date: 2018/11/7 15:12
 * @Description:
 */
public class MqttServer {
    public static void main(String[] args) throws Exception {
        new MqttServer().start();
    }
    void  start(){
        ServerBootstrap b = new ServerBootstrap();
        NioEventLoopGroup m_bossGroup = new NioEventLoopGroup();//接收请求
        NioEventLoopGroup m_workerGroup = new NioEventLoopGroup();//处理接收的请求
        try {
                   b.group(m_bossGroup, m_workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("decoder", new MqttDecoder(2048)); //解码
                            pipeline.addLast("encoder", MqttEncoder.INSTANCE);//编码
                            pipeline.addLast("handler", new NettyMQTTHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 512)//缓存
                    .option(ChannelOption.SO_REUSEADDR, true)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                           .bind(18384).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
