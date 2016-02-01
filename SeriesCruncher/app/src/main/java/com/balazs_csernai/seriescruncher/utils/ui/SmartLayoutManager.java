package com.balazs_csernai.seriescruncher.utils.ui;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by ErikKramli on 2016.01.30..
 */
public class SmartLayoutManager extends LinearLayoutManager {

    private boolean isVerticalScrollEnabled;

    public SmartLayoutManager(Context context) {
        super(context);
        init();
    }

    public SmartLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
        init();
    }

    public SmartLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        isVerticalScrollEnabled = true;
    }

    public void setVerticalScrollEnabled(boolean enabled) {
        isVerticalScrollEnabled = enabled;
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (!isVerticalScrollEnabled) {
            dy = 0;
        }
        return super.scrollVerticallyBy(dy, recycler, state);
    }
}
