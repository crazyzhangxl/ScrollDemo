package com.example.apple.scrolldemo.center;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.apple.scrolldemo.R;

import java.util.ArrayList;
import java.util.List;

public class ItemCenterActivity extends AppCompatActivity implements CenterAdapter.MyOnItemClickListener {
    private RecyclerView mRecyclerView;
    private CenterAdapter mCenterAdapter;
    private List<String> mList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_center);
        mRecyclerView = findViewById(R.id.recyclerView);

        for (int i=0;i<50;i++){
            mList.add("数据源 "+i);
        }
        mCenterAdapter = new CenterAdapter(this,mList,this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mCenterAdapter);
    }

    @Override
    public void onItemClick(int position) {
        if (position % 2 == 0){
            scrollToCenter(position);
        }else {
            scrollToCenter2(position);
        }
    }


    public void scrollToCenter(int position){
        LinearLayoutManager layoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
        if (layoutManager != null){
            int visibleItemIndex =  layoutManager.findFirstVisibleItemPosition();
            // 可见的孩子下标 = 当前下标 - 可见的首个item
            int visiblePosition = position - visibleItemIndex;
            View child =  mRecyclerView.getChildAt(visiblePosition);
            if (null != child){
                mRecyclerView.smoothScrollBy(0,child.getTop()+child.getHeight()/2 - mRecyclerView.getHeight()/2);
            }
        }
    }

    public void scrollToCenter2(int position){
        LinearLayoutManager layoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
        if (layoutManager != null){
            int visibleItemIndex =  layoutManager.findFirstVisibleItemPosition();
            // 可见的孩子下标 = 当前下标 - 可见的首个item
            int visiblePosition = position - visibleItemIndex;
            View child =  mRecyclerView.getChildAt(visiblePosition);
            if (null != child){
                int itemHeight =  child.getHeight();
                int prePosition = position -  mRecyclerView.getHeight()/2/itemHeight;
                Log.e("滑动到", "scrollToCenter: "+prePosition);
                layoutManager.scrollToPositionWithOffset(prePosition,0);
            }
        }
    }
}
