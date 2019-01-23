package com.example.apple.scrolldemo.recycle;

/**
 * @author crazyZhangxl on 2019/1/20.
 * Describe:
 */
public class FriendBean {
    private String name;
    private String nameSpelling;

    public FriendBean(String name, String nameSpelling) {
        this.name = name;
        this.nameSpelling = nameSpelling;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameSpelling() {
        return nameSpelling;
    }

    public void setNameSpelling(String nameSpelling) {
        this.nameSpelling = nameSpelling;
    }
}
