package com.example.apple.scrolldemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * @author crazyZhangxl on 2019-1-24 23:05:48.
 * Describe:
 */

public class LocationActivity extends AppCompatActivity {
    private View child;
    private int[] location = new int[2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        initViews();
        initTreeObserver();
    }

    private void initTreeObserver() {
        child.getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        child.getLocationInWindow(location);
                        Log.e("日志", "getLocationInWindow Y = "+location[1] );
                        Log.e("日志", "getTop  = "+child.getTop() );
                        Log.e("日志", "getRelativeTop =  "+getRelativeTop(child));
                        child.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
    }

    private void initViews() {
        child = findViewById(R.id.child);
    }

    private int getRelativeTop(View view){
        if (view.getParent() == view.getRootView()){
            return view.getTop();
        }else {
            return view.getTop()+getRelativeTop((View) view.getParent());
        }
    }
}
