package com.lm.study.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author chenxin
 * @date 2019/8/2 10:08
 */
public class EchoClient {
    private final String host;
    private final int prot;

    public EchoClient(String host, int prot) {
        this.host = host;
        this.prot = prot;
    }

    public static void main(String[] args) throws InterruptedException {
        new EchoClient("127.0.0.1",8888).start();
    }
    public void start() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        EchoClientHandler echoClientHandler = new EchoClientHandler();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, prot))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            System.out.println("localAddr: " + ch.localAddress() + " romoteAddr: " + ch.remoteAddress());

                            ch.pipeline().addLast(echoClientHandler);
                        }
                    });
            ChannelFuture f = b.connect().sync();
            System.out.println("localAddr: " + f.channel().localAddress() + " romoteAddr: " + f.channel().remoteAddress());

        }catch (Exception e){
            e.printStackTrace();
        }
    }






































}
