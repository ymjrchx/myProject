package com.lm.demo.diy.service.impl;

import com.lm.demo.diy.client.NamedThreadFactory;
import com.lm.demo.diy.handler.TransactionHandler;
import com.lm.demo.diy.utils.SocketUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: lm
 * @Date: 2019/1/25 09:49
 * @Description:
 */
public class DiyNettyClient {


    private EventLoopGroup workerGroup;


    private static volatile boolean isStarting = false;



    public static void main(String args[]){
        new DiyNettyClient().start();
    }

//    private ExecutorService threadPool = Executors.newFixedThreadPool(100,new NamedThreadFactory("receiver"));


     void start() {
        if (isStarting) {
            return;
        }
        isStarting = true;
//        nettyDistributeService.loadTxServer();

        String host = "172.16.3.156";
        int port = 6666;
        final int heart = 40;
        int delay = 1000;

        final TransactionHandler transactionHandler = new TransactionHandler(null,delay);
        workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap(); // (1)
            b.group(workerGroup); // (2)
            b.channel(NioSocketChannel.class); // (3)
            b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {

                    ch.pipeline().addLast("timeout", new IdleStateHandler(heart, heart, heart, TimeUnit.SECONDS));

                    ch.pipeline().addLast(new LengthFieldPrepender(4, false));
                    ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));

                    ch.pipeline().addLast(transactionHandler);
                }
            });
            // Start the client.
            final ChannelFuture future = b.connect(host, port); // (5)

            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (!channelFuture.isSuccess()) {
                        channelFuture.channel().eventLoop().schedule(new Runnable() {
                            @Override
                            public void run() {
                                isStarting = false;
                                start();
                            }
                        }, 5, TimeUnit.SECONDS);
                    }else {
//                        SocketUtils.sendMsg(channelFuture.channel(),"{\"status\":-13,\"message\":\"该客户已在团队中\",\"data\":null}");
                        channelFuture.channel().writeAndFlush(Unpooled.buffer().writeBytes("{\"status\":-13,\"message\":\"该客户已在团队中\",\"data\":null}".getBytes())).addListener(new ChannelFutureListener() {
                            @Override
                            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                                if (channelFuture.isSuccess()){
                                    System.out.println("发送成功！！");
                                }else {
                                    System.out.println("发送失败！！");
                                }
                            }
                        });
                    }
                }
            });


        } catch (Exception e) {
        }
    }



}
