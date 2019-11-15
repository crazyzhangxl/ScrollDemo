package com.example.apple.scrolldemo.recycle.ding;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.example.apple.scrolldemo.R;

/**
 * Created by apple on 2019-11-14.
 * description:
 */
public class DingTitleViewHolder extends DingBaseViewHolder {
    private TextView tvTitle,tvMore;

    public DingTitleViewHolder(@NonNull View itemView) {
        super(itemView);
        tvMore = itemView.findViewById(R.id.tvMore);
        tvTitle = itemView.findViewById(R.id.tvTitle);
    }

    @Override
    public void onBind(DingBean t, int position) {
        tvTitle.setText(t.getTitle());
    }
}
