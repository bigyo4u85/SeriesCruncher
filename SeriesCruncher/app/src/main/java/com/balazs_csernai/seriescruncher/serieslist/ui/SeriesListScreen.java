package com.balazs_csernai.seriescruncher.serieslist.ui;

import com.balazs_csernai.seriescruncher.rest.api.epguides.model.Show;
import com.balazs_csernai.seriescruncher.serieslist.model.SeriesModel;

/**
 * Created by ErikKramli on 2016.01.17..
 */
public interface SeriesListScreen {

    void onCrate(Callbacks callbacks);

    void displaySeries(SeriesModel model);

    interface Callbacks {

        void onShowTapped(Show show);
    }
}
