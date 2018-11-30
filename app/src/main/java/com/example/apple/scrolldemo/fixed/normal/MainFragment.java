package com.example.apple.scrolldemo.fixed.normal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.apple.scrolldemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author crazyZhangxl on 2018/11/27.
 * Describe:
 */
public class MainFragment extends Fragment{
    private RecyclerView mRecyclerView;
    private FloatingActionButton mFloatingActionButton;
    private BaseQuickAdapter<String,BaseViewHolder> mQuickAdapter;
    private List<String> mStringsList = new ArrayList<>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for (int i =0 ;i<30;i++){
            mStringsList.add("数据:"+i);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initViews(view);
        initAdapters();
        return view;
    }

    private void initAdapters() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        mQuickAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_scroll,mStringsList) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.tv,item);
            }
        };
        mRecyclerView.setAdapter(mQuickAdapter);

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerView.scrollToPosition(0);
            }
        });
    }

    private void initViews(View view) {
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mFloatingActionButton = view.findViewById(R.id.fab);
    }
}
