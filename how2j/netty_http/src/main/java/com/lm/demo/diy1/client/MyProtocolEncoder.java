package com.lm.demo.diy1.client;

import com.lm.demo.diy1.server.MyProtocolBean;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.Charset;

/**
 * @Auther: lm
 * @Date: 2019/1/25 14:53
 * @Description:
 */
public class MyProtocolEncoder extends MessageToByteEncoder<MyProtocolBean> {

    @Override
    protected void encode(ChannelHandlerContext ctx, MyProtocolBean msg, ByteBuf out) throws Exception {
        if(msg == null){
            throw new Exception("msg is null");
        }
        out.writeByte(msg.getType());
        out.writeByte(msg.getQos());
        out.writeInt(msg.getLength());
        out.writeBytes(msg.getContent().getBytes(Charset.forName("UTF-8")));
    }
}