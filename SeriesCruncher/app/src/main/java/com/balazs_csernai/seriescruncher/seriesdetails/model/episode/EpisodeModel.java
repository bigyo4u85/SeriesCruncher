package com.balazs_csernai.seriescruncher.seriesdetails.model.episode;

import java.util.Date;

/**
 * Created by Balazs_Csernai on 2016.01.27..
 */
public interface EpisodeModel {

    String getTitle();

    int getSeasonNumber();

    int getEpisodeNumber();

    Date getAirDate();
}
