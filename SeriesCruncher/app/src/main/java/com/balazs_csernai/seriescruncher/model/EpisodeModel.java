package com.balazs_csernai.seriescruncher.model;

/**
 * Created by Balazs_Csernai on 2016.01.27..
 */
public interface EpisodeModel {

    int getSeasonNumber();

    int getEpisodeNumber();

    String getTitle();

    String getAirDate();
}
