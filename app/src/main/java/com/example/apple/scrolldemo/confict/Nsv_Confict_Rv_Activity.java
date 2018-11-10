package com.example.apple.scrolldemo.confict;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.apple.scrolldemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author crazyZhangxl on 2018-11-10 8:51:52.
 * Describe:解决NestedSCrollView与RecyclerView的冲突问题
 */

public class Nsv_Confict_Rv_Activity extends AppCompatActivity {
    private TNoConflictRecyclerView mRvConflict;
    private List<String> mData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nsv__confict__rv_);
        initData();
        initView();
    }


    private void initView() {
        mRvConflict = findViewById(R.id.rv);
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
    private void initData() {
        for (int i=0;i<80;i++){
            mData.add("每天进步一点点-.-"+i);
        }
    }
}
