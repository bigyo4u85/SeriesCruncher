package com.balazs_csernai.seriescruncher.utils.navigator;

import android.app.Activity;
import android.content.Intent;

import com.balazs_csernai.seriescruncher.seriesdetails.SeriesDetailsActivity;
import com.balazs_csernai.seriescruncher.settings.SettingsActivity;

import javax.inject.Inject;

/**
 * Created by ErikKramli on 2016.01.17..
 */
public class SeriesListNavigatorImpl implements SeriesListNavigator {

    private final Activity activity;

    @Inject
    public SeriesListNavigatorImpl(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void launchSeriesDetails(String seriesName) {
        activity.startActivity(SeriesDetailsActivity.createLaunchIntent(activity, seriesName));
    }

    @Override
    public void launchSettings() {
        activity.startActivity(new Intent(activity, SettingsActivity.class));
    }

    @Override
    public void closeSeriesList() {
        activity.finish();
    }
}
