package com.example.apple.scrolldemo.recycle.one.model;

/**
 * @author crazyZhangxl on 2019/1/16.
 * Describe:
 */
public class TextMessage extends Message {
    /**
     * 文本信息 -----
     */
    private String textInfo;


    public TextMessage(long time,int direction,String sendName,MessageType messageType,String textInfo){
        this.sendTime = time;
        this.direction = direction;
        this.sendName = sendName;
        this.mMessageType = messageType;
        this.textInfo = textInfo;
    }

    public String getTextInfo() {
        return textInfo;
    }

    public void setTextInfo(String textInfo) {
        this.textInfo = textInfo;
    }
}
