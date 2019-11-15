package com.example.apple.scrolldemo.recycle.ding;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.example.apple.scrolldemo.R;

/**
 * Created by apple on 2019-11-14.
 * description:
 */
public class DingIconViewHolder extends DingBaseViewHolder {
    private TextView tvDes;

    public DingIconViewHolder(@NonNull View itemView) {
        super(itemView);
        tvDes = itemView.findViewById(R.id.tvDescription);
    }

    @Override
    public void onBind(DingBean t, int position) {
        tvDes.setText(t.getIconText());
    }
}
