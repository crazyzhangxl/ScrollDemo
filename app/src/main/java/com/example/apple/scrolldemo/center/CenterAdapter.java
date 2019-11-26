package com.example.apple.scrolldemo.center;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.apple.scrolldemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 2019-11-25.
 * description:
 */
public class CenterAdapter extends RecyclerView.Adapter<CenterAdapter.ViewHolder> {
    private Context mContext;
    private List<String> mList = new ArrayList<>();
    private MyOnItemClickListener mMyOnItemClickListener;

    public CenterAdapter(Context context, List<String> list,MyOnItemClickListener myOnItemClickListener) {
        mContext = context;
        mList = list;
        this.mMyOnItemClickListener = myOnItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_center,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.mTvDes.setText(mList.get(i));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMyOnItemClickListener != null){
                    mMyOnItemClickListener.onItemClick(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvDes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvDes = itemView.findViewById(R.id.tvText);
        }
    }

    public interface MyOnItemClickListener{
        void onItemClick(int position);
    }
}
