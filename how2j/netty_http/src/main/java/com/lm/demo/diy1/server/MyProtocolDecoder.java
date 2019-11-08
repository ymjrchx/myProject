package com.lm.demo.diy1.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @Auther: lm
 * @Date: 2019/1/25 14:50
 * @Description:
 */
public class MyProtocolDecoder extends LengthFieldBasedFrameDecoder {

    private static final int HEADER_SIZE = 6;



//    maxFrameLength：单个包最大的长度，这个值根据实际场景而定，我设置的是1024，固然我的心跳包不大，但是其他包可能比较大。
//    lengthFieldOffset：表示数据长度字段开始的偏移量，比如上面的协议，lengthFieldOffset应该取值为5，因为数据长度之前有2个字节的包头，1个字节的功能ID，2个字节的设备ID,一共为5。
//    lengthFieldLength：数据长度字段的所占的字节数，上面的协议中写的是2个字节，所以取值为2
//    lengthAdjustment：这里取值为10=7(系统时间) + 1（校验码）+ 2 (包尾)，如果这个值取值为0，试想一下，解码器跟数据长度字段的取值（这里数据长度内容肯定是1），只向后取一个字节，肯定不对。
//            （lengthAdjustment + 数据长度取值 = 数据长度字段之后剩下包的字节数）
//    initialBytesToStrip：表示从整个包第一个字节开始，向后忽略的字节数，我设置为0，本来可以忽略掉包头的两个字节（即设置为2），但是，实际项目中，需要校验包头取值是否为AA55，来判断包的合法性。

    /**
     *
     * @param maxFrameLength  帧的最大长度
     * @param lengthFieldOffset length字段偏移的地址
     * @param lengthFieldLength length字段所占的字节长
     * @param lengthAdjustment 修改帧数据长度字段中定义的值，可以为负数 因为有时候我们习惯把头部记入长度,若为负数,则说明要推后多少个字段
     * @param initialBytesToStrip 解析时候跳过多少个长度
     * @param failFast 为true，当frame长度超过maxFrameLength时立即报TooLongFrameException异常，为false，读取完整个帧再报异
     */

    public MyProtocolDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment, int initialBytesToStrip, boolean failFast) {

        super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip, failFast);

    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        //在这里调用父类的方法,实现指得到想要的部分,我在这里全部都要,也可以只要body部分
        in = (ByteBuf) super.decode(ctx,in);

        if(in == null){
            return null;
        }
        if(in.readableBytes()<HEADER_SIZE){
            throw new Exception("字节数不足");
        }
        System.out.println("读取头部之前："+in.readableBytes());
        //读取type字段
        byte type = in.readByte();
        System.out.println("读取type之后："+in.readableBytes());
        //读取flag字段
        byte qos = in.readByte();
        System.out.println("读取flag之后："+in.readableBytes());
        //读取length字段
        int length = in.readInt();
        System.out.println("读取length之后："+in.readableBytes());

        if(in.readableBytes()!=length){
            throw new Exception("标记的长度不符合实际长度");
        }
        System.out.println("读取头部之后："+in.readableBytes());
        //读取body
        byte []bytes = new byte[in.readableBytes()];
        in.readBytes(bytes);

        return new MyProtocolBean(type,qos,length,new String(bytes,"UTF-8"));

    }
}