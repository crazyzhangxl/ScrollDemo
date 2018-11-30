package com.example.apple.scrolldemo.fixed.normal;

/**
 * @author crazyZhangxl on 2018/11/27.
 * Describe: 底部导航的更新操作 ————————
 */
public class BottomUpdateMessage {

    private boolean isShow;

    public BottomUpdateMessage() {
    }

    public BottomUpdateMessage(boolean isShow) {
        this.isShow = isShow;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }
}
