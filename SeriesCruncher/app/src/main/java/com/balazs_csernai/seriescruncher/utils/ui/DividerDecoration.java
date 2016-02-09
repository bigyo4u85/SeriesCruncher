package com.balazs_csernai.seriescruncher.utils.ui;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.balazs_csernai.seriescruncher.R;

/**
 * Created by ErikKramli on 2016.01.17..
 */
public class DividerDecoration extends RecyclerView.ItemDecoration {

    private final Paint paint;

    public DividerDecoration() {
        this(Color.BLACK);
    }

    public DividerDecoration(@ColorInt int color) {
        paint = new Paint();
        paint.setColor(color);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        final int height = getHeight(parent.getResources());
        View child;
        for (int i = 0, count = parent.getChildCount(); i < count; i++) {
            child = parent.getChildAt(i);

            int top = child.getBottom();
            int bottom = top + height;
            int left = child.getLeft();
            int right = child.getRight();

            c.save();
            c.drawRect(left, top, right, bottom, paint);
            c.restore();
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(0, 0, 0, getHeight(parent.getResources()));
    }

    private int getHeight(Resources resources) {
        return resources.getDimensionPixelSize(R.dimen.divider_height);
    }
}
