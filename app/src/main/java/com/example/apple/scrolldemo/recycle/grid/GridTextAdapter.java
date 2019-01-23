package com.example.apple.scrolldemo.recycle.grid;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.apple.scrolldemo.R;

import java.util.List;

/**
 * @author crazyZhangxl on 2019/1/22.
 * Describe:
 */
public class GridTextAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    public static final int LEFT = 0;
    public static final int CENTER = 1;
    public static final int RIGHT = 2;
    private Context mContext;
    private List<GridTextBean> mGridTextBeans;

    public GridTextAdapter(Context context, List<GridTextBean> gridTextBeans) {
        mContext = context;
        mGridTextBeans = gridTextBeans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if ( i == LEFT){
            return new LeftViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_left,viewGroup,false));
        }else if (i == CENTER){
            return new CenterViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_center,viewGroup,false));
        }else if (i == RIGHT){
            return new RightViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_right,viewGroup,false));
        }
        return onCreateViewHolder(viewGroup,i);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        GridTextBean gridTextBean = mGridTextBeans.get(i);
        if (viewHolder instanceof LeftViewHolder){
            LeftViewHolder leftViewHolder = (LeftViewHolder) viewHolder;
            leftViewHolder.tvText.setText(gridTextBean.getName());
        }else if (viewHolder instanceof CenterViewHolder){
            CenterViewHolder centerViewHolder = (CenterViewHolder) viewHolder;
            centerViewHolder.tvText.setText(gridTextBean.getName());
        }else if (viewHolder instanceof RightViewHolder){
            RightViewHolder rightViewHolder = (RightViewHolder) viewHolder;
            rightViewHolder.tvText.setText(gridTextBean.getName());
        }
    }

    @Override
    public int getItemCount() {
        return mGridTextBeans.size();
    }

    // size / x = 目标
    // 假如目标 = 3 / 那么 x = 2
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager){
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int i) {
                    int itemViewType = getItemViewType(i);
                    if (LEFT == itemViewType){
                        return 2;
                    }else if (CENTER == itemViewType){
                        return 6;
                    }else if (RIGHT == itemViewType){
                        return 3;
                    }
                    return 6;
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mGridTextBeans != null){
            return mGridTextBeans.get(position).getType();
        }
        return super.getItemViewType(position);

    }


    public class LeftViewHolder extends RecyclerView.ViewHolder{
        TextView tvText;
        public LeftViewHolder(@NonNull View itemView) {
            super(itemView);
            tvText = itemView.findViewById(R.id.tvText);
        }
    }

    public class CenterViewHolder extends RecyclerView.ViewHolder{
        TextView tvText;
        public CenterViewHolder(@NonNull View itemView) {
            super(itemView);
            tvText = itemView.findViewById(R.id.tvText);
        }
    }

    public class RightViewHolder extends RecyclerView.ViewHolder{
        TextView tvText;
        public RightViewHolder(@NonNull View itemView) {
            super(itemView);
            tvText = itemView.findViewById(R.id.tvText);
        }
    }
}
