package com.balazs_csernai.seriescruncher.seriesdetails.ui;

import com.balazs_csernai.seriescruncher.seriesdetails.model.episode.EpisodeListModel;
import com.balazs_csernai.seriescruncher.seriesdetails.model.poster.PosterModel;

/**
 * Created by Erik_Markus_Kramli on 2016-01-13.
 */
public interface SeriesDetailsScreen {

    void onCreate();

    void show(String title);

    void show(EpisodeListModel episodes);

    void show(PosterModel posterModel);
}
