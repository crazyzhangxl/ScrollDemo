package com.example.apple.scrolldemo.confict.recv_recv;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.apple.scrolldemo.R;

/**
 * Created by apple on 2019-11-19.
 * description:
 */
public class InnerHolder extends RecyclerView.ViewHolder {
    private TextView mHeadView;
    private RecyclerView mRecyclerView;

    public TextView getHeadView() {
        return mHeadView;
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    public InnerHolder(@NonNull View itemView) {
        super(itemView);
        mHeadView = itemView.findViewById(R.id.tvHead);
        mRecyclerView = itemView.findViewById(R.id.innerRecycle);
    }
}
