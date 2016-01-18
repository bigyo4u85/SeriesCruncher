package com.balazs_csernai.seriescruncher.rest;

import com.balazs_csernai.seriescruncher.details.model.ShowDetailsModel;
import com.balazs_csernai.seriescruncher.rest.loader.Loader;
import com.balazs_csernai.seriescruncher.serieslist.model.SeriesModel;

/**
 * Created by Balazs_Csernai on 2016.01.08..
 */
public interface SeriesLoader extends Loader {

    void loadSeries(Callback<SeriesModel> callback);

    void loadDetails(String showName, String imdbId, Callback<ShowDetailsModel> callback);
}
