package com.balazs_csernai.seriescruncher.seriesdetails.model;

import java.util.List;

/**
 * Created by Erik_Markus_Kramli on 2016-01-13.
 */
public interface SeriesDetailsModel {

    String getTitle();

    String getName();

    String getImdbId();

    String getImageUrl();

    List<EpisodeModel> getEpisodes();
}
