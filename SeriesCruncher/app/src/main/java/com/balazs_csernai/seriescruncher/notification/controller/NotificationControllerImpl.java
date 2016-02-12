package com.balazs_csernai.seriescruncher.notification.controller;

import android.util.Log;

import com.balazs_csernai.seriescruncher.preferences.Preferences;
import com.balazs_csernai.seriescruncher.preferences.user.UserPreferencesModel;
import com.balazs_csernai.seriescruncher.rest.SeriesLoader;
import com.balazs_csernai.seriescruncher.rest.SeriesLoader.LoadType;
import com.balazs_csernai.seriescruncher.rest.loader.Loader;
import com.balazs_csernai.seriescruncher.seriesdetails.model.SeriesDetailsModel;
import com.balazs_csernai.seriescruncher.seriesdetails.model.finder.EpisodeFinder;

import java.util.Set;
import java.util.concurrent.CountDownLatch;

import javax.inject.Inject;

/**
 * Created by Erik_Markus_Kramli on 2016-02-12.
 */
public class NotificationControllerImpl implements NotificationController, Loader.Callback<SeriesDetailsModel> {

    private final Preferences preferences;
    private final SeriesLoader loader;
    private final EpisodeFinder episodeFinder;

    private CountDownLatch latch;

    @Inject
    public NotificationControllerImpl(Preferences preferences, SeriesLoader loader, EpisodeFinder episodeFinder) {
        this.preferences = preferences;
        this.loader = loader;
        this.episodeFinder = episodeFinder;
    }

    @Override
    public void showNewEpisodeNotifications() {
        Set<String> favoredSeries = preferences.getUserPreferences().getFavoredSeries();
        if (!favoredSeries.isEmpty()) {
            latch = new CountDownLatch(favoredSeries.size());
            loader.loadDetails(favoredSeries, LoadType.CACHE_ONLY, this);
        }
    }

    @Override
    public synchronized void onSuccess(SeriesDetailsModel result) {




        tryToShowNotifications();
    }

    @Override
    public synchronized void onFailure() {
        tryToShowNotifications();
    }

    private void tryToShowNotifications() {
        latch.countDown();
        if (latch.getCount() == 0) {

            Log.d("notificatin", "show notifications for series: ");

        }
    }
}
