package com.example.apple.scrolldemo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.apple.scrolldemo.views.ObservableAlphaScrollView;

/**
 * @author crazyZhangxl on 2018-11-5 13:29:31.
 * Describe: 滑动渐变的活动
 */

public class ScrollAlphaActivity extends AppCompatActivity implements ObservableAlphaScrollView.OnAlphaScrollChangeListener {
    private ObservableAlphaScrollView mObservableAlphaScrollView;
    private View mTitleView;
    private TextView tvHeadView;
    private int statusBarHeight;
    private RelativeLayout mRlHead;
    private int moveDiatance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_alpha);
        // 设置的沉浸式状态栏
        mObservableAlphaScrollView = findViewById(R.id.llTest);
        mTitleView = findViewById(R.id.ll_header_content);
        tvHeadView = findViewById(R.id.tv_main_topContent);
        mRlHead = findViewById(R.id.rlHead);

        // 设置空余的Padiing
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mRlHead.getLayoutParams();
        layoutParams.topMargin = getStatusBarHeight();
        mRlHead.setLayoutParams(layoutParams);


        // 测量高度差-----
        ViewTreeObserver treeObserver = tvHeadView.getViewTreeObserver();
        treeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // 移除监听
                tvHeadView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int titleHeight = mTitleView.getMeasuredHeight();
                int headHeight = tvHeadView.getMeasuredHeight();
                moveDiatance = headHeight - titleHeight;
                mObservableAlphaScrollView.setOnAlphaScrollChangeListener(ScrollAlphaActivity.this);
            }
        });
    }

    @Override
    public void onVerticalScrollChanged(int t) {
        if (t<=0){
            mTitleView.setBackgroundColor(Color.argb(0, 48, 63, 159));
        }else {
            if (t<moveDiatance){
                float scale = (float)t / moveDiatance;
                float alpha = (255 * scale);//得到透明度
                mTitleView.setBackgroundColor(Color.argb((int)alpha, 48, 63, 159));
            }else {
                mTitleView.setBackgroundColor(Color.argb(255, 48, 63, 159));
            }
        }
    }

    private int getStatusBarHeight() {
        if (statusBarHeight == 0) {
            int result = 0;
            //获取状态栏高度的资源id
            int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = getResources().getDimensionPixelSize(resourceId);
            }
            statusBarHeight = result;
        }
        return statusBarHeight;
    }
}
