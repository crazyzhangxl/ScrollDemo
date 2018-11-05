package com.example.apple.scrolldemo.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;

/**
 * @author crazyZhangxl on 2018/11/5.
 * Describe:
 */
public class ObservableAlphaScrollView extends NestedScrollView {
    private OnAlphaScrollChangeListener mOnAlphaScrollChangeListener;

    public ObservableAlphaScrollView(@NonNull Context context) {
        super(context);
    }

    public ObservableAlphaScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ObservableAlphaScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnAlphaScrollChangeListener(OnAlphaScrollChangeListener onAlphaScrollChangeListener){
        this.mOnAlphaScrollChangeListener = onAlphaScrollChangeListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mOnAlphaScrollChangeListener != null){
            mOnAlphaScrollChangeListener.onVerticalScrollChanged(t);
        }
    }

    public interface OnAlphaScrollChangeListener{
        void onVerticalScrollChanged(int t);
    }
}
