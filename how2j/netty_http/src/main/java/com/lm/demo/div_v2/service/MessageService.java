package com.lm.demo.div_v2.service;

import com.lm.demo.div_v2.server.DiyMessageBean;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: lm
 * @Date: 2019/5/30 16:39
 * @Description:
 */
public interface MessageService {

    void msgDispose(DiyMessageBean diyMessageBean,ChannelHandlerContext ctx, ConcurrentHashMap<String, Channel> connectMap);
}
