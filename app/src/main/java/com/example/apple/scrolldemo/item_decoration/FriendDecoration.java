package com.example.apple.scrolldemo.item_decoration;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.apple.scrolldemo.item_decoration.activities.MyFriendAdapter;
import com.example.apple.scrolldemo.recycle.FriendAdapter;

/**
 * Created by apple on 2019-11-20.
 * description:朋友的分割线
 *
 * 添加头部....
 */
public class FriendDecoration extends RecyclerView.ItemDecoration {
    private int mLineHeight = 2;
    private int mHeadHeight = 50;
    private Paint singleLinePaint;
    private Paint bgPaint;
    private Paint textPaint;

    public FriendDecoration() {
        singleLinePaint = new Paint();
        singleLinePaint.setColor(Color.RED);
        singleLinePaint.setAntiAlias(true);
        singleLinePaint.setStyle(Paint.Style.FILL);

        textPaint = new Paint();
        textPaint.setTextSize(30);
        textPaint.setColor(Color.RED);
        textPaint.setTextAlign(Paint.Align.LEFT);

        bgPaint = new Paint();
        bgPaint.setColor(Color.GREEN);
        bgPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        RecyclerView.Adapter adapter = parent.getAdapter();
        if (adapter instanceof MyFriendAdapter){
            // 如果是朋友类...
            MyFriendAdapter friendAdapter = (MyFriendAdapter)adapter;
            // 根据view获得position
            int position = parent.getChildLayoutPosition(view);
            int _position = parent.getChildAdapterPosition(view);

            // 拿到最后一个位置的下标
            int lastPosition = state.getItemCount() - 1;

            if (position == 0){
                return;
            }
            if (friendAdapter.isGroupHead(position)){
                outRect.top = mHeadHeight;
            }else {
                outRect.top = mLineHeight;
            }
        }
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        c.save();
        int count =  parent.getChildCount();
        MyFriendAdapter friendAdapter = (MyFriendAdapter) (parent.getAdapter());
        if (friendAdapter == null){
            return;
        }
        for (int i=1;i<count;i++){
            View view = parent.getChildAt(i);
            int position = parent.getChildLayoutPosition(view);
            if (friendAdapter.isGroupHead(position)){
                c.drawRect(view.getLeft(),view.getTop()-mHeadHeight,view.getRight(),view.getTop(),bgPaint);
                Log.e("打印", "onDraw: "+ friendAdapter.getFriendBeans().get(position).getNameSpelling());
                c.drawText(friendAdapter.getFriendBeans().get(position).getNameSpelling(),view.getLeft()+50,
                        view.getTop()-mHeadHeight/2+getDistance(textPaint),textPaint);
            }else {
                c.drawRect(view.getLeft(),view.getTop()-mLineHeight,view.getRight(),view.getTop(),singleLinePaint);
            }
        }
        c.restore();
    }

    private int getDistance(Paint paint){
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return (int) (-fontMetrics.bottom/2-fontMetrics.top/2);
    }
}
