package com.balazs_csernai.seriescruncher.details.ui;

import android.app.Activity;
import android.widget.TextView;

import com.balazs_csernai.seriescruncher.R;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Erik_Markus_Kramli on 2016-01-13.
 */
public class SeriesDetailsScreenImpl implements SeriesDetailsScreen {

    @InjectView(R.id.title)
    TextView title;

    private final Activity activity;

    @Inject
    public SeriesDetailsScreenImpl(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onCreate() {
        ButterKnife.inject(this, activity);
    }

    @Override
    public void show(String url) {
        title.setText(url);
    }
}
