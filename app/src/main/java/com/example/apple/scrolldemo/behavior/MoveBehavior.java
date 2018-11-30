package com.example.apple.scrolldemo.behavior;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author crazyZhangxl on 2018/11/28.
 * Describe:
 */
public class MoveBehavior extends CoordinatorLayout.Behavior<View>{
    public MoveBehavior() {
    }

    public MoveBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        return dependency instanceof MoveView;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        int offset = dependency.getTop() - child.getTop();
        child.offsetTopAndBottom(offset);
        return true;
    }
}
