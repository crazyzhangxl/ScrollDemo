package com.example.apple.scrolldemo.recycle.one.model;

/**
 * @author crazyZhangxl on 2019/1/16.
 * Describe:消息方向
 */
public enum  MessageDirection {

    SEND(0),
    RECEIVE(1);

    public int integerValue;

    MessageDirection(int integerValue){
        this.integerValue = integerValue;
    }
}
