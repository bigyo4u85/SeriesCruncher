package com.balazs_csernai.seriescruncher.seriesdetails.ui;

import com.balazs_csernai.seriescruncher.seriesdetails.model.EpisodeListModel;

/**
 * Created by Erik_Markus_Kramli on 2016-01-13.
 */
public interface SeriesDetailsScreen {

    void onCreate();

    void show(String url);

    void show(EpisodeListModel episodes);
}
