package com.balazs_csernai.seriescruncher.notification.ui;

import com.balazs_csernai.seriescruncher.notification.model.TodaysEpisodeModel;

import java.util.List;

/**
 * Created by ErikKramli on 2016.02.14..
 */
public interface Notification {

    Notification create(List<TodaysEpisodeModel> todaysEpisodes);

    void show();
}
