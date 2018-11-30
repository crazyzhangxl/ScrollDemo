package com.example.apple.scrolldemo.behavior;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Behavior(CoordinatorLayout.Behavior/FloatingActionBar.Behavior)
 * 桥梁 监听者,包裹在里面的所有子控件或者容器会产生联动的效果
 *
 * Behavior使用情况包括两者:
 * 1. 某个View需要监听另外一个View的状态(比如:位置,大小,显示状态) 需要重写layoutDependsOn/onDependentViewChanged
 * 2. 某个View需要监听CoordinatorLayout里面所有控件的滑动状态。  需要重写方法: onStartNestedScroll,onNestedScroll 或者 onNestedPreScroll
 *
 * 注意：能被CoordinatorLayout捕获的View有RecyclerView,NestedScrollView,ViewPager
 */
public class CustomBehavior extends CoordinatorLayout.Behavior<View>{

    public CustomBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 用来决定需要监听哪些控件或者容器的状态（1.知道监听谁 2.什么状态改变）
     * @param parent 父容器
     * @param child  子控件 需要监听dependency这个view的视图们 ——————观察者
     * @param dependency 你要监听的那个view
     * @return true依赖  否则不依赖
     */
    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        // 还可以根据ID 或者TAG来改变
        return dependency instanceof TextView || super.layoutDependsOn(parent, child, dependency);

    }

    /**
     * 当被监听的view发生改变的时候回调,做一些响应的联动动画效果
     * 可以在此方法里面做一些响应的联动动画等效果
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        // 获取被监听的view的状态 ———————— 垂直方向位置
        // 明确getTop获得的是 View顶部相对于父容器的高度
        int offset = dependency.getTop() - child.getTop();
        // 设置偏移 ——————————
        ViewCompat.offsetTopAndBottom(child,offset);
        child.animate().rotation(child.getTop()*20);
        return true;
    }
}
