package com.example.apple.scrolldemo.recycle.grid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.apple.scrolldemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author crazyZhangxl on 2019-1-22 21:16:06.
 * Describe:
 */

public class GridTextActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<GridTextBean> mGridTextBeans = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_text);
        initData();
        initViews();
    }

    private void initData() {
        mGridTextBeans.add(new GridTextBean(GridTextAdapter.LEFT,"小李"));
        mGridTextBeans.add(new GridTextBean(GridTextAdapter.LEFT,"小黄"));
        mGridTextBeans.add(new GridTextBean(GridTextAdapter.LEFT,"小红"));
        mGridTextBeans.add(new GridTextBean(GridTextAdapter.CENTER,"小绿"));
        mGridTextBeans.add(new GridTextBean(GridTextAdapter.RIGHT,"小橙"));
        mGridTextBeans.add(new GridTextBean(GridTextAdapter.RIGHT,"小王"));
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,6));
        recyclerView.setAdapter(new GridTextAdapter(this,mGridTextBeans));
    }
}
