package com.example.apple.scrolldemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.apple.scrolldemo.base.MoveActivity;
import com.example.apple.scrolldemo.base.ScrollShowActivity;
import com.example.apple.scrolldemo.behavior.BehaviorMoveActivity;
import com.example.apple.scrolldemo.behavior.BehaviorOneActivity;
import com.example.apple.scrolldemo.behavior.BehaviorTitleActivity;
import com.example.apple.scrolldemo.confict.NormalNestedActivity;
import com.example.apple.scrolldemo.confict.Nsv_Confict_Rv_Activity;
import com.example.apple.scrolldemo.confict.RecvActivity;
import com.example.apple.scrolldemo.confict.Rv_contains_rvActivity;
import com.example.apple.scrolldemo.fixed.FixedNormalActivity;
import com.example.apple.scrolldemo.fixed.StickyCrimActivity;
import com.example.apple.scrolldemo.fixed.StickyNestedActivity;
import com.example.apple.scrolldemo.fixed.StickyScrollActivity;
import com.example.apple.scrolldemo.fixed.review.RecycleFixedActivity;
import com.example.apple.scrolldemo.recycle.MulRvOneActivity;
import com.example.apple.scrolldemo.recycle.WebchatListActivity;
import com.example.apple.scrolldemo.recycle.city.MulCityActivity;
import com.example.apple.scrolldemo.recycle.ding.DingActivity;
import com.example.apple.scrolldemo.recycle.grid.GridTextActivity;
import com.example.apple.scrolldemo.refresh.RefreshListActivity;

import java.util.Map;

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

        findViewById(R.id.btnScrollBase).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ScrollShowActivity.class));
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

        findViewById(R.id.btnNormalScroll).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FixedNormalActivity.class));
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

        findViewById(R.id.btnCaseOne).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BehaviorOneActivity.class));
            }
        });

        findViewById(R.id.btnTitle).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BehaviorTitleActivity.class));
            }
        });

        findViewById(R.id.btnMove).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BehaviorMoveActivity.class));
            }
        });

        findViewById(R.id.btnRvOne).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MulRvOneActivity.class));
            }
        });

        findViewById(R.id.btnCity).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MulCityActivity.class));
            }
        });

        findViewById(R.id.btnAppbar).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AppBarScrollActivity.class));
            }
        });

        findViewById(R.id.btnWebchat).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, WebchatListActivity.class));
            }
        });

        findViewById(R.id.btnGrid).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GridTextActivity.class));
            }
        });


        findViewById(R.id.btnMoveTwo).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MoveActivity.class));
            }
        });

        findViewById(R.id.btnLocation).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LocationActivity.class));
            }
        });

        findViewById(R.id.btnNest).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                NormalNestedActivity.show(MainActivity.this);
            }
        });

        findViewById(R.id.rvHead).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                RecvActivity.show(MainActivity.this);
            }
        });

        findViewById(R.id.btnDing).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                DingActivity.show(MainActivity.this);
            }
        });

        findViewById(R.id.review).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                RecycleFixedActivity.show(MainActivity.this);
            }
        });

        findViewById(R.id.btnRefresh).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                RefreshListActivity.show(MainActivity.this);
            }
        });

        findViewById(R.id.rv_rv).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Rv_contains_rvActivity.show(MainActivity.this);
            }
        });

    }
}
