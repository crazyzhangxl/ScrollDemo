package com.example.apple.scrolldemo.fixed.review;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apple.scrolldemo.R;
import com.example.apple.scrolldemo.recycle.base.ZxlHeadWithFooterAdapter;
import com.example.apple.scrolldemo.recycle.ding.DingAdapter;
import com.example.apple.scrolldemo.recycle.ding.DingBean;

import java.util.ArrayList;
import java.util.List;

public class RecycleFixedActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private DingAdapter mDingAdapter;
    private ZxlHeadWithFooterAdapter mZxlHeadWithFooterAdapter;
    private List<DingBean> mDingBeans = new ArrayList<>();

    private LinearLayout mOutLinearWrapper = null;
    private LinearLayout mInnerLinerWrapper = null;
    private View headView = null;
    private View mWrapperView;
    private int calculateHeight = 0;
    private GridLayoutManager mGridLayoutManager;
    private int i = 0;

    public static void show(Activity activity){
        Intent intent = new Intent(activity,RecycleFixedActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_fixed);
        initData();
        initViews();
        initListener();
    }

    private void initData() {
        mDingBeans.add(new DingBean("智能内外勤",8));
        mDingBeans.add(new DingBean("智能内外勤","考勤打卡"));
        mDingBeans.add(new DingBean("智能内外勤","考勤统计"));
        mDingBeans.add(new DingBean("智能内外勤","签到"));
        mDingBeans.add(new DingBean("智能内外勤","请假"));
        mDingBeans.add(new DingBean("智能内外勤","公告"));
        mDingBeans.add(new DingBean("智能内外勤","钉盘"));
        mDingBeans.add(new DingBean("智能内外勤","审核"));
        mDingBeans.add(new DingBean("智能内外勤","智能工资条"));
        mDingBeans.add(new DingBean("行政管理",5));
        mDingBeans.add(new DingBean("行政管理","物品领用"));
        mDingBeans.add(new DingBean("行政管理","用车申请"));
        mDingBeans.add(new DingBean("行政管理","用印申请"));
        mDingBeans.add(new DingBean("行政管理","日志"));
        mDingBeans.add(new DingBean("行政管理","日报"));
        mDingBeans.add(new DingBean("协同效率",3));
        mDingBeans.add(new DingBean("协同效率","电话会议"));
        mDingBeans.add(new DingBean("协同效率","办公电话"));
        mDingBeans.add(new DingBean("协同效率","钉邮"));
        mDingBeans.add(new DingBean("智能内外勤",8));
        mDingBeans.add(new DingBean("智能内外勤","考勤打卡"));
        mDingBeans.add(new DingBean("智能内外勤","考勤统计"));
        mDingBeans.add(new DingBean("智能内外勤","签到"));
        mDingBeans.add(new DingBean("智能内外勤","请假"));
        mDingBeans.add(new DingBean("智能内外勤","公告"));
        mDingBeans.add(new DingBean("智能内外勤","钉盘"));
        mDingBeans.add(new DingBean("智能内外勤","审核"));
        mDingBeans.add(new DingBean("智能内外勤","智能工资条"));
        mDingBeans.add(new DingBean("行政管理",5));
        mDingBeans.add(new DingBean("行政管理","物品领用"));
        mDingBeans.add(new DingBean("行政管理","用车申请"));
        mDingBeans.add(new DingBean("行政管理","用印申请"));
        mDingBeans.add(new DingBean("行政管理","日志"));
        mDingBeans.add(new DingBean("行政管理","日报"));
        mDingBeans.add(new DingBean("协同效率",3));
        mDingBeans.add(new DingBean("协同效率","电话会议"));
        mDingBeans.add(new DingBean("协同效率","办公电话"));
        mDingBeans.add(new DingBean("协同效率","钉邮"));
    }

    private void initViews() {
        mOutLinearWrapper =findViewById(R.id.llFixed);
        View view = LayoutInflater.from(this).inflate(R.layout.head_view, null);
        mWrapperView = LayoutInflater.from(this).inflate(R.layout.fixed_wrapper,null);
        mInnerLinerWrapper = mWrapperView.findViewById(R.id.fixedWrapper);
        headView = LayoutInflater.from(this).inflate(R.layout.fixed_view,null);
        // 初始化时现将view假如到列表中
        mInnerLinerWrapper.addView(headView);
        mRecyclerView = findViewById(R.id.recyclerView);
        mDingAdapter = new DingAdapter(mDingBeans,this);
        mZxlHeadWithFooterAdapter = new ZxlHeadWithFooterAdapter(mDingAdapter,this);
        mGridLayoutManager = new GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mZxlHeadWithFooterAdapter.addHeadView(view);
        mZxlHeadWithFooterAdapter.addHeadView(mWrapperView);
        mRecyclerView.setAdapter(mZxlHeadWithFooterAdapter);
        calculateHeight();
    }


    private void initListener() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // 通过layoutManager获得控件
                // 启发,我们可以获取其top值
                View topView = mGridLayoutManager.findViewByPosition(0);
                if (topView != null){
                    int top = topView.getTop();
                    if (top < -1* calculateHeight){
                    if (headView.getParent() != mOutLinearWrapper){
                        mInnerLinerWrapper.removeView(headView);
                        mOutLinearWrapper.addView(headView);
                    }
                }else {
                    if (headView.getParent() != mInnerLinerWrapper){
                        mOutLinearWrapper.removeView(headView);
                        mInnerLinerWrapper.addView(headView);
                    }
                } }
            }
        });

        headView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               TextView tvdes=  headView.findViewById(R.id.tv_topView);
               tvdes.setText("变形啦"+i);
               i++;
            }
        });


    }

    private void addOrRemoveHead(){

    }

    private void calculateHeight(){
        mRecyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mRecyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                calculateHeight = mWrapperView.getTop();
            }
        });
    }
}
