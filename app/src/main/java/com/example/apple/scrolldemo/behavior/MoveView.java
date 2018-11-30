package com.example.apple.scrolldemo.behavior;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author crazyZhangxl on 2018/11/28.
 * Describe:
 */
public class MoveView  extends View{
    private int defaultSizeDP = 60;
    private int lastX;
    private int lastY;

    public MoveView(Context context) {
        this(context,null);
    }

    public MoveView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public MoveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initSize();
    }

    private void initSize() {
        float density = getContext().getResources().getDisplayMetrics().density;
        defaultSizeDP = (int)(density * defaultSizeDP);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int resultWidth = widthSize;
        int resultHeight = heightSize;
        if (MeasureSpec.AT_MOST == widthMode){
            resultWidth = defaultSizeDP;
        }

        if (MeasureSpec.AT_MOST == heightMode){
            resultHeight = defaultSizeDP;
        }
        setMeasuredDimension(resultWidth,resultHeight);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int endX = (int) event.getRawX();
                int endY = (int) event.getRawY();
                // 设置上下左右的偏移
                ViewCompat.offsetTopAndBottom(this,endY-lastY);
                ViewCompat.offsetLeftAndRight(this,endX-lastX);
                lastX = endX;
                lastY = endY;
                break;
                default:
                    break;
        }
        return true;
    }

   /*
        ViewCompat.offsetTopAndBottom()
        ViewCompat.offsetLeftAndRight()
        设置相对之前位置的上下偏移量,增量

        绝对值量
        改变位置 还可以这样去书写 ——————————————————————————  使用向父亲布局申请空间的方式
        CoordinatorLayout.MarginLayoutParams layoutParams = (CoordinatorLayout.MarginLayoutParams) v.getLayoutParams();
        layoutParams.leftMargin = x;
        layoutParams.topMargin = y;
        v.setLayoutParams(layoutParams);
   */

}
