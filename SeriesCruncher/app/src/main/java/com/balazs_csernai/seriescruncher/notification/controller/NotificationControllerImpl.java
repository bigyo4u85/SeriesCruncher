package com.balazs_csernai.seriescruncher.notification.controller;

import com.balazs_csernai.seriescruncher.preferences.Preferences;
import com.balazs_csernai.seriescruncher.rest.SeriesLoader;
import com.balazs_csernai.seriescruncher.rest.SeriesLoader.LoadType;
import com.balazs_csernai.seriescruncher.rest.loader.Loader;
import com.balazs_csernai.seriescruncher.seriesdetails.model.SeriesDetailsModel;
import com.balazs_csernai.seriescruncher.seriesdetails.model.episode.EpisodeModel;
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
    private final List<EpisodeModel> todaysEpisodes;

    private CountDownLatch latch;
    private Callback callback;

    @Inject
    public NotificationControllerImpl(Preferences preferences, SeriesLoader loader, EpisodeFinder episodeFinder) {
        this.preferences = preferences;
        this.loader = loader;
        this.episodeFinder = episodeFinder;
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
            loader.loadDetails(favoredSeries, LoadType.CACHE_ONLY, this);
        }
    }

    @Override
    public synchronized void onSuccess(SeriesDetailsModel result) {
        episodeFinder.setEpisodes(result.getEpisodes());
        if (episodeFinder.hasNewEpisodeToday()) {
            todaysEpisodes.add(episodeFinder.getNextEpisode());
        }
        tryToShowNotifications();
    }

    @Override
    public synchronized void onFailure() {
        tryToShowNotifications();
    }

    private void tryToShowNotifications() {
        latch.countDown();
        if (latch.getCount() == 0) {



            loader.unbind();
            callback.onFinish();
        }
    }
}
