package com.example.apple.scrolldemo.recycle.city;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.apple.scrolldemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author crazyZhangxl on 2019-1-17 16:05:02.
 * Describe: 城市添加
 */

public class MulCityActivity extends AppCompatActivity {
    private RecyclerView mCityRecyclerView;
    private MulCityAdapter mMulCityAdapter;
    private List<MulCityBean> mMulCityBeanList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mul_city);
        initData();
        initViews();
        initAdapter();
    }

    private void initData() {
        mMulCityBeanList.add(new MulCityBean("101","南京","12","晴"));
        mMulCityBeanList.add(new MulCityBean("101","北京","15","阴"));
        mMulCityBeanList.add(new MulCityBean("101","天津","18","小雨"));
        mMulCityBeanList.add(new MulCityBean());
    }

    private void initAdapter() {
        mCityRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        mMulCityAdapter = new MulCityAdapter(mMulCityBeanList,this);
        // 设置adapter
        mCityRecyclerView.setAdapter(mMulCityAdapter);
    }

    private void initViews() {
        mCityRecyclerView = findViewById(R.id.cityRecycleView);
    }
}
