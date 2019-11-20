package com.example.apple.scrolldemo.refresh;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.apple.scrolldemo.R;

import java.util.ArrayList;
import java.util.List;

public class RefreshListActivity extends AppCompatActivity {
    private RefreshListView mRefreshListView;
    private List<String> mData = new ArrayList<>();

    public static void show(Activity activity){
        Intent intent = new Intent(activity,RefreshListActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_list);
        for (int i=0;i<50;i++){
            mData.add("数据"+i);
        }
        mRefreshListView = findViewById(R.id.listview);
        mRefreshListView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return mData.size();
            }

            @Override
            public Object getItem(int position) {
                return mData.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null){
                    convertView = LayoutInflater.from(RefreshListActivity.this).inflate(R.layout.item_center,null);
                }
                TextView tvText =  convertView.findViewById(R.id.tvText);
                tvText.setText(mData.get(position));
                return convertView;
            }
        });
    }
}
