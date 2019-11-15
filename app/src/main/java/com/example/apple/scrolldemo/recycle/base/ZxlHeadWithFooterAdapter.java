package com.example.apple.scrolldemo.recycle.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Created by apple on 2019-11-14.
 * description:
 */
public class ZxlHeadWithFooterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LinearLayout mHeaderLayout = null;
    private LinearLayout mFooterLayout = null;
    public static final int HEADER_VIEW = 0x00000111;
    public static final int FOOTER_VIEW = 0x00000333;
    private RecyclerView.Adapter mInnerAdapter;

    public ZxlHeadWithFooterAdapter(ZxlRecyclerAdapter innerAdapter,Context context) {
        mInnerAdapter = innerAdapter;
        innerAdapter.setZxlHeadWithFooterAdapter(this);

        // 初始化头尾部布局...
        if (mHeaderLayout == null) {
            mHeaderLayout = new LinearLayout(context);
            mHeaderLayout.setOrientation(LinearLayout.VERTICAL);
            mHeaderLayout.setLayoutParams(new RecyclerView.LayoutParams(MATCH_PARENT, WRAP_CONTENT));
        }

        if (mFooterLayout == null) {
            mFooterLayout = new LinearLayout(context);
            mFooterLayout.setOrientation(LinearLayout.VERTICAL);
            mFooterLayout.setLayoutParams(new RecyclerView.LayoutParams(MATCH_PARENT, WRAP_CONTENT));
        }
    }

    public RecyclerView.Adapter getInnerAdapter() {
        return mInnerAdapter;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i){
            case HEADER_VIEW:
                return new RecyclerView.ViewHolder(mHeaderLayout){

                };
            case FOOTER_VIEW:
                return new RecyclerView.ViewHolder(mFooterLayout) {

                };
                default:
                    return mInnerAdapter.onCreateViewHolder(viewGroup,i);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int headCounts = getHeadCount();
        if (i < headCounts){
            return;
        }else {
            int adjPosition = i - headCounts;
            int adapterCount = mInnerAdapter.getItemCount();
            if (adjPosition < adapterCount){
                mInnerAdapter.onBindViewHolder(viewHolder,adjPosition);
            }else {
                return;
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        int headCounts = getHeadCount();
        if (position < headCounts){
            return HEADER_VIEW;
        }else {
            int adjPosition = position - headCounts;
            int adapterCount = mInnerAdapter.getItemCount();
            if (adjPosition < adapterCount){
                return mInnerAdapter.getItemViewType(adjPosition);
            }else {
                return FOOTER_VIEW;
            }
        }
    }

    /**
     * 返回数量
     * @return
     */
    @Override
    public final int getItemCount() {
        return getHeadCount()+getTailCount()+mInnerAdapter.getItemCount();
    }


    public final int getHeadCount(){
        return 1;
    }


    public final int getTailCount(){
        return 1;
    }

    @Override
    public final void onAttachedToRecyclerView(RecyclerView recyclerView) {
        mInnerAdapter.onAttachedToRecyclerView(recyclerView);
    }

    /**
     * 添加尾部的方法等
     * @param header
     */

    public void addHeadView(View header){
        mHeaderLayout.addView(header);
    }

    public void removeHeadView(View header){
        mHeaderLayout.removeView(header);
    }

    public void addFootView(View footer){
        mFooterLayout.addView(footer);
    }

    public void removeFootView(View footer){
        mFooterLayout.removeView(footer);
    }
}
