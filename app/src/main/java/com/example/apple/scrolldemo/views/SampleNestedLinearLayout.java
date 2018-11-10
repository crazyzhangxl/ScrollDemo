package com.example.apple.scrolldemo.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.OverScroller;

import com.example.apple.scrolldemo.R;

/**
 * @author crazyZhangxl on 2018/11/6.
 * Describe: 自定义NestedLinearLayout 继承自NestedScrollingParent
 *            用来处理特定的布局视图
 *            改造自张弘扬大神,但个人觉得这里很不好
 */
public class SampleNestedLinearLayout extends LinearLayout implements NestedScrollingParent{
    private View nslTitle, nslHeader;
    private NestedScrollView nslContent;
    private NestedScrollingParentHelper helper;
    private  int headHeight;
    private int TOP_CHILD_FLING_THRESHOLD = 3;
    private OverScroller mScroller;
    private ValueAnimator mOffsetAnimator;
    private int  topDy,bottomDy;


    public SampleNestedLinearLayout(Context context) {
        this(context,null);
    }

    public SampleNestedLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initSnl();
    }

    private void initSnl() {
        helper = new NestedScrollingParentHelper(this);
        mScroller = new OverScroller(getContext());
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        nslHeader = getChildAt(0);
        nslTitle = getChildAt(1);
        nslContent = (NestedScrollView) getChildAt(2);
        ViewTreeObserver viewTreeObserver = nslHeader.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                nslHeader.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                headHeight = nslHeader.getMeasuredHeight();
            }
        });
    }

    /**
     * 允许滑动
     * @param child
     * @param target
     * @param nestedScrollAxes
     * @return
     */
    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int nestedScrollAxes) {
        return true;
    }


    @Override
    public void onStopNestedScroll(@NonNull View child) {
        helper.onStopNestedScroll(child);
    }


    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes) {
        helper.onNestedScrollAccepted(child,target,axes);
    }

    /**
     *  后于child滚动
     */
    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {

    }


    /**
     * 是否消费了fling
     * @param target
     * @param velocityX
     * @param velocityY
     * @return
     */
    @Override
    public boolean onNestedPreFling(@NonNull View target, float velocityX, float velocityY) {
        return false;
    }


    @Override
    public boolean onNestedFling(@NonNull View target, float velocityX, float velocityY, boolean consumed) {
        //如果是recyclerView 根据判断第一个元素是哪个位置可以判断是否消耗
        //这里判断如果第一个元素的位置是大于TOP_CHILD_FLING_THRESHOLD的
        //认为已经被消耗，在animateScroll里不会对velocityY<0时做处理
        if (target instanceof RecyclerView && velocityY < 0) {
            final RecyclerView recyclerView = (RecyclerView) target;
            final View firstChild = recyclerView.getChildAt(0);
            final int childAdapterPosition = recyclerView.getChildAdapterPosition(firstChild);
            consumed = childAdapterPosition > TOP_CHILD_FLING_THRESHOLD;
        }
        if (!consumed) {
            Log.e("result --- consumed = ",consumed+"");
            animateScroll(velocityY, computeDuration(0),consumed);
        } else {
            animateScroll(velocityY, computeDuration(velocityY),consumed);
        }
        return true;
        // default return false
    }

    @Override
    public int getNestedScrollAxes() {
        return helper.getNestedScrollAxes();
    }



    /**
     *    最终要这里进行相应的处理
     *
     *     先于child滚动
     *     前3个为输入参数，最后一个是输出参数
     *
     *     child首先会请求Parent,parent进行处理,并执行一定的操作,然后高度child我消耗了多少
     *
     *     super.onNestedPreScroll(target,dx,dy,consumed); 如果不做处理则,NSL处于静止状态****
     * @param target
     * @param dx
     * @param dy  >0 为scrollview向上滑动 ; dy < 0 为scrollView向下滑动
     * @param consumed
     */
    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed) {
        //super.onNestedPreScroll(target,dx,dy,consumed);
        Log.e("dy = ",dy+"  ");
       if (showHead(dy,target) || hideHead(dy)){
           // 执行滚动
           scrollBy(0,dy);
           // 告诉孩子我消耗了多少
           consumed[1] = dy;
       }
    }

    /**
     *  dy < 0 说明 scrollView在下拉 此时应当判断是否需要显示head
     */
    public boolean showHead(int dy,View target) {
        if (dy < 0) {
            return getScrollY() >= 0 && !ViewCompat.canScrollVertically(target, -1);
        }
        return false;
    }

    /**
     *   dy > 0  scrollView正在上拉 此时判断是否要隐藏
     */
    public boolean hideHead(int dy) {
        return dy > 0 && getScrollY() <  headHeight;
    }

    @Override
    public void scrollTo(int x, int y)
    {
        if (y < 0)
        {
            y = 0;
        }
        if (y > headHeight)
        {
            y = headHeight;
        }
        if (y != getScrollY())
        {
            super.scrollTo(x, y);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //重新测量高度
        headHeight = nslHeader.getMeasuredHeight();
        int newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredHeight() + headHeight, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, newHeightMeasureSpec);
    }


    /**
     * 根据速度计算滚动动画持续时间
     * @param velocityY
     * @return
     */
    private int computeDuration(float velocityY) {
        final int distance;
        if (velocityY > 0) {
            distance = Math.abs(nslHeader.getHeight() - getScrollY());
        } else {
            distance = Math.abs(nslHeader.getHeight() - (nslHeader.getHeight() - getScrollY()));
        }

        Log.e("result --- distance = ",distance+"");


        final int duration;
        velocityY = Math.abs(velocityY);
        if (velocityY > 0) {
            duration = 3 * Math.round(1000 * (distance / velocityY));
        } else {
            final float distanceRatio = (float) distance / getHeight();
            duration = (int) ((distanceRatio + 1) * 150);
        }
        Log.e("result --- duration = ",duration+"");
        return duration;

    }


    private void animateScroll(float velocityY, final int duration,boolean consumed) {
        final int currentOffset = getScrollY();
        final int topHeight = nslHeader.getHeight();
        Log.e("result --- current = ",currentOffset+"");
        Log.e("result --- topHeight = ",topHeight+"");
        if (mOffsetAnimator == null) {
            mOffsetAnimator = new ValueAnimator();
            mOffsetAnimator.setInterpolator(new LinearInterpolator());
            mOffsetAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    if (animation.getAnimatedValue() instanceof Integer) {
                        scrollTo(0, (Integer) animation.getAnimatedValue());
                    }
                }
            });
        } else {
            mOffsetAnimator.cancel();
        }
        int min = Math.min(duration, 200);
        mOffsetAnimator.setDuration(min);
        Log.e("速度",velocityY+" ");
        if (velocityY >= 0) {
            // topHeight
            if (velocityY > 1000){
                topDy = topHeight;
            }else {
                topDy = (int) (currentOffset + (topHeight - currentOffset)/1000f);
            }
            mOffsetAnimator.setIntValues(currentOffset,topDy);
            mOffsetAnimator.start();
        }else {
            //如果子View没有消耗down事件 那么就让自身滑倒0位置
            if(!consumed){
                if (velocityY <-1000){
                    bottomDy = 0;
                }else {
                    bottomDy =(int)(currentOffset*(1+velocityY/1000f));
                }

                mOffsetAnimator.setIntValues(currentOffset,bottomDy);
                mOffsetAnimator.start();
            }

        }
    }


    @Override
    public void computeScroll()
    {
        if (mScroller.computeScrollOffset())
        {
            scrollTo(0, mScroller.getCurrY());
            invalidate();
        }
    }





}
