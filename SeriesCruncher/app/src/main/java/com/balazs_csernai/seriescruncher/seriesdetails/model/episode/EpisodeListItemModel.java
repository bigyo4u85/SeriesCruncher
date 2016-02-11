package com.balazs_csernai.seriescruncher.seriesdetails.model.episode;

import java.util.Date;

/**
 * Created by Balazs_Csernai on 2016.01.27..
 */
public interface EpisodeListItemModel {

    boolean isEpisode();

    boolean isExpanded();

    void toggle();

    String getEpisodeNumber();

    String getTitle();

    Date getAirDate();
}
