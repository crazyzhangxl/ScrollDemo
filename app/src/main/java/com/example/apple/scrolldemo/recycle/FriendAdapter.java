package com.example.apple.scrolldemo.recycle;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.apple.scrolldemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author crazyZhangxl on 2019/1/20.
 * Describe:
 */
public class FriendAdapter extends BaseQuickAdapter<FriendBean,BaseViewHolder>{
    private List<FriendBean> mFriendBeanList;
    public FriendAdapter(int layoutResId, @Nullable List<FriendBean> data) {
        super(layoutResId, data);
        mFriendBeanList = new ArrayList<>();
        mFriendBeanList = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, FriendBean item) {
        helper.setText(R.id.tvUserName,item.getName());
        int position = helper.getAdapterPosition();
        String nowSpelling = item.getNameSpelling();
        TextView tvIndex = helper.getView(R.id.tvIndex);
        View viewLine = helper.getView(R.id.line);
        String index = "";
        if (position == 0){
            index = nowSpelling;
        }else {
            String beforeSpelling = mFriendBeanList.get(position - 1).getNameSpelling();
            if (!beforeSpelling.equals(nowSpelling)){
                index = nowSpelling;
            }
        }

        int nextIndex = position + 1;
        if (nextIndex < mFriendBeanList.size() - 1) {
            //得到下一个字母
            String nextLetter = mFriendBeanList.get(nextIndex).getNameSpelling();
            //如果和下一个字母的首字母不同则隐藏下划线
            if (!nextLetter.equalsIgnoreCase(nowSpelling)) {
               viewLine.setVisibility(View.INVISIBLE);
            } else {
                viewLine.setVisibility(View.VISIBLE);

            }
        } else {
            viewLine.setVisibility(View.VISIBLE);
        }
        if (position == mFriendBeanList.size() - 1) {
            viewLine.setVisibility(View.INVISIBLE);
        }


        if (TextUtils.isEmpty(index)){
            tvIndex.setVisibility(View.GONE);
        }else {
            tvIndex.setText(index);
            tvIndex.setVisibility(View.VISIBLE);
        }
    }
}
