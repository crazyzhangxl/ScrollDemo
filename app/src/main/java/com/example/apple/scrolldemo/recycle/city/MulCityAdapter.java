package com.example.apple.scrolldemo.recycle.city;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.apple.scrolldemo.R;

import java.util.List;

/**
 * @author crazyZhangxl on 2019/1/17.
 * Describe:
 */
public class MulCityAdapter extends BaseMultiItemQuickAdapter<MulCityBean,BaseViewHolder>{
    private Context context;
    public static final int[] BLUR_IMAGE = {
            R.mipmap.ic_city_blur0,
            R.mipmap.ic_city_blur1,
            R.mipmap.ic_city_blur2,
            R.mipmap.ic_city_blur3,
            R.mipmap.ic_city_blur4,
            R.mipmap.ic_city_blur5};
    private Drawable mDrawableLocation;
    private ImageView mImageView;
    private TextView mTv_status;
    private ImageView mView_del;
    private View mView_hover;
    private TextView mText_city_name;
    private ImageView mMChecked;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MulCityAdapter(List<MulCityBean> data,Context context) {
        super(data);
        // 对应类型绑定不同的布局
        addItemType(MulCityBean.CITY, R.layout.item_city_followed_city);
        addItemType(MulCityBean.ADD_SYMBOL, R.layout.item_city_add_city);
        this.context = context;
        initDrawable();
    }

    /**
     * 初始化Drawable-----
     */
    private void initDrawable() {
        mDrawableLocation = ContextCompat.getDrawable(context,R.mipmap.ic_location);
        mDrawableLocation.setBounds(0,0,dpToPx(context,14),dpToPx(context,14));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    protected void convert(BaseViewHolder helper, MulCityBean item) {
        switch (helper.getItemViewType()){
            case MulCityBean.CITY:
                if (item == null){
                    return;
                }
                mImageView = helper.getView(R.id.image);
                mImageView.setImageResource(BLUR_IMAGE[helper.getAdapterPosition()% BLUR_IMAGE.length]);
                helper.setText(R.id.city_temp,item.temp);
                mText_city_name = helper.getView(R.id.city_name);
                mText_city_name.setText(item.cityName);

                mTv_status = helper.getView(R.id.city_status);
                mTv_status.setText(item.weatherStatus);
                mView_del = helper.getView(R.id.delete);
                helper.addOnClickListener(R.id.delete);
                // 设置删除按钮的显示
                mView_del.setVisibility(item.isShowDelete()?View.VISIBLE:View.GONE);

                // 设备阴影的显示
                mView_hover = helper.getView(R.id.hover);
                mView_hover.setVisibility(item.isShowDelete()?View.VISIBLE : View.GONE);

                // 是否是当前
                mMChecked = helper.getView(R.id.checked);
                mMChecked.setVisibility(item.isChecked()?View.VISIBLE:View.GONE);
                break;
                case MulCityBean.ADD_SYMBOL:
                    break;
                    default:
                        break;
        }
    }

    private int dpToPx(Context context,int dp){
        if (context == null){
            return  0;
        }

        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) (displayMetrics.density*(dp+0.5f));
    }
}
