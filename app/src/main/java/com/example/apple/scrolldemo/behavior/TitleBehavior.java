package com.example.apple.scrolldemo.behavior;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @author crazyZhangxl on 2018/11/28.
 * Describe:
 */
public class TitleBehavior extends CoordinatorLayout.Behavior<View>{
    private float deltaY;

    public TitleBehavior() {
    }

    /**
     * 该方法是一定得实现的不然,无法在xml中使用
     * @param context
     * @param attrs
     */
    public TitleBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     *
     * @param parent
     * @param child
     * @param dependency  依赖于布局文件中的 RecyclerView
     * @return
     */
    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        return dependency instanceof RecyclerView;
    }

    /**
     * 难点依旧在这里 ————————————
     * 依赖的View改变时调用
     * @param parent
     * @param child
     * @param dependency
     * @return
     *
     * 主要需要记住的是   child 和 dependency的意思; 还有就是滑动位置的关系
     */
    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        if (deltaY == 0){
            // dy = 400 - 120
            deltaY = dependency.getY() - child.getHeight();
            Log.e("RecyclerView = ",dependency.getY()+"");
            Log.e("titleHeight =",child.getHeight()+"");
            Log.e("deltaY 计算  deltaY = ",deltaY+"");
        }
        // 只有RecyclerView在动——————————往上滑的过程中Title呈现
        float dy = dependency.getY() - child.getHeight();
        Log.e("滑动中 dy = ",dy+"");
        dy = dy < 0 ? 0 : dy;
        float y = -(dy / deltaY) * child.getHeight();
        // 初始化状态下向上滑动了整个title的高度, 【滑动都是相对于原始位置的】 所以滑动到0那么就是自己本身的位置了
        child.setTranslationY(y);
        float alpha = 1 - (dy / deltaY);
        child.setAlpha(alpha);
        return true;
    }

}
