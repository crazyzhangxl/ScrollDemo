package com.example.apple.scrolldemo.recycle.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 2019-11-15.
 * description:自定义adapter
 */
public  abstract class ZxlRecyclerAdapter<B,V extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<V> {

    private List<B> mList = new ArrayList<>();
    private Context mContext = null;
    private ZxlHeadWithFooterAdapter mZxlHeadWithFooterAdapter = null;

    public List<B> getList() {
        return mList;
    }

    public Context getContext() {
        return mContext;
    }

    public void setZxlHeadWithFooterAdapter(ZxlHeadWithFooterAdapter zxlHeadWithFooterAdapter) {
        mZxlHeadWithFooterAdapter = zxlHeadWithFooterAdapter;
    }

    public ZxlHeadWithFooterAdapter getZxlHeadWithFooterAdapter() {
        return mZxlHeadWithFooterAdapter;
    }

    public ZxlRecyclerAdapter(List<B> list, Context context) {
        mList = list;
        mContext = context;
    }


    @Override
    public int getItemCount() {
        return mList == null ? 0:mList.size();
    }


    public final void notifyItemChangedWrapper(int position) {
        if (mZxlHeadWithFooterAdapter == null) {
            notifyItemChanged(position);
        } else {
            mZxlHeadWithFooterAdapter.
                    notifyItemChanged(mZxlHeadWithFooterAdapter.getHeadCount() + position);
        }
    }

    public final void notifyItemRemovedWrapper(int position){
        if (mZxlHeadWithFooterAdapter == null){
            notifyItemRemoved(position);
        }else {
            mZxlHeadWithFooterAdapter.notifyItemRemoved(mZxlHeadWithFooterAdapter.getHeadCount() + position);
        }
    }

    public final void notifyItemInsertWrapper(int position){
        if (mZxlHeadWithFooterAdapter == null){
            notifyItemInserted(position);
        }else {
            mZxlHeadWithFooterAdapter.notifyItemInserted(mZxlHeadWithFooterAdapter.getHeadCount()+position);
        }
    }

    public final void notifyChangedWrapper(){
        if (mZxlHeadWithFooterAdapter == null){
            notifyDataSetChanged();
        }else {
            mZxlHeadWithFooterAdapter.notifyDataSetChanged();
        }
    }
}
