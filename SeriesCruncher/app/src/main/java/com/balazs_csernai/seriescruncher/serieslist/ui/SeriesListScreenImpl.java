package com.balazs_csernai.seriescruncher.serieslist.ui;

import android.app.Activity;

import javax.inject.Inject;

/**
 * Created by ErikKramli on 2016.01.17..
 */
public class SeriesListScreenImpl implements SeriesListScreen {

    private final Activity activity;

    @Inject
    public SeriesListScreenImpl(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onCrate() {

    }
}
