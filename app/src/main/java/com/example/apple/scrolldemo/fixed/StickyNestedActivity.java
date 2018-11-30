package com.example.apple.scrolldemo.fixed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.apple.scrolldemo.R;

/**
 * @author crazyZhangxl on 2018-11-6 15:34:11.
 * Describe: 模仿张弘扬大神
 *    粘性布局通过NestScrolling实现-------
 *    (初步窥探了NestedScrolling 这一神奇组件)
 */

public class StickyNestedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky_nested);
    }
}
