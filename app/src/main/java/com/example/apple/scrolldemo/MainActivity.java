package com.example.apple.scrolldemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.apple.scrolldemo.confict.Nsv_Confict_Rv_Activity;

/**
 * @author crazyZhangxl on 2018-11-1 19:42:28.
 * Describe:ScrollView 滑动组件学习
 */

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnTest1).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,BasicScrollActivity.class));
            }
        });

        findViewById(R.id.btnScroll).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ScrollAlphaActivity.class));
            }
        });

        findViewById(R.id.btnLikeWc).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LikeWChatActivity.class));
            }
        });

        findViewById(R.id.btnCoor).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,StickyCrimActivity.class));
            }
        });

        findViewById(R.id.btnScroll2).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,StickyScrollActivity.class));
            }
        });

        findViewById(R.id.nested).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,StickyNestedActivity.class));
            }
        });

        findViewById(R.id.btnConflict).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Nsv_Confict_Rv_Activity.class));
            }
        });

    }
}
