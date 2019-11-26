package com.example.apple.scrolldemo.confict;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.apple.scrolldemo.R;
import com.example.apple.scrolldemo.item_decoration.SimpleDecoration;

import java.util.ArrayList;
import java.util.List;

public class NormalNestedActivity extends AppCompatActivity {
    private RecyclerView mRvConflict;
    private List<String> mData = new ArrayList<>();
    private Button mBtnGet;

    public static void show(Activity activity){
        Intent intent = new Intent(activity,NormalNestedActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_nested);
        initData();
        initView();
    }

    private void initView() {
        mBtnGet = findViewById(R.id.btnGet);
        mRvConflict = findViewById(R.id.recyclerView);
        LinearLayoutManager manager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        // 默认为true
        manager.setSmoothScrollbarEnabled(true);
        // 允许自动测量
        manager.setAutoMeasureEnabled(true);
        mRvConflict.addItemDecoration(new SimpleDecoration());
        mRvConflict.setLayoutManager(manager);
        mRvConflict.setHasFixedSize(true);
        mRvConflict.setNestedScrollingEnabled(false);
        BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_scroll, mData) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                Log.e("性能", "加载了"+helper.getAdapterPosition() );
                helper.setText(R.id.tv, item);
            }

        };
        mRvConflict.setAdapter(baseQuickAdapter) ;


        mBtnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getIndexDecorationHeight(1);
            }
        });

    }

    private void getIndexDecorationHeight(int index){
        Rect rect = new Rect();
        View view =  mRvConflict.getLayoutManager().findViewByPosition(index);
        if (view != null){
            mRvConflict.getLayoutManager().calculateItemDecorationsForChild(view,rect);
            Log.e("decoration", "高度 = "+rect.height() );
        }
    }

    private void initData() {
        for (int i=0;i<80;i++){
            mData.add("每天进步一点点-.-"+i);
        }
    }
}
