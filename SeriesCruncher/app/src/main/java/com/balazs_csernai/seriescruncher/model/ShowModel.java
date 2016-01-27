package com.balazs_csernai.seriescruncher.model;

import java.util.List;

/**
 * Created by Balázs on 2016.01.23..
 */
public interface ShowModel {

    String getName();

    String getTitle();

    String getImdbId();

    String getImageUrl();

    List<EpisodeModel> getEpisodes();
}
