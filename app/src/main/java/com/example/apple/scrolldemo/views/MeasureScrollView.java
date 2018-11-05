package com.example.apple.scrolldemo.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ScrollView;

/**
 * @author crazyZhangxl on 2018/11/1.
 * Describe:
 */
public class MeasureScrollView extends ScrollView {
    public MeasureScrollView(Context context) {
        super(context);
    }

    public MeasureScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MeasureScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        Log.e("onScrollChanged","l = "+l+" ,t = "+t+" ,oldl = "+oldl+" ,oldt = "+oldt);
    }
}
