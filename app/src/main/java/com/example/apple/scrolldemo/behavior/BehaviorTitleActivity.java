package com.example.apple.scrolldemo.behavior;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.apple.scrolldemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author crazyZhangxl on 2018-11-28 9:38:50.
 * Describe:
 */

public class BehaviorTitleActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private BaseQuickAdapter<String,BaseViewHolder> mQuickAdapter;
    private List<String> mStringsList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavior_title);
        for (int i= 0;i< 50;i++){
            mStringsList.add("数据    "+ i);
        }
        mRecyclerView =  findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mQuickAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_scroll,mStringsList) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.tv,item);
            }
        };
        mRecyclerView.setAdapter(mQuickAdapter);
    }
}
