package com.balazs_csernai.seriescruncher.utils.navigator;

import android.app.Activity;

import com.balazs_csernai.seriescruncher.details.ShowDetailsActivity;

import javax.inject.Inject;

/**
 * Created by ErikKramli on 2016.01.17..
 */
public class ActivityNavigator implements Navigator {

    private final Activity activity;

    @Inject
    public ActivityNavigator(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void launchShowDetails(String showName, String imdbId) {
        activity.startActivity(ShowDetailsActivity.createLaunchIntent(activity, showName, imdbId));
    }
}
