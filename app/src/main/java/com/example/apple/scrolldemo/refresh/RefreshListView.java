package com.example.apple.scrolldemo.refresh;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by apple on 2019-11-16.
 * description:
 */
public class RefreshListView  extends ListView {
    private RefreshHead mRefreshHead;
    private float lastY = -1;

    public RefreshListView(Context context) {
        this(context,null);
    }

    public RefreshListView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mRefreshHead = new RefreshHead(getContext());
        addHeaderView(mRefreshHead);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastY = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                // 位移的增量...
                float dy = ev.getRawY() - lastY;
                lastY = ev.getRawY();
                Log.e("滑动", "onTouchEvent: "+dy );
                mRefreshHead.onMove(dy/2);
                if (mRefreshHead.getVisibleHeight() > 0 && mRefreshHead.getRefreshState().value < RefreshState.STATE_THREE.value){
                    return false;
                }
                break;
                default:
                    lastY = -1;
                    if (mRefreshHead.releaseAction()){
                        likeRefresh();
                    }
                    break;
        }
        return super.onTouchEvent(ev);

    }

    /**
     * 模拟刷新
     */
    private void likeRefresh() {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                mRefreshHead.finishRefresh();
            }
        },3000);
    }
}
