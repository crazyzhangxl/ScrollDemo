package com.example.apple.scrolldemo.confict;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.apple.scrolldemo.R;

import java.util.ArrayList;
import java.util.List;

public class NormalNestedActivity extends AppCompatActivity {
    private RecyclerView mRvConflict;
    private List<String> mData = new ArrayList<>();


    public static void show(Activity activity){
        Intent intent = new Intent(activity,NormalNestedActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_nested);
        initData();
        initView();
    }

    private void initView() {
        mRvConflict = findViewById(R.id.recyclerView);
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
                Log.e("性能", "加载了"+helper.getAdapterPosition() );
                helper.setText(R.id.tv, item);
            }

        };
        mRvConflict.setAdapter(baseQuickAdapter);

    }
    private void initData() {
        for (int i=0;i<80;i++){
            mData.add("每天进步一点点-.-"+i);
        }
    }
}
