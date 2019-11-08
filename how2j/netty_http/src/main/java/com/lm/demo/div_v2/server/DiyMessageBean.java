package com.lm.demo.div_v2.server;

/**
 * @Auther: lm
 * @Date: 2019/1/25 14:51
 * @Description:
 */
public class DiyMessageBean<T> {
    //信息标志  哪类消息
    private int type;
    //信息标志 qos=0时 消息只发送一次 ，1 消息最少发送一次 broker broker会给消息回执， 　Qos2使用两阶段确认来保证消息的不丢失和不重复。在Qos2情况下，Broker肯定会收到消息，且只收到一次
    private  int qos;
    //心跳
    private  int ping;
    /**
     * 用户账号
     */
    private String userName;
    //内容长度
    private int length;
    //内容
    private T content;

    public DiyMessageBean() {

    }


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

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public int getPing() {
        return ping;
    }

    public void setPing(int ping) {
        this.ping = ping;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public DiyMessageBean(int qos, int type, int ping, String userName, T content) {
        this.qos = qos;
        this.type = type;
        this.ping=ping;
        this.userName=userName;
        this.content = content;
    }

    @Override
    public String toString() {
        return "DiyMessageBean{" +
                "type=" + type +
                ", qos=" + qos +
                ", ping="+ping+
                ", length=" + length +
                ", content='" + content + '\'' +
                '}';
    }
}