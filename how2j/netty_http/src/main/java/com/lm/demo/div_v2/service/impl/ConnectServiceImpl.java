package com.lm.demo.div_v2.service.impl;

import com.lm.demo.div_v2.entity.ConnectAckMessage;
import com.lm.demo.div_v2.entity.ConnectMessage;
import com.lm.demo.div_v2.enums.MessageType;
import com.lm.demo.div_v2.server.DiyMessageBean;
import com.lm.demo.div_v2.server.TimeOutHandle;
import com.lm.demo.div_v2.service.MessageService;
import com.sun.xml.internal.ws.api.model.MEP;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: lm
 * @Date: 2019/5/30 16:40
 * @Description:
 */
public class ConnectServiceImpl  implements MessageService {
    @Override
    public void msgDispose(DiyMessageBean diyMessageBean, ChannelHandlerContext ctx, ConcurrentHashMap<String, Channel> connectMap) {
        Channel context=connectMap.get(diyMessageBean.getUserName());
        if (context!=null){
            connectMap.remove(diyMessageBean.getUserName());
            context.close();
        }
        connectMap.put(diyMessageBean.getUserName(),ctx.channel());
        ConnectMessage connectMessage= (ConnectMessage) diyMessageBean.getContent();
        if (connectMessage.getUserName()!=null&&connectMessage.getPassword()!=null){
            System.out.println("用户"+connectMessage.getUserName()+"登录了");
        }
        ConnectAckMessage connectAckMessage=new ConnectAckMessage();
        connectAckMessage.setCode(0);
        connectAckMessage.setContent("登录成功！");
        ctx.pipeline().addFirst("idleStateHandler", new TimeOutHandle(60,
                0, 0,diyMessageBean.getUserName(),connectMap));
        diyMessageBean.setContent(connectAckMessage);
        diyMessageBean.setType(MessageType.CONNACK.getValue());
        ctx.writeAndFlush(diyMessageBean);
    }
}
