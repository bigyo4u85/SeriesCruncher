package com.balazs_csernai.seriescruncher.utils.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.balazs_csernai.seriescruncher.R;

/**
 * Created by erik_markus_kramli on 2016-02-05.
 */
public class ProgressView extends LinearLayout {

    public ProgressView(Context context) {
        super(context);
        init();
    }

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        if (isInEditMode()) return;

        setGravity(Gravity.CENTER);
        setBackgroundColor(getResources().getColor(R.color.white));

        ProgressBar progressBar = new ProgressBar(getContext());
        progressBar.setIndeterminate(true);
        addView(progressBar);
    }
}
