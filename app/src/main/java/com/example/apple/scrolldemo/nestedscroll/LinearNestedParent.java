package com.example.apple.scrolldemo.nestedscroll;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParent2;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.apple.scrolldemo.R;

/**
 * Created by apple on 2019-11-20.
 * description: 简易的LinearLayout嵌套RecyclerView...
 *
 *
 * 头部 + RecyclerView的形式
 *
 * 当上滑动时,此时头部需要进行收缩,那么父组件消费滑动距离
 * 向下滑动,当滑不动的时候,需要也需要父组件消费滑动距离
 *
 * 这里滑动速度并没有进行传递,scroller还不会...
 */
public class LinearNestedParent extends LinearLayout implements NestedScrollingParent{
    private NestedScrollingParentHelper mNestedScrollingParentHelper;
    private int topViewHeight = 150;
    private View mHeadView;

    public LinearNestedParent(Context context) {
        this(context,null);
    }

    public LinearNestedParent(Context context,  AttributeSet attrs) {
        this(context,attrs,0);
    }

    public LinearNestedParent(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);
    }

    /**
     * 布局结束后
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mHeadView = findViewById(R.id.head);
    }

    // 可判断view来决定是否允许嵌套滑动... nestedScrollAxes 方向
    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {
        mNestedScrollingParentHelper.onNestedScrollAccepted(child,target,axes);
    }

    @Override
    public void onStopNestedScroll(View child) {
        mNestedScrollingParentHelper.onStopNestedScroll(child);
    }


    /**
     * 前三个参数为输入参数 第三个输出
     * @param target
     * @param dx
     * @param dy
     * @param consumed 告诉child 我消耗了多少
     */
    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        boolean isHideTop = dy > 0 && getScrollY() < topViewHeight;
        // 当向下滑动 且头部隐藏 且RecyclerView不能再向下滑动了
        boolean isShowTop = dy < 0 &&  getScrollY() > 0 && !target.canScrollVertically( -1);
        if (isHideTop || isShowTop){
            scrollBy(0,dy);
            consumed[1] = dy;
        }

    }

    /**
     * step1: 重新测量高度,添加上需要隐藏的部分,否则会造成底部高度不够
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = getMeasuredHeight() +150;
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(height,MeasureSpec.EXACTLY));
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return false;
    }

    @Override
    public int getNestedScrollAxes() {
        return mNestedScrollingParentHelper.getNestedScrollAxes();
    }

    @Override
    public void scrollTo(int x, int y) {
        if (y < 0){
            y = 0;
        }

        if (y > topViewHeight){
            y = topViewHeight;
        }

        if ( y != getScrollY()){
            super.scrollTo(x, y);
        }
    }
}
