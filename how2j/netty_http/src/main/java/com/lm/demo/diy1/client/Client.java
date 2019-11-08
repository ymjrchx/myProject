package com.lm.demo.diy1.client;

import com.lm.demo.diy1.server.MyProtocolBean;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @Auther: lm
 * @Date: 2019/1/25 14:53
 * @Description:
 */
public class Client {
    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "8080"));
    static final int SIZE = Integer.parseInt(System.getProperty("size", "256"));

    public static void main(String[] args) throws Exception {

        // Configure the client.
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new MyProtocolEncoder());
                            ch.pipeline().addLast(new ClientHandler());
                        }
                    });

            ChannelFuture future = b.connect(HOST, PORT).sync();
            String messge="你好啊！哈哈哈！你是哪个哦啊";
            MyProtocolBean myProtocolBean=new MyProtocolBean(1,3,messge.length()*4,messge);
            future.channel().writeAndFlush(myProtocolBean).addListener(new ChannelFutureListener(){

                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (channelFuture.isSuccess()){
                        System.out.println("发送成功！！");
                    }else {
                        System.out.println("发送失败！！");
                    }
                }
            });
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }

}