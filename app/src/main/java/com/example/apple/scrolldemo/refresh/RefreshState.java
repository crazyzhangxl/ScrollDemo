package com.example.apple.scrolldemo.refresh;

/**
 * Created by apple on 2019-11-16.
 * description:
 */
public enum RefreshState {
    STATE_ONE("下拉刷新",1),
    STATE_TWO("释放刷新",2),
    STATE_THREE("正在刷新",3),
    STATE_FOUR("刷新完成",4);
    String des;
    int value;
    RefreshState(String des,int value) {
        this.des = des;
        this.value = value;
    }}
