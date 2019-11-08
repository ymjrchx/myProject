package com.lm.demo.div_v2.server;

import com.lm.demo.div_v2.entity.ConnectMessage;
import com.lm.demo.div_v2.entity.PublishMessage;
import com.lm.demo.div_v2.enums.MessageType;
import com.lm.demo.div_v2.service.MessageService;
import com.lm.demo.div_v2.service.impl.ConnectServiceImpl;
import com.lm.demo.div_v2.service.impl.PublishServiceImpl;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import javafx.print.Printer;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: lm
 * @Date: 2019/1/25 14:52
 * @Description:
 */
@ChannelHandler.Sharable
public class ServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 所有的链接对象存储 ConcurrentHashMap 线程安全 通过分段式锁实现 性能很高
     */
    private ConcurrentHashMap<String,Channel>connectMap=new ConcurrentHashMap<String, Channel>();

    private MessageService connectService;
    private MessageService publishService;
    public ServerHandler(MessageService connectService, MessageService publishService) {
        this.connectService=connectService;
        this.publishService=publishService;

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //直接转化成协议消息实体
        DiyMessageBean diyMessageBean = (DiyMessageBean) msg;

        switch (MessageType.get(diyMessageBean.getType())){
            case CONNECT:
                connectService.msgDispose(diyMessageBean,ctx,connectMap);
                break;
            case PING:
                break;
            case PUBLISH:
                publishService.msgDispose(diyMessageBean,ctx,connectMap);
                break;
            case PUBREC:
                break;
        }
//        int b= diyMessageBean.getQos();
//        int c= diyMessageBean.getType();
//
//        System.out.println(diyMessageBean.getContent());
//        System.out.println("消息qos"+b+"  "+"类型"+c +"心跳"+ diyMessageBean.getPing()+"用户"+diyMessageBean.getUserName());
//        String msgStr="收到了消息，现在发回给你";
//
//        DiyMessageBean diyMessageBean1 =new DiyMessageBean(0,3,0,"test",msgStr);
//        ctx.writeAndFlush(diyMessageBean1);
//        System.out.println(diyMessageBean.getFlag());
//        System.out.println(diyMessageBean.getLength());
//        System.out.println(diyMessageBean.getType());
//    }
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    private int lossConnectCount = 0;

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
//        System.out.println("已经5秒未收到客户端的消息了！");
//        if (evt instanceof IdleStateEvent){
//            IdleStateEvent event = (IdleStateEvent)evt;
//            if (event.state()== IdleState.READER_IDLE){
//                lossConnectCount++;
//                if (lossConnectCount>2){
//                    System.out.println("关闭这个不活跃通道！");
//                    ctx.channel().close();
//                }
//            }
//        }else {
//            super.userEventTriggered(ctx,evt);
//        }
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}