package com.balazs_csernai.seriescruncher.notification.model;

import com.balazs_csernai.seriescruncher.seriesdetails.model.episode.EpisodeModel;

/**
 * Created by ErikKramli on 2016.02.13..
 */
public class TodaysEpisodeEntity implements TodaysEpisodeModel {

    private final String title;
    private final String name;
    private final EpisodeModel episode;

    public TodaysEpisodeEntity(String title, String name, EpisodeModel episode) {
        this.title = title;
        this.name = name;
        this.episode = episode;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public EpisodeModel getEpisode() {
        return episode;
    }
}
