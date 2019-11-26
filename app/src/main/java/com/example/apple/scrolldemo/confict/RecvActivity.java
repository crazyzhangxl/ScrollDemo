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

public class RecvActivity extends AppCompatActivity {
    private RecyclerView mRvConflict;
    private List<String> mData = new ArrayList<>();

    public static void show(Activity activity){
        Intent intent = new Intent(activity,RecvActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recv);
        initData();
        initView();
    }

    private void initView() {
        mRvConflict = findViewById(R.id.recyclerView);
        LinearLayoutManager manager =
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRvConflict.setLayoutManager(manager);

        manager.setSmoothScrollbarEnabled(true);
        manager.setAutoMeasureEnabled(true);
//取消recycleview的滑动
        mRvConflict.setHasFixedSize(true);
        mRvConflict.setNestedScrollingEnabled(false);
        mRvConflict.setAdapter(new PicTextAdapter(mData,RecvActivity.this));

    }
    private void initData() {
        for (int i=0;i<80;i++){
            mData.add("数据信息"+i);
        }
    }
}
