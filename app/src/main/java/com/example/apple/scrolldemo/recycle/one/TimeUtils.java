package com.example.apple.scrolldemo.recycle.one;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author crazyZhangxl on 2019/1/16.
 * Describe:
 */
public class TimeUtils {

    private SimpleDateFormat normalDateFormat;

    @SuppressLint("SimpleDateFormat")
    private TimeUtils(){
        normalDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    private static class TimeUtilsHolder{
        private static final TimeUtils  MINSTANCE = new TimeUtils();
    }

    public static TimeUtils getInstance(){
        return TimeUtilsHolder.MINSTANCE;
    }

    public long convertTimeToLong(String  time){
        try {
            Date date = normalDateFormat.parse(time);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public String longToTime(long time){
        return normalDateFormat.format(new Date(time));
    }
}
