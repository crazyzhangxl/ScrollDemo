package com.example.apple.scrolldemo.nestedscroll;

import android.support.design.widget.CoordinatorLayout;
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

public class SimpleRecvActivity extends AppCompatActivity {
    private List<String> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_recv);
        initData();
        initViews();
    }

    private void initViews() {
        RecyclerView recyclerView =  findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_scroll, mData) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                Log.e("性能", "加载了"+helper.getAdapterPosition() );
                helper.setText(R.id.tv, item);
            }

        };
        recyclerView.setAdapter(baseQuickAdapter);
    }

    private void initData() {
        for (int i=0;i<80;i++){
            mData.add("每天进步一点点-.-"+i);
        }
    }
}
