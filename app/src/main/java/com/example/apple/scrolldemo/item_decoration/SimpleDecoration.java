package com.example.apple.scrolldemo.item_decoration;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by apple on 2019-11-20.
 * description:建议的Decoration
 *
 * 案例1: 出去第一个 在之后的item添加10px分割线
 */
public class SimpleDecoration extends RecyclerView.ItemDecoration {
    private Paint mLinePaint;

    public SimpleDecoration() {
        mLinePaint = new Paint();
        mLinePaint.setColor(Color.RED);
        mLinePaint.setStyle(Paint.Style.FILL);
        mLinePaint.setAntiAlias(true);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        // 当position != 1 那么在view底部设置布局...
        if (view != parent.getLayoutManager().findViewByPosition(0)){
            outRect.bottom = 10;
        }
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int index =  parent.getChildCount();
        for (int i=1;i<index;i++){
            View view = parent.getChildAt(i);
            c.drawRect(0,view.getBottom(),view.getWidth(),view.getBottom()+5,mLinePaint);
        }
    }
}
