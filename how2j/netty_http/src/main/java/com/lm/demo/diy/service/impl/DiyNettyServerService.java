package com.lm.demo.diy.service.impl;


import com.lm.demo.diy.handler.TxCoreServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by lorne on 2017/6/30.
 */

public class DiyNettyServerService {


//    @Autowired
//    private NettyService nettyService;


    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;

    private TxCoreServerHandler txCoreServerHandler;

    private ExecutorService threadPool = Executors.newFixedThreadPool(100);





    public void start() {
        final int heartTime = 40; //心跳
        txCoreServerHandler = new TxCoreServerHandler(threadPool,null);
        bossGroup = new NioEventLoopGroup(50); // (1)
        workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast("timeout", new IdleStateHandler(heartTime, heartTime, heartTime, TimeUnit.SECONDS));
                            ch.pipeline().addLast(new LengthFieldPrepender(4, false));
                            ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                            ch.pipeline().addLast(txCoreServerHandler);
                        }
                    });

            // Start the server.
            b.bind(6666);

        } catch (Exception e) {
            // Shut down all event loops to terminate all threads.
            e.printStackTrace();
        }
    }


    public void close() {

    }


    public static void main(String args[]){
        new DiyNettyServerService().start();
    }


}
