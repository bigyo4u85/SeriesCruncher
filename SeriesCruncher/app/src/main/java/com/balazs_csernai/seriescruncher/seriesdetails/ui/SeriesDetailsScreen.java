package com.balazs_csernai.seriescruncher.seriesdetails.ui;

import com.balazs_csernai.seriescruncher.seriesdetails.model.EpisodeListModel;
import com.balazs_csernai.seriescruncher.image.ImageLoader.ImageTarget;
import com.balazs_csernai.seriescruncher.seriesdetails.model.PosterModel;

/**
 * Created by Erik_Markus_Kramli on 2016-01-13.
 */
public interface SeriesDetailsScreen {

    void onCreate();

    void show(String url);

    void show(EpisodeListModel episodes);

    ImageTarget getPosterImageTarget();

    void show(PosterModel posterModel);
}
