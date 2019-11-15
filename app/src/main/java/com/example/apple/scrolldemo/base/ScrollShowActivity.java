package com.example.apple.scrolldemo.base;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.apple.scrolldemo.R;

/**
 * @author crazyZhangxl on 2019-1-24 13:21:25.
 * Describe:
 *
 * 显示一个view的时候,默认情况下view的左边和画布对齐
 * 显示超出屏幕的内容 确实是view向左进行移动, 我现在理解成画布向左移动，相当于布局向右移动了
 *
 * getScrollX() 代表的是画布在屏幕左边的距离,在右边当然是负数咯
 */

public class ScrollShowActivity extends AppCompatActivity {
    private HorizontalScrollView mHorizontalScrollView;
    private Button mBtnOne,mBtnTwo;
    private TextView mTvShow;
    private LinearLayout mLinearLayout;
    private TextView tvResultOne,tvResultTwo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_show);
        initViews();
    }

    private void initViews() {
        mHorizontalScrollView = findViewById(R.id.scrollView);
        mBtnOne = findViewById(R.id.btnLeft);
        mBtnTwo = findViewById(R.id.btnRight);
        mTvShow = findViewById(R.id.tvShow);
        mLinearLayout = findViewById(R.id.ll);
        tvResultOne = findViewById(R.id.tvResultOne);
        tvResultTwo = findViewById(R.id.tvResultTwo);
        ViewTreeObserver viewTreeObserver = mHorizontalScrollView.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                Log.e("日志", "水平滑动 = "+mHorizontalScrollView.getWidth() );
                Log.e("日志", "线性布局长度 =   "+mLinearLayout.getWidth() );
                Log.e("日志", "scrollView的scrollX =   "+mHorizontalScrollView.getScrollX() );
                Log.e("日志", "线性布局 scrollX =   "+mLinearLayout.getScrollX() );
                mHorizontalScrollView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        mTvShow.setText("原始scrollX = "+mHorizontalScrollView.getScrollX());
        mBtnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHorizontalScrollView.smoothScrollTo(-200,0);
                Log.e("日志", "-200 = "+mHorizontalScrollView.getScrollX() );
                Log.e("日志", "线性scrollX =   "+mLinearLayout.getScrollX() );

                tvResultOne.setText("执行scrollView.scrollTo(-200,0)\n"+"mHorizontalScrollView.getScrollX() = "+mHorizontalScrollView.getScrollX()
                        +"  mLinearLayout.getScrollX()  = "+mLinearLayout.getScrollX());
            }
        });

        mBtnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHorizontalScrollView.smoothScrollTo(200,0);
                // 因为这里需要一定的时间
                Log.e("日志", "+200 = "+mHorizontalScrollView.getScrollX() );
                Log.e("日志", "线性scrollX =   "+mLinearLayout.getScrollX() );

                tvResultOne.setText("执行scrollView.scrollTo(200,0)\n"+"mHorizontalScrollView.getScrollX() = "+mHorizontalScrollView.getScrollX()
                        +"  mLinearLayout.getScrollX()  = "+mLinearLayout.getScrollX());
            }
        });
    }
}
