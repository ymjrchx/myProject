package com.lm.demo.div_v2.entity;

/**
 * @author: lm
 * @Date: 2019/5/30 18:43
 * @Description:
 */
public class PublishMessage {
    private String sender;
    private String receiver;
    private String time;
    private String content;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
