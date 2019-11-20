package com.example.apple.scrolldemo.refresh;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.apple.scrolldemo.R;

/**
 * Created by apple on 2019-11-16.
 * description:
 */
public class RefreshHead extends FrameLayout {
    private TextView mTvDes;
    private int mViewHeight;
    private RelativeLayout mContainer;
    private RefreshState mRefreshState;
    private boolean isRefreshing = false;


    public RefreshHead( Context context) {
        this(context,null);
    }

    public RefreshHead(Context context,  AttributeSet attrs) {
       this(context,attrs,0);
    }

    public RefreshHead(Context context,AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    private void initViews() {
        // 设置状态1 下拉刷新...
        mRefreshState = RefreshState.STATE_ONE;

        // 初始化头布局...
        final View view = LayoutInflater.from(getContext()).inflate(R.layout.refresh_head_view, this);
        mTvDes = findViewById(R.id.tvDes);

        // 获得原始测量高度
        measure(0,0);
        mViewHeight = RefreshHead.this.getMeasuredHeight();
        Log.e("测量高度", "onGlobalLayout: "+mViewHeight);

        // 一开始设置内容布局高度为0
        mContainer = findViewById(R.id.container);
        mContainer.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0));
    }

    /**
     * 设置其可见高度...
     * @param height
     */
    private void setVisibleHeight(int height){
        if (height < 0){
            height = 0;
        }
        LinearLayout.LayoutParams lp =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0);
        lp.height = height;
        mContainer.setLayoutParams(lp);
    }


    public void onMove(float delta){
        if (getVisibleHeight() > 0 || delta > 0) {
            setVisibleHeight((int) delta + getVisibleHeight());
            // 1,2
            if (mRefreshState.value <= RefreshState.STATE_TWO.value) {
                  if (getVisibleHeight() >= mViewHeight){
                      setRefreshState(RefreshState.STATE_TWO);
                  } else {
                      setRefreshState(RefreshState.STATE_ONE);
                  }
            }

        }
    }

    private void setRefreshState(RefreshState refreshState){
        if (refreshState == mRefreshState){
            return;
        }
        mRefreshState = refreshState;
        mTvDes.setText(mRefreshState.des);
    }

    public boolean releaseAction(){
        // 依据条件进行判断

        // 下拉刷新   直接回到0
        // 释放刷新   回到控件原始的高度
        // 正在刷新   回到0
        isRefreshing = false;
        if (mRefreshState == RefreshState.STATE_ONE){
            smoothScrollToHeight(0);
        }else if (mRefreshState == RefreshState.STATE_TWO){
            smoothScrollToHeight(mViewHeight);
            setRefreshState(RefreshState.STATE_THREE);
            isRefreshing = true;
        }

        return isRefreshing;
    }

    public void finishRefresh(){
        setRefreshState(RefreshState.STATE_FOUR);
        new Handler(Looper.getMainLooper())
                .postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        reset();
                    }
                },500);
    }

    /**
     * 重置状态
     */
    public void reset(){
        smoothScrollToHeight(0);
        setRefreshState(RefreshState.STATE_ONE);
    }

    private void smoothScrollToHeight(int height){
        ValueAnimator valueAnimator = ValueAnimator.ofInt(getVisibleHeight(),height);
        valueAnimator.setDuration(500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int)animation.getAnimatedValue();
                setVisibleHeight(animatedValue);
            }
        });
        valueAnimator.start();
    }

    public RefreshState getRefreshState() {
        return mRefreshState;
    }

    /**
     * 获得当前头部view的实际布局高度
     * @return
     */
    public int  getVisibleHeight(){
        return mContainer.getHeight();
    }
}
