package com.example.apple.scrolldemo.recycle.one.model;

/**
 * @author crazyZhangxl on 2019/1/15.
 * Describe:
 */
public class Message {
    /**
     * 方向 0 = 发送; 1 = 接受
     */
    protected int direction;
    /**
     * 发送时间
     */
    protected long sendTime;

    /**
     * 发送的用户名
     */
    protected String sendName;

    protected MessageType mMessageType;

    public MessageType getMessageType() {
        return mMessageType;
    }

    public void setMessageType(MessageType messageType) {
        mMessageType = messageType;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }
}
