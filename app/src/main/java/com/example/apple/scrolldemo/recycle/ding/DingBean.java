package com.example.apple.scrolldemo.recycle.ding;

/**
 * Created by apple on 2019-11-14.
 * description:
 */
public class DingBean {
    private String category;
    private String iconImage;
    private String iconText;
    private String title;
    private int itemSize;

    public DingBean(String title, int itemSize) {
        this.title = title;
        this.itemSize = itemSize;
    }

    public int getItemSize() {
        return itemSize;
    }

    public void setItemSize(int itemSize) {
        this.itemSize = itemSize;
    }

    public DingBean(String category, String iconText) {
        this.category = category;
        this.iconText = iconText;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIconImage() {
        return iconImage;
    }

    public void setIconImage(String iconImage) {
        this.iconImage = iconImage;
    }

    public String getIconText() {
        return iconText;
    }

    public void setIconText(String iconText) {
        this.iconText = iconText;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
