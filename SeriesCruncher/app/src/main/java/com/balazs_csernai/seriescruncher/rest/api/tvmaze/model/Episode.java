package com.balazs_csernai.seriescruncher.rest.api.tvmaze.model;

/**
 * Created by Balázs on 2016.01.23..
 */
public interface Episode {

    int getId();

    String getUrl();

    String getName();

    int getSeasonNumber();

    int getEpisodeNumber();

    String getAirDate();

    String getAirTime();

    int getRuntime();

    ImageUrls getImageUrls();

    String getSummary();

    Refs getRefs();
}
