package com.lm.demo.div_v2.client;

import com.lm.demo.div_v2.codec.DiyMessageEncoder;
import com.lm.demo.div_v2.entity.ConnectAckMessage;
import com.lm.demo.div_v2.entity.ConnectMessage;
import com.lm.demo.div_v2.entity.PublishMessage;
import com.lm.demo.div_v2.enums.MessageType;
import com.lm.demo.div_v2.server.DiyMessageBean;
import com.lm.demo.div_v2.codec.DiyMessageDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: lm
 * @Date: 2019/1/25 14:53
 * @Description:
 */
public class Client {
    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "8080"));
    static final int SIZE = Integer.parseInt(System.getProperty("size", "256"));

    private static final int MAX_FRAME_LENGTH = 2048;  //最大长度
    private static final int LENGTH_FIELD_LENGTH = 4;  //长度字段所占的字节数
    private static final int LENGTH_FIELD_OFFSET = 26;  //长度偏移
    private static final int LENGTH_ADJUSTMENT = 0;
    private static final int INITIAL_BYTES_TO_STRIP = 0;


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
                            ch.pipeline().addLast(new DiyMessageEncoder());
                            ch.pipeline().addLast(new DiyMessageDecoder(MAX_FRAME_LENGTH,LENGTH_FIELD_OFFSET,LENGTH_FIELD_LENGTH,LENGTH_ADJUSTMENT,INITIAL_BYTES_TO_STRIP,false));
                            ch.pipeline().addLast(new ClientHandler());
                        }
                    });

            ChannelFuture future = b.connect(HOST, PORT).sync();
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入账号：");
            String account = sc.nextLine();

            System.out.println("请输入密码：");
            String password = sc.nextLine();

            ConnectMessage connectMessage=new ConnectMessage();
            connectMessage.setUserName(account);
            connectMessage.setPassword(password);
            DiyMessageBean diyMessageBean =new DiyMessageBean(1,MessageType.CONNECT.getValue(),0,account,connectMessage);
            future.channel().writeAndFlush(diyMessageBean).addListener(new ChannelFutureListener(){

                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (channelFuture.isSuccess()){
                        System.out.println("发送成功！！");
                    }else {
                        System.out.println("发送失败！！");
                    }
                }
            });


            PublishMessage publishMessage=new PublishMessage();
            DiyMessageBean push =new DiyMessageBean(1,MessageType.PUBLISH.getValue(),0,diyMessageBean.getUserName(),publishMessage);

            System.out.println("输入你要发送的消息：");
            String messageContent = sc.nextLine();
            publishMessage.setSender(diyMessageBean.getUserName());
            publishMessage.setReceiver("test2");
            publishMessage.setContent(messageContent);
            publishMessage.setTime(new Date().toString());
            future.channel().writeAndFlush(push).addListener(new ChannelFutureListener(){

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