package com.balazs_csernai.seriescruncher.rest;

import com.balazs_csernai.seriescruncher.details.model.SeriesDetailsModel;
import com.balazs_csernai.seriescruncher.rest.loader.Loader;
import com.balazs_csernai.seriescruncher.serieslist.model.SeriesListModel;

/**
 * Created by Balazs_Csernai on 2016.01.08..
 */
public interface SeriesLoader extends Loader {

    void loadSeries(Callback<SeriesListModel> callback);

    void loadDetails(String showName, String imdbId, Callback<SeriesDetailsModel> callback);
}
