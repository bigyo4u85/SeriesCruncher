package com.balazs_csernai.seriescruncher.utils.navigator;

import android.app.Activity;

import javax.inject.Inject;

/**
 * Created by Balazs_Csernai on 2016.02.08..
 */
public class SeriesDetailsNavigatorImpl implements SeriesDetailsNavigator {

    private final Activity activity;

    @Inject
    public SeriesDetailsNavigatorImpl(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void closeSeriesDetails() {
        activity.finish();
    }
}
