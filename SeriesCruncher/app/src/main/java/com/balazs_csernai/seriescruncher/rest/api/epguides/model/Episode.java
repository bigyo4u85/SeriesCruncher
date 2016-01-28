package com.balazs_csernai.seriescruncher.rest.api.epguides.model;

/**
 * Created by Erik_Markus_Kramli on 2016-01-13.
 */
public interface Episode {

    int getSeasonNumber();

    int getEpisodeNumber();

    String getTitle();

    String getAirDate();

    Series getSeries();
}
