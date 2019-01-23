package com.example.apple.scrolldemo.recycle.grid;

/**
 * @author crazyZhangxl on 2019/1/22.
 * Describe:
 */
public class GridTextBean {
    private int type;
    private String name;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GridTextBean(int type, String name) {
        this.type = type;
        this.name = name;
    }
}
