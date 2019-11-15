package com.example.apple.scrolldemo.base;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.apple.scrolldemo.R;

/**
 * @author crazyZhangxl on 2019-1-24 13:40:44.
 * Describe:
 */

public class MoveActivity extends AppCompatActivity {
    private View viewChild;
    private Button mBtnLeft,mBtnTop,mBtnRight,mBtnBottom,btnAnimator,btnScrollOne,btnScrollTwo,translationYOne,translationYTwo;
    private TextView mTvResult;
    private FrameLayout llParent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move);
        initViews();
        initObserver();
        initListener();
    }

    private void initListener() {
        mBtnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewChild.offsetLeftAndRight(-10);
                setResult();
            }
        });

        mBtnTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewChild.offsetTopAndBottom(-10);
                setResult();
            }
        });

        mBtnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewChild.offsetLeftAndRight(10);
                setResult();
            }
        });

        mBtnBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewChild.offsetTopAndBottom(10);
                setResult();
            }
        });

        btnAnimator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doAnimate();
            }
        });

        btnScrollOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llParent.scrollBy(50,50);
                setResult();
            }
        });

        btnScrollTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llParent.scrollBy(-50,-50);
                setResult();
            }
        });

        translationYTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewChild.setTranslationY(-50);
                setResult();
            }
        });

        translationYOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewChild.setTranslationY(50);
                setResult();
            }
        });
    }

    private void doAnimate() {
        ObjectAnimator translationX = ObjectAnimator.ofFloat(viewChild, "translationX", 0,100);
        translationX.setDuration(200);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(viewChild, "translationY", 0,100);
        translationY.setDuration(200);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(translationX).with(translationY);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                setResult();
            }
        });
        animatorSet.start();

    }

    private void initObserver() {
        ViewTreeObserver viewTreeObserver = viewChild.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // 用完即移除监听即可
                setResult();
               viewChild.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    private void  setResult(){
        mTvResult.setText("getLeft = "+viewChild.getLeft()+",getTop = "+viewChild.getTop()+"\n"+
                "getX() = "+viewChild.getX()+",getY() = "+viewChild.getY()+"\n"+
                "getTranslationX() = "+viewChild.getTranslationX()+",getTranslationY() = "+viewChild.getTranslationY()+"\n"+
                "parent.getScrollX() = "+llParent.getScrollX()+",parent.getScrollY() = "+llParent.getScrollY());
    }

    private void initViews() {
        viewChild = findViewById(R.id.viewChild);
        mBtnLeft = findViewById(R.id.btnLeft);
        mBtnTop = findViewById(R.id.btnTop);
        mBtnRight = findViewById(R.id.btnRight);
        mBtnBottom = findViewById(R.id.btnBottom);
        mTvResult = findViewById(R.id.tvResult);
        btnAnimator = findViewById(R.id.btnAnimator);
        llParent = findViewById(R.id.llParent);
        btnScrollOne = findViewById(R.id.btnScrollOne);
        btnScrollTwo = findViewById(R.id.btnScrollTwo);
        translationYOne = findViewById(R.id.translationYOne);
        translationYTwo =findViewById(R.id.translationYTwo);
    }

}
