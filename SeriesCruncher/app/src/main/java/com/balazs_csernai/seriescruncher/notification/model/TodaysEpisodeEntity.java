package com.balazs_csernai.seriescruncher.notification.model;

import com.balazs_csernai.seriescruncher.seriesdetails.model.episode.EpisodeModel;

/**
 * Created by ErikKramli on 2016.02.13..
 */
public class TodaysEpisodeEntity implements TodaysEpisodeModel {

    private final String seriesTitle;
    private final String seriesName;
    private final EpisodeModel episode;

    public TodaysEpisodeEntity(String seriesTitle, String seriesName, EpisodeModel episode) {
        this.seriesTitle = seriesTitle;
        this.seriesName = seriesName;
        this.episode = episode;
    }

    @Override
    public String getSeriesTitle() {
        return seriesTitle;
    }

    @Override
    public String getSeriesName() {
        return seriesName;
    }

    @Override
    public EpisodeModel getEpisode() {
        return episode;
    }
}
