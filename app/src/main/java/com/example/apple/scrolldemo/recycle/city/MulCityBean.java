package com.example.apple.scrolldemo.recycle.city;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @author crazyZhangxl on 2019/1/17.
 * Describe:城市的多布局文件
 */
public class MulCityBean implements MultiItemEntity{
    /**
     * 具体城市信息
     */
    public static final int CITY = 1;
    /**
     * 添加的按钮
     */
    public static final int ADD_SYMBOL = 2;

    /**
     * 用于返回给itemType的数值
     * 在构造方法中可进行初始化
     */
    private int itemType;

    // 对应的每个Item的具体数值
    public String cityId;
    // 就是江宁
    public String cityName;
    public String temp;
    public String weatherStatus;
    /**
     * 是否显示删除的按钮
     */
    private boolean isShowDelete;
    /**
     * 是否显示本地的城市
     */
    private boolean isNowCity;


    private boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean isShowDelete() {
        return isShowDelete;
    }

    public void setShowDelete(boolean showDelete) {
        isShowDelete = showDelete;
    }

    public boolean isNowCity() {
        return isNowCity;
    }

    public void setNowCity(boolean nowCity) {
        isNowCity = nowCity;
    }

    /**
     * 无参数构造方法那么默认该bean就是添加按钮
     */
    public MulCityBean() {
        itemType = ADD_SYMBOL;
    }

    /**
     * 多惨构造方法即是城市的信息
     * @param cityId
     * @param cityName
     * @param temp
     * @param weatherStatus
     */
    public MulCityBean(String cityId, String cityName, String temp, String weatherStatus) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.temp = temp;
        this.weatherStatus = weatherStatus;
        itemType = CITY;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
