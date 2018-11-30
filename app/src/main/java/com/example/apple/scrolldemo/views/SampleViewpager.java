package com.example.apple.scrolldemo.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * @author crazyZhangxl on 2018/11/5.
 * Describe: 一个简易的viewPager当然这是很不完善的 主要参考了郭神的效果
 *           主要用于了解 scroller的运用，包括onMeasure() onLayout() 对于子布局的测量和摆放
 */
public class SampleViewpager  extends ViewGroup{
    private Scroller mScroller;
    private int mTouchSlop;  // 最小滑动距离
    private float mMLastX;

    public SampleViewpager(Context context) {
        this(context,null);
    }

    public SampleViewpager(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public SampleViewpager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledPagingTouchSlop();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        for (int i=0;i<getChildCount();i++){
            View view = getChildAt(i);
            // 测量每一个子布局
            measureChild(view,widthMeasureSpec,heightMeasureSpec);
        }
    }

    /**
     * 继承ViewGroup必定要实现的------
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed){
            for (int i=0;i<getChildCount();i++){
                View child = getChildAt(i);
                // 水平摆放
                child.layout(i*child.getMeasuredWidth(),0,(i+1)*child.getMeasuredWidth(),child.getMeasuredHeight());
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean isIntercept = false;
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                mMLastX = ev.getRawX();
                break;
            case MotionEvent.ACTION_MOVE:
                float moveRawX =  ev.getRawX();
                if (Math.abs(moveRawX-mMLastX) > mTouchSlop){
                    isIntercept = true;
                }
                mMLastX = moveRawX;
                break;
            default:
                break;
        }
        return isIntercept;
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                float moveRawX = event.getRawX();
                int scrollX = (int) (mMLastX - moveRawX);
                Log.e("结果", "onTouchEvent: "+scrollX );
                // 判断边界----
                scrollBy(scrollX,0);
                mMLastX = moveRawX;
                break;
            case MotionEvent.ACTION_UP:
                // 抬起的时候进行校验 进行回滚
                // 当手指抬起时，根据当前的滚动值来判定应该滚动到哪个子控件的界面
                int targetIndex = (getScrollX() + getWidth() / 2) / getWidth();
                int dx = targetIndex * getWidth() - getScrollX();
                // 第二步，调用startScroll()方法来初始化滚动数据并刷新界面
                mScroller.startScroll(getScrollX(), 0, dx, 0);
                invalidate();
                break;
                default:
                    break;
        }

        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }
}
