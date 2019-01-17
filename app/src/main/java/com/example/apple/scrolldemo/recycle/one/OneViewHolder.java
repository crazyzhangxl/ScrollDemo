package com.example.apple.scrolldemo.recycle.one;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author crazyZhangxl on 2019/1/15.
 * Describe: 基本的ViewHolder
 */
public class OneViewHolder extends RecyclerView.ViewHolder{
    protected View mConvertView;
    private Context mContext;

    public OneViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public OneViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        mConvertView = itemView;
        mContext = context;
    }

    public View getConvertView() {
        return mConvertView;
    }

    public void setConvertView(View convertView) {
        mConvertView = convertView;
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context;
    }
}
