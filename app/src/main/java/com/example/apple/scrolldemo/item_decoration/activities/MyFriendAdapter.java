package com.example.apple.scrolldemo.item_decoration.activities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.apple.scrolldemo.R;
import com.example.apple.scrolldemo.recycle.FriendBean;

import java.util.List;

/**
 * Created by apple on 2019-11-20.
 * description:分组的adapter
 */
public class MyFriendAdapter extends BaseQuickAdapter<FriendBean, BaseViewHolder> {
    private List<FriendBean> mFriendBeans;
    private Context mContext;

    public MyFriendAdapter(int layoutResId, @Nullable List<FriendBean> data) {
        super(layoutResId, data);
        this.mFriendBeans = data;
    }


    @Override
    protected void convert(@NonNull BaseViewHolder helper, FriendBean item) {
        helper.setText(R.id.tvUserName,item.getName());
    }


    public boolean isGroupHead(int position){
        if (position == 1){
            return true;
        }else {
            String cur = mFriendBeans.get(position-1).getNameSpelling();
            String pre = mFriendBeans.get(position -2).getNameSpelling();
            return !cur.equals(pre);
        }
    }

    public List<FriendBean> getFriendBeans() {
        return mFriendBeans;
    }
}
