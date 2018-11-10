package com.example.apple.scrolldemo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.apple.scrolldemo.views.ObservableAlphaScrollView;

/**
 * @author crazyZhangxl on 2018-11-5 16:49:54.
 * Describe: 仿微信朋友圈界面
 */

public class LikeWChatActivity extends AppCompatActivity implements ObservableAlphaScrollView.OnAlphaScrollChangeListener {
    private LinearLayout mLlTitle,mLlScHead;
    private int mTitleHeight;
    private int mHeadHeight;
    private int mDistance;
    private ObservableAlphaScrollView mScrollView;
    private ImageView mIvBack;
    private TextView mTvText;
    private int mDistanceY = 30;// 设置一个临界值吧

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like_wchat);
        mLlTitle = findViewById(R.id.llTitle);
        mLlScHead = findViewById(R.id.llScHead);
        mScrollView = findViewById(R.id.scrollView);
        mIvBack = findViewById(R.id.imageBack);
        mTvText = findViewById(R.id.ivText);
        ViewTreeObserver viewTreeObserver = mLlScHead.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mLlScHead.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mTitleHeight = mLlTitle.getMeasuredHeight();
                mHeadHeight = mLlScHead.getMeasuredHeight();
                mDistance = mHeadHeight - mTitleHeight;
                Log.e("result  mTitleHead = ",mTitleHeight+"");
                Log.e("result  mHeadHeight = ",mHeadHeight+"");
                Log.e("result  mDistance = ",mDistance+"");
                mScrollView.setOnAlphaScrollChangeListener(LikeWChatActivity.this);
            }
        });

    }

    @Override
    public void onVerticalScrollChanged(int t) {
        if (t<= (mDistance - mDistanceY)){
            mTvText.setAlpha(0f);
            mIvBack.setSelected(false);
            mLlTitle.setBackgroundColor(Color.argb(0, 242, 242, 242));
        }else if (t<=mDistance) {
            mTvText.setAlpha(0f);
            mIvBack.setSelected(false);
        }else if (t <= (mDistance + mDistanceY)){
            mTvText.setAlpha(1f);
            mIvBack.setSelected(true);
            mLlTitle.setBackgroundColor(Color.argb(0, 242, 242, 242));
        }else {
            mTvText.setAlpha(1f);
            mIvBack.setSelected(true);
            mLlTitle.setBackgroundColor(Color.argb(255, 242, 242, 242));
        }
    }
}
