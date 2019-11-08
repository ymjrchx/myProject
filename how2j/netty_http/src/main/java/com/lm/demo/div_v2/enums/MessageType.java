package com.lm.demo.div_v2.enums;

/**
 * @author: lm
 * @Date: 2019/5/30 16:05
 * @Description:
 */
public enum  MessageType {

    CONNECT(1, "CONNECT"),
    CONNACK(2, "CONNACK"),
    PUBLISH(3, "PUBLISH"),
    PUBACK(4, "PUBACK"),
    PUBREC(5, "PUBREC"),
    MESSAGE(6, "MESSAGE"),
    PUBCOMP(7, "PUBCOMP"),
    PING(8, "PING"),
    PINGRSP(9, "PINGRSP"),
    DISCONNECT(10, "DISCONNECT");

    /**
     * 枚举值.
     */
    private Integer value;
    /**
     * 枚举描述.
     */
    private String desc;

    /**
     * 构造函数.
     *
     * @param value
     *            枚举值
     * @param desc
     *            枚举描述
     */
    MessageType(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    protected void setValue(Integer value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    protected void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 根据枚举值获取枚举对象.
     *
     * @param value
     *            枚举值
     * @return SkuStatus
     */
    public static MessageType get(int value) {
        for (MessageType messageType : MessageType.values()) {
            if (messageType.value == value) {
                return messageType;
            }
        }
        throw new IllegalArgumentException("argument error: " + value);
    }
}
