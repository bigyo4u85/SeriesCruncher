package com.balazs_csernai.seriescruncher.rest;

import com.balazs_csernai.seriescruncher.rest.interactor.CacheType;
import com.balazs_csernai.seriescruncher.seriesdetails.model.poster.PosterModel;
import com.balazs_csernai.seriescruncher.seriesdetails.model.SeriesDetailsModel;
import com.balazs_csernai.seriescruncher.rest.loader.Loader;
import com.balazs_csernai.seriescruncher.serieslist.model.SeriesListModel;
import java.util.Collection;

/**
 * Created by Balazs_Csernai on 2016.01.08..
 */
public interface SeriesLoader extends Loader {

    void loadSeries(@CacheType long type, Callback<SeriesListModel> callback);

    void loadDetails(String seriesName, @CacheType long type, Callback<SeriesDetailsModel> callback);

    void loadDetails(Collection<String> seriesNames, @CacheType long type, final Callback<SeriesDetailsModel> callback);

    void loadPoster(String posterUrl, Callback<PosterModel> callback);
}
