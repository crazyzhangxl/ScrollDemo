package com.example.apple.scrolldemo.confict;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.apple.scrolldemo.R;

import java.util.List;

/**
 * Created by apple on 2019-11-18.
 * description:
 */
public class PicTextAdapter extends RecyclerView.Adapter<PicTextAdapter.ViewHolder> {
    private List<String> mData;
    private Context mContext;

    public PicTextAdapter(List<String> data, Context context) {
        mData = data;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =  LayoutInflater.from(mContext).inflate(R.layout.item_pic_text,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Log.e("adapter", "onBindViewHolder: "+i );
        viewHolder.tvText.setText(mData.get(i));
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0: mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvText = itemView.findViewById(R.id.tvText);
        }
    }
}
