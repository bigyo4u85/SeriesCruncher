package com.balazs_csernai.seriescruncher.notification.controller;

import com.balazs_csernai.seriescruncher.notification.model.TodaysEpisodeEntity;
import com.balazs_csernai.seriescruncher.notification.model.TodaysEpisodeModel;
import com.balazs_csernai.seriescruncher.notification.ui.Notification;
import com.balazs_csernai.seriescruncher.preferences.Preferences;
import com.balazs_csernai.seriescruncher.rest.SeriesLoader;
import com.balazs_csernai.seriescruncher.rest.interactor.CacheType;
import com.balazs_csernai.seriescruncher.rest.loader.Loader;
import com.balazs_csernai.seriescruncher.seriesdetails.model.SeriesDetailsModel;
import com.balazs_csernai.seriescruncher.seriesdetails.model.finder.EpisodeFinder;

import java.util.ArrayList;
import java.util.List;
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
    private final Notification notification;
    private final List<TodaysEpisodeModel> todaysEpisodes;

    private CountDownLatch latch;
    private Callback callback;

    @Inject
    public NotificationControllerImpl(Preferences preferences, SeriesLoader loader, EpisodeFinder episodeFinder, Notification notification) {
        this.preferences = preferences;
        this.loader = loader;
        this.episodeFinder = episodeFinder;
        this.notification = notification;
        todaysEpisodes = new ArrayList<>();
    }

    @Override
    public void showNewEpisodeNotifications(Callback callback) {
        this.callback = callback;

        Set<String> favoredSeries = preferences.getUserPreferences().getFavoredSeries();
        if (!favoredSeries.isEmpty()) {
            todaysEpisodes.clear();
            latch = new CountDownLatch(favoredSeries.size());
            loader.bind();
            loader.loadDetails(favoredSeries, CacheType.CACHE_ONLY, this);
        }
    }

    @Override
    public synchronized void onSuccess(SeriesDetailsModel result) {
        episodeFinder.setEpisodes(result.getEpisodes());
        if (episodeFinder.hasNewEpisodeToday()) {
            todaysEpisodes.add(new TodaysEpisodeEntity(result.getTitle(), result.getName(), episodeFinder.getNextEpisode()));
        }
        tryToFinish();
    }

    @Override
    public synchronized void onFailure() {
        tryToFinish();
    }

    private void tryToFinish() {
        latch.countDown();
        if (latch.getCount() == 0) {
            finish();
        }
    }

    private void finish() {
        if (!todaysEpisodes.isEmpty()) {
            notification.create(todaysEpisodes).show();
        }
        loader.unbind();
        callback.onFinish();
    }
}
