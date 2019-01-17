package com.example.apple.scrolldemo.recycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.apple.scrolldemo.R;
import com.example.apple.scrolldemo.recycle.one.SessionAdapter;
import com.example.apple.scrolldemo.recycle.one.TimeUtils;
import com.example.apple.scrolldemo.recycle.one.model.Message;
import com.example.apple.scrolldemo.recycle.one.model.MessageDirection;
import com.example.apple.scrolldemo.recycle.one.model.MessageType;
import com.example.apple.scrolldemo.recycle.one.model.TextMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author crazyZhangxl on 2019-1-15 22:45:41.
 * Describe:RecyclerView多布局版本1
 */

public class MulRvOneActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Message> mMessageList = new ArrayList<>();
    private SessionAdapter mSessionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mul_rv_one);
        initData();
        initViews();
        setRecycleView();
    }

    private void initData() {
        mMessageList.add(new TextMessage(TimeUtils.getInstance().convertTimeToLong("2019-01-10 12:01:20"),
                MessageDirection.RECEIVE.integerValue,"小李", MessageType.TEXT,"你好"));
        mMessageList.add(new TextMessage(TimeUtils.getInstance().convertTimeToLong("2019-01-11 12:01:20"),
                MessageDirection.SEND.integerValue,"小王",MessageType.TEXT,"晚上好"));
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
    }

    private void setRecycleView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mSessionAdapter = new SessionAdapter(this, mMessageList);
        recyclerView.setAdapter(mSessionAdapter);
    }
}
