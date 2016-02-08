package com.balazs_csernai.seriescruncher.utils.ui;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.AppBarLayout.OnOffsetChangedListener;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;

import com.balazs_csernai.seriescruncher.utils.ui.materialfix.FlingBehavior;

/**
 * Created by ErikKramli on 2016.01.31..
 */
@CoordinatorLayout.DefaultBehavior(FlingBehavior.class)
public class SmartAppBarLayout extends AppBarLayout implements OnOffsetChangedListener {

    public interface AppBarChangeListener {
        void onAppBarCollapsed();
        void onAppBarExpanded();
    }

    private static final float INVALID = -1F;
    private static final float EXPANDED = 0F;
    private static final float COLLAPSED = 1F;
    private float previousRatio;

    private AppBarChangeListener listener;

    public SmartAppBarLayout(Context context) {
        super(context);
    }

    public SmartAppBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setOnAppBarChangeListener(AppBarChangeListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        previousRatio = INVALID;
        addOnOffsetChangedListener(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        removeOnOffsetChangedListener(this);
        previousRatio = INVALID;
        super.onDetachedFromWindow();
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        float ratio = Math.abs(verticalOffset) / (float) appBarLayout.getTotalScrollRange();
        if (ratio == EXPANDED && ratio != previousRatio && listener != null) {
            listener.onAppBarExpanded();

        } else if (ratio == COLLAPSED && ratio != previousRatio && listener != null) {
            listener.onAppBarCollapsed();
        }
        previousRatio = ratio;
    }
}
