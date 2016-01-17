package com.balazs_csernai.seriescruncher.serieslist.ui;

import com.balazs_csernai.seriescruncher.serieslist.model.SeriesModel;

/**
 * Created by ErikKramli on 2016.01.17..
 */
public interface SeriesListScreen {

    void onCrate();

    void displaySeries(SeriesModel model);
}
