package com.example.apple.scrolldemo.recycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.apple.scrolldemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author crazyZhangxl on 2019-1-20 17:30:10.
 * Describe: 微信朋友列表展示
 */

public class WebchatListActivity extends AppCompatActivity {
    private List<FriendBean> mFriendBeanList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private FriendAdapter mFriendAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webchat_list);
        initData();
        initViews();
        initListener();
    }

    private void initListener() {
        findViewById(R.id.btnA).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToIndex("A");
            }
        });

        findViewById(R.id.btnB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToIndex("B");
            }
        });

        findViewById(R.id.btnH).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToIndex("H");
            }
        });
    }

    private void initData() {
        mFriendBeanList.add(new FriendBean("AP","A"));
        mFriendBeanList.add(new FriendBean("爱学习","A"));
        mFriendBeanList.add(new FriendBean("AN代理","A"));
        mFriendBeanList.add(new FriendBean("波特","B"));
        mFriendBeanList.add(new FriendBean("BoB","B"));
        mFriendBeanList.add(new FriendBean("笨的死","B"));
        mFriendBeanList.add(new FriendBean("build","B"));
        mFriendBeanList.add(new FriendBean("奔奔","B"));
        mFriendBeanList.add(new FriendBean("韩信","H"));
        mFriendBeanList.add(new FriendBean("hello_world","H"));
        mFriendBeanList.add(new FriendBean("和畅","H"));
        mFriendBeanList.add(new FriendBean("胡兄弟","H"));
        mFriendBeanList.add(new FriendBean("黄兄弟","H"));
        mFriendBeanList.add(new FriendBean("呼呼","H"));
    }


    private void initViews() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mFriendAdapter = new FriendAdapter(R.layout.item_friend, mFriendBeanList);
        mRecyclerView.setAdapter(mFriendAdapter);
    }

    private void moveToIndex(String index){
        for (int i=0;i<mFriendBeanList.size();i++){
            FriendBean friendBean = mFriendBeanList.get(i);
            if (index.equals(friendBean.getNameSpelling())){
                moveToPosition(i);
                break;
            }
        }
    }

    /**
     * 平滑的滑动
     * @param position
     */
    public void smoothMoveToPosition(int position) {
        mRecyclerView.stopScroll();
        int firstItem = mLinearLayoutManager.findFirstVisibleItemPosition();
        int lastItem = mLinearLayoutManager.findLastVisibleItemPosition();
        if (position <= firstItem) {
            mRecyclerView.smoothScrollToPosition(position);
        } else if (position <= lastItem) {
            int top = mRecyclerView.getChildAt(position - firstItem).getTop();
            mRecyclerView.smoothScrollBy(0, top);
        } else {
            mRecyclerView.smoothScrollToPosition(position);
        }

    }

    /**
     * 直接滑动
     * @param position
     */
    public void moveToPosition(int position) {
        mRecyclerView.stopScroll();
        int firstItem = mLinearLayoutManager.findFirstVisibleItemPosition();
        int lastItem = mLinearLayoutManager.findLastVisibleItemPosition();
        if (position <= firstItem) {
            mRecyclerView.scrollToPosition(position);
        } else if (position <= lastItem) {
            int top = mRecyclerView.getChildAt(position - firstItem).getTop();
            mRecyclerView.scrollBy(0, top);
        } else {
            mRecyclerView.scrollToPosition(position);
        }
    }

}
