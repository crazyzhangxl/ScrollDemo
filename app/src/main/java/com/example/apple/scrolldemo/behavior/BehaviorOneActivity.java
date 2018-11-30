package com.example.apple.scrolldemo.behavior;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.apple.scrolldemo.R;

/**
 * @author crazyZhangxl on 2018-11-28 8:18:47.
 * Describe: 初步的Behavior活动 ————————————————————
 */

public class BehaviorOneActivity extends AppCompatActivity {
    private TextView mTvDependentOn,mTvDependLook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavior_one);
        initViews();
        initListener();
    }

    private void initListener() {
        mTvDependentOn = findViewById(R.id.tvOn);
        mTvDependentOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 设置父亲容器顶部的偏移
                mTvDependentOn.offsetTopAndBottom(30);
            }
        });
    }

    private void initViews() {
        mTvDependLook = findViewById(R.id.look);
    }
}
