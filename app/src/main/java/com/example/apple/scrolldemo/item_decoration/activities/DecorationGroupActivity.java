package com.example.apple.scrolldemo.item_decoration.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.apple.scrolldemo.R;
import com.example.apple.scrolldemo.item_decoration.FriendDecoration;
import com.example.apple.scrolldemo.recycle.FriendAdapter;
import com.example.apple.scrolldemo.recycle.FriendBean;

import java.util.ArrayList;
import java.util.List;

public class DecorationGroupActivity extends AppCompatActivity {
    private List<FriendBean> mFriendBeanList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private TextView mTextView;
    public static void show(Activity activity){
        Intent intent = new Intent(activity,DecorationGroupActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decoration_group);
        initData();
        initViews();
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
        mTextView = findViewById(R.id.tvText);
        mRecyclerView.addItemDecoration(new FriendDecoration());
        MyFriendAdapter myFriendAdapter = new MyFriendAdapter(R.layout.item_friend, mFriendBeanList);
//        LinearLayout linearLayout = new LinearLayout(this);
//        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,400));
//        linearLayout.setAlpha(0);
         View linearLayout=  LayoutInflater.from(this).inflate(R.layout.layout_head,null,false);
        myFriendAdapter.addHeaderView(linearLayout);
        mRecyclerView.setAdapter(myFriendAdapter);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                View view = recyclerView.getLayoutManager().findViewByPosition(0);
                View view = recyclerView.findViewById(R.id.head);
                if (view != null){
                    View parent = (View) view.getParent();
                    Log.i("onScrolled","dy="+parent.getTop());
                    setAlpha(parent.getTop());

                }

            }


        });

    }

    private void setAlpha(int distance) {
        mTextView.setAlpha((200+distance)/200f);
    }


}
