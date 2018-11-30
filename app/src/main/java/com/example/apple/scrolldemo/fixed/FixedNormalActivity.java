package com.example.apple.scrolldemo.fixed;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.apple.scrolldemo.R;
import com.example.apple.scrolldemo.fixed.normal.BottomUpdateMessage;
import com.example.apple.scrolldemo.fixed.normal.MainFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @author crazyZhangxl on 2018-11-27 9:31:25.
 * Describe: 实现一个通用的滑动相关的界面UI
 *
 * 包括一系列behavior的学习和使用 ————————————
 */

public class FixedNormalActivity extends AppCompatActivity {
    private FrameLayout mFrameBody;
    private LinearLayout mLlBottomBar;
    private static int tabLayoutHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixed_normal);
        // 初始化View -----
        initViews();
        // 很牛叉的测量控件的高度/宽度 的方式
        mLlBottomBar.measure(0,0);
        // 进行注册
        EventBus.getDefault().register(this);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fmBody,new MainFragment());
        fragmentTransaction.commit();
        ViewTreeObserver treeObserver = mLlBottomBar.getViewTreeObserver();

        // 测量布局的高度 ———————————————— 我凑 —————————————————————— 别偷懒了
        treeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                tabLayoutHeight = mLlBottomBar.getMeasuredHeight();
                Log.e("打印","=========="+mLlBottomBar.getHeight()+" ===========");
                mLlBottomBar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    private void initViews() {
        mFrameBody = findViewById(R.id.fmBody);
        mLlBottomBar = findViewById(R.id.llBottomBar);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 取消注册 ————————
        EventBus.getDefault().unregister(this);
    }

    /**
     * 菜单显示隐藏动画
     * @param message 传递的参数 ————————
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void startAnimation(BottomUpdateMessage message){
        boolean showOrHide = message.isShow();
        final ViewGroup.LayoutParams layoutParams = mLlBottomBar.getLayoutParams();
        ValueAnimator valueAnimator;
        ObjectAnimator alpha;
        if(!showOrHide){
            valueAnimator = ValueAnimator.ofInt(tabLayoutHeight, 0);
            alpha = ObjectAnimator.ofFloat(mLlBottomBar, "alpha", 1, 0);
        }else{
            valueAnimator = ValueAnimator.ofInt(0, tabLayoutHeight);
            alpha = ObjectAnimator.ofFloat(mLlBottomBar, "alpha", 0, 1);
        }
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                layoutParams.height= (int) valueAnimator.getAnimatedValue();
                mLlBottomBar.setLayoutParams(layoutParams);
            }
        });
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.setDuration(500);
        animatorSet.playTogether(valueAnimator,alpha);
        animatorSet.start();
    }
}
