package com.example.apple.scrolldemo.confict.recv_recv;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apple.scrolldemo.R;
import com.example.apple.scrolldemo.confict.PicTextAdapter;
import com.example.apple.scrolldemo.confict.Rv_contains_rvActivity;

import java.util.List;

/**
 * Created by apple on 2019-11-19.
 * description:
 */
public class WrapperAdapter extends RecyclerView.Adapter<InnerHolder> {

    private List<Beans> mBeans;
    private Context mContext;

    public WrapperAdapter(List<Beans> beans, Context context) {
        mBeans = beans;
        mContext = context;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =  LayoutInflater.from(mContext).inflate(R.layout.item_inner_recv,viewGroup,false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder innerHolder, int i) {
        innerHolder.getHeadView().setText(mBeans.get(i).getHead());
        innerHolder.getRecyclerView().setLayoutManager(new GridLayoutManager(mContext,3, LinearLayoutManager.VERTICAL,false));
        innerHolder.getRecyclerView().setAdapter(new PicTextAdapter(mBeans.get(i).getContent(),mContext));
    }

    @Override
    public int getItemCount() {
        return mBeans.size();
    }
}
