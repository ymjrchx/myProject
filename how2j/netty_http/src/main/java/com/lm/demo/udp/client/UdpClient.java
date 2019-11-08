package com.lm.demo.udp.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
 * @Auther: lm
 * @Date: 2019/1/22 09:31
 * @Description:
 */
public class UdpClient {

    public void run(int port,String context)throws Exception{

        EventLoopGroup group=new NioEventLoopGroup();
        try {
            Bootstrap b=new Bootstrap();
            b.group(group).channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    .handler(new UdpClientHandler());
            Channel ch=b.bind(5454).sync().channel();


            ch.writeAndFlush(
                    new DatagramPacket(
                            Unpooled.copiedBuffer(context, CharsetUtil.UTF_8),new InetSocketAddress("localhost", port)));

            if(!ch.closeFuture().await(15000)){
                System.out.println("查询超时");
            }

        } catch (Exception e) {
// TODO: handle exception
            e.printStackTrace();
        }finally{
           // group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {

        int port=7070;
        String context="测试一哈哈啊~~~~~~~~~";
        if(args.length>0){
            try {
                port=Integer.parseInt(args[0]);
            } catch (Exception e) {
// TODO: handle exception
                e.printStackTrace();
            }
        }
        new UdpClient().run(port,context);
    }

}
