package com.balazs_csernai.seriescruncher.notification.model;

import com.balazs_csernai.seriescruncher.seriesdetails.model.episode.EpisodeModel;

/**
 * Created by ErikKramli on 2016.02.13..
 */
public interface TodaysEpisodeModel {

    String getSeriesTitle();

    String getSeriesName();

    EpisodeModel getEpisode();
}
