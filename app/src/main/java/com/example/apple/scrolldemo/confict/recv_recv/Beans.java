package com.example.apple.scrolldemo.confict.recv_recv;

import java.util.List;

/**
 * Created by apple on 2019-11-19.
 * description:
 */
public class Beans {
    private String head;
    private List<String> content;

    public Beans(String head, List<String> content) {
        this.head = head;
        this.content = content;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }
}
