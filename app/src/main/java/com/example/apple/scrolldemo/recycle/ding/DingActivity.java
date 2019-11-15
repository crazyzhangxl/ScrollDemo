package com.example.apple.scrolldemo.recycle.ding;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;


import com.example.apple.scrolldemo.R;
import com.example.apple.scrolldemo.recycle.base.ZxlHeadWithFooterAdapter;

import java.util.ArrayList;
import java.util.List;

public class DingActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private DingAdapter mDingAdapter;
    private ZxlHeadWithFooterAdapter mZxlHeadWithFooterAdapter;
    private List<DingBean> mDingBeans = new ArrayList<>();
    private View mRlAdd;

    public static void show(Activity activity){
        Intent intent = new Intent(activity,DingActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ding);
        initData();
        initViews();
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
    }

    private void initViews() {
        mRlAdd = LayoutInflater.from(this).inflate(R.layout.add_view,null);

        mRecyclerView = findViewById(R.id.recyclerView);
        mDingAdapter = new DingAdapter(mDingBeans,this);
        mZxlHeadWithFooterAdapter = new ZxlHeadWithFooterAdapter(mDingAdapter,this);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,4, LinearLayoutManager.VERTICAL,false));
        mZxlHeadWithFooterAdapter.addHeadView(mRlAdd);
        mRecyclerView.setAdapter(mZxlHeadWithFooterAdapter);
        initListener();
    }

    private void initListener(){
        mRlAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view = LayoutInflater.from(DingActivity.this).inflate(R.layout.remove_view,null);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mZxlHeadWithFooterAdapter.removeHeadView(view);
                    }
                });
                mZxlHeadWithFooterAdapter.addHeadView(view);
            }
        });

        findViewById(R.id.title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDingBeans.set(3,new DingBean("智能内外勤","签到你妹"));
                mDingAdapter.notifyItemChangedWrapper(3);
            }
        });

    }
}
