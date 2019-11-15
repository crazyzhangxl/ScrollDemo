package com.example.apple.scrolldemo.recycle.ding;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.apple.scrolldemo.R;
import com.example.apple.scrolldemo.recycle.base.ZxlRecyclerAdapter;

import java.util.List;

/**
 * Created by apple on 2019-11-14.
 * description:
 */
public class DingAdapter extends ZxlRecyclerAdapter<DingBean,DingBaseViewHolder> {
    private static final int TYPE_TITLE = 1;
    private static final int TYPE_ICON = 2;

    public DingAdapter(List<DingBean> list, Context context) {
        super(list, context);
    }

    @NonNull
    @Override
    public DingBaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // 根据类型进行数据返回
        switch (i){
            case TYPE_TITLE:
                return new DingTitleViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.multi_item_head,viewGroup,false));
            case TYPE_ICON:
                return new DingIconViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.multi_item_inner,viewGroup,false));
            default:
                return new DingTitleViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.multi_item_head,viewGroup,false));

        }
    }

    @Override
    public void onBindViewHolder(@NonNull DingBaseViewHolder dingBaseViewHolder, int i) {
        DingBean dingBean = getList().get(i);
        // 传入数据以及position
        dingBaseViewHolder.onBind(dingBean,i);
    }

    @Override
    public int getItemViewType(int position) {
        String category = getList().get(position).getCategory();
        if (TextUtils.isEmpty(category)){
            return TYPE_TITLE;
        }
        return TYPE_ICON;
    }


    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager){
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int i) {
                    // 注意这里是添加头部尾部的情况哦....
                    int itemViewType;
                    if (getZxlHeadWithFooterAdapter() != null){
                        if ( i == 0){
                            return gridLayoutManager.getSpanCount();
                        }else if (i -1 == getItemCount()){
                            return gridLayoutManager.getSpanCount();
                        }

                        itemViewType = getItemViewType(i-1);
                    }else {
                        itemViewType = getItemViewType(i);
                    }

                    switch (itemViewType){
                        case TYPE_TITLE:
                            return gridLayoutManager.getSpanCount();
                        case TYPE_ICON:
                            return 1;
                            default:
                                return gridLayoutManager.getSpanCount();
                    }
                }

            } );
        }
    }

}
