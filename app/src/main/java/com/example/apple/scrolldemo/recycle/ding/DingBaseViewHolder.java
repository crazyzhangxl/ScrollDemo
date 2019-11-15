package com.example.apple.scrolldemo.recycle.ding;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by apple on 2019-11-14.
 * description:
 */
public abstract class DingBaseViewHolder extends RecyclerView.ViewHolder {

    public DingBaseViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void onBind(DingBean t,int position);

}
