package com.lm.demo.diy1.server;

/**
 * @Auther: lm
 * @Date: 2019/1/25 14:51
 * @Description:
 */
public class MyProtocolBean {
    //信息标志  0xA 表示心跳包    0xC 表示超时包  0xC 业务信息包
    private int type;

    //信息标志 qos=0时 消息只发送一次 ，1 消息最少发送一次 broker broker会给消息回执， 　Qos2使用两阶段确认来保证消息的不丢失和不重复。在Qos2情况下，Broker肯定会收到消息，且只收到一次

     private  int qos;

    //内容长度
    private int length;

    //内容
    private String content;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public int getQos() {
        return qos;
    }

    public void setQos(int qos) {
        this.qos = qos;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MyProtocolBean(int qos, int type, int length, String content) {
        this.qos = qos;
        this.type = type;
        this.length = length;
        this.content = content;
    }

    @Override
    public String toString() {
        return "DiyMessageBean{" +
                "type=" + type +
                ", qos=" + qos +
                ", length=" + length +
                ", content='" + content + '\'' +
                '}';
    }
}