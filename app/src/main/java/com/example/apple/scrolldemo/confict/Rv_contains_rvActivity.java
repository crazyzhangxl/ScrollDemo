package com.example.apple.scrolldemo.confict;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.apple.scrolldemo.R;
import com.example.apple.scrolldemo.confict.recv_recv.Beans;
import com.example.apple.scrolldemo.confict.recv_recv.WrapperAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView嵌套问题
 *
 * 同样的RecyclerView嵌套也造成了性能问题...
 */
public class Rv_contains_rvActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<Beans> mBeans = new ArrayList<>();

    public static void show(Activity activity){
        Intent intent = new Intent(activity,Rv_contains_rvActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_contans_rv);

        initData();

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mRecyclerView.setAdapter(new WrapperAdapter(mBeans,this));
    }

    private void initData() {
        List<String> inners = new ArrayList<>();
        for (int i=0;i<30;i++){
            inners.add("数据 "+i);
        }

        mBeans.add(new Beans("头部1",inners));
        mBeans.add(new Beans("头部2",inners));
        mBeans.add(new Beans("头部3",inners));
        mBeans.add(new Beans("头部4",inners));
    }
}
