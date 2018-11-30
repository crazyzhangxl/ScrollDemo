package com.example.apple.scrolldemo.fixed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.apple.scrolldemo.R;
import com.example.apple.scrolldemo.views.ObservableAlphaScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author crazyZhangxl on 2018-11-6 9:01:17.
 * Describe:scroll滑动实现粘性布局
 */

public class StickyScrollActivity extends AppCompatActivity implements ObservableAlphaScrollView.OnAlphaScrollChangeListener {
    private RecyclerView mRvConflict;
    private ObservableAlphaScrollView mNestedScrollView;
    private LinearLayout mFixedView,mLLTopView;
    private TextView mTvHeadView,mTvTopView;
    private List<String> mData = new ArrayList<>();
    private int topViewHeight;
    private boolean isFirst = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky_scroll);
        initData();
        initViews();
        ViewTreeObserver viewTreeObserver = mLLTopView.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mLLTopView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                topViewHeight = mTvHeadView.getMeasuredHeight();
                mNestedScrollView.setOnAlphaScrollChangeListener(StickyScrollActivity.this);
                Log.e("result onGlobalLayout",topViewHeight+"");
            }
        });
    }


    private void initData() {
        for (int i=0;i<80;i++){
            mData.add("sticky固定---------"+i);
        }
    }

    private void initViews() {
        mRvConflict = findViewById(R.id.recyclerview);
        mFixedView = findViewById(R.id.ll_fixedView);
        mNestedScrollView = findViewById(R.id.svContentView);
        mTvHeadView = findViewById(R.id.tv_headerView);
        mLLTopView = findViewById(R.id.ll_topView);
        mTvTopView = findViewById(R.id.tv_topView);

        LinearLayoutManager manager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        // 默认为true
        manager.setSmoothScrollbarEnabled(true);
        // 允许自动测量
        manager.setAutoMeasureEnabled(true);
        mRvConflict.setLayoutManager(manager);
        mRvConflict.setHasFixedSize(true);
        mRvConflict.setNestedScrollingEnabled(false);
        BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_scroll, mData) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.tv, item);
            }

        };
        mRvConflict.setAdapter(baseQuickAdapter);
    }

    @Override
    public void onVerticalScrollChanged(int t) {
        if (t>=topViewHeight){
            if (mTvTopView.getParent() != mFixedView){
                mLLTopView.removeView(mTvTopView);
                mFixedView.addView(mTvTopView);
            }
        }else {
            if (mTvTopView.getParent() != mLLTopView){
                mFixedView.removeView(mTvTopView);
                mLLTopView.addView(mTvTopView);
            }
        }
    }
}
