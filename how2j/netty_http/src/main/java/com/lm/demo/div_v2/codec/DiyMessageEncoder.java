package com.lm.demo.div_v2.codec;

import com.google.gson.Gson;
import com.lm.demo.div_v2.server.DiyMessageBean;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.Charset;

/**
 * @Auther: lm
 * @Date: 2019/1/25 14:53
 * @Description:
 */
public class DiyMessageEncoder extends MessageToByteEncoder<DiyMessageBean> {

    @Override
    protected void encode(ChannelHandlerContext ctx, DiyMessageBean msg, ByteBuf out) throws Exception {
        if(msg == null){
            throw new Exception("msg is null");
        }

        byte [] content=new Gson().toJson(msg.getContent()).getBytes(Charset.forName("UTF-8"));
//        byte [] content=msg.getContent().toString().getBytes(Charset.forName("UTF-8"));
        byte [] user=new byte[20];
        user=msg.getUserName().getBytes(Charset.forName("UTF-8"));
        out.writeByte(msg.getType());
        out.writeByte(msg.getQos());
        //账号长度te
        out.writeInt(user.length);
        out.writeBytes(user);
        //账号定长20  不到20 也要写满20
        out.writeBytes(new  byte[20-user.length]);
        out.writeInt(content.length);
        out.writeBytes(content);


    }
}