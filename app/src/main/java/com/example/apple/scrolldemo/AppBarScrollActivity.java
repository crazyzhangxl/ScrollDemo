package com.example.apple.scrolldemo;

import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.LinearLayout;

/**
 * @author crazyZhangxl on 2019-1-18 21:02:42.
 * Describe:
 */

public class AppBarScrollActivity extends AppCompatActivity {
    private AppBarLayout mAppBarLayout;
    private LinearLayout mLlContainer;
    private float mWeatherInfoContainerLeft;
    private float percentageOfShowTitle = DEFAULT_PERCENTAGE;
    private static final float DEFAULT_PERCENTAGE = 0.8f;
    protected float mTitlePercentage;
    private Toolbar mToolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_bar_scroll);

        initViews();
        initListeners();
        // 进行测量
        mLlContainer.post(new Runnable() {
            @Override
            public void run() {
                mWeatherInfoContainerLeft = mLlContainer.getX();
                Log.e("app", "container left "+mWeatherInfoContainerLeft );
                percentageOfShowTitle = (mLlContainer.getBottom()) * 1.0f / mAppBarLayout.getTotalScrollRange();
                Log.e("app", "percentageOfShowTitle "+percentageOfShowTitle );
                if (percentageOfShowTitle == 0) {
                    mWeatherInfoContainerLeft = DEFAULT_PERCENTAGE;
                }
            }
        });
    }

    private void initListeners() {
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.e("app", "onOffsetChanged: "+verticalOffset );
                int maxScroll = appBarLayout.getTotalScrollRange();
                mTitlePercentage = (float) Math.abs(verticalOffset) / (float) maxScroll;
                handleInfoAnimate(mTitlePercentage);
            }
        });
    }

    private void handleInfoAnimate(float titlePercentage) {
        mToolbar.getBackground().mutate().setAlpha((int)(255*titlePercentage));
        mLlContainer.setAlpha(1 - titlePercentage);
        mLlContainer.setScaleX(1 - titlePercentage);
        mLlContainer.setScaleY(1 - titlePercentage);
        Log.e("app", "handleInfoAnimate: "+mWeatherInfoContainerLeft );
        if (mWeatherInfoContainerLeft > 0) {
            mLlContainer.setX(mWeatherInfoContainerLeft * (1 - titlePercentage));
        }
    }

    private void initViews() {
        mAppBarLayout = findViewById(R.id.appBarLayout);
        mLlContainer = findViewById(R.id.llContainer);
        mToolbar = findViewById(R.id.toolbar);
    }
}
