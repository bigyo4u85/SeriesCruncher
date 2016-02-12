package com.balazs_csernai.seriescruncher.rest;

import android.support.annotation.IntDef;

import com.balazs_csernai.seriescruncher.seriesdetails.model.poster.PosterModel;
import com.balazs_csernai.seriescruncher.seriesdetails.model.SeriesDetailsModel;
import com.balazs_csernai.seriescruncher.rest.loader.Loader;
import com.balazs_csernai.seriescruncher.serieslist.model.SeriesListModel;
import com.octo.android.robospice.persistence.DurationInMillis;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collection;

/**
 * Created by Balazs_Csernai on 2016.01.08..
 */
public interface SeriesLoader extends Loader {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({LoadType.DEFAULT, LoadType.FORCE_LOAD, LoadType.CACHE_ONLY})
    @interface LoadType {
        long DEFAULT = DurationInMillis.ONE_DAY;
        long FORCE_LOAD = DurationInMillis.ALWAYS_EXPIRED;
        long CACHE_ONLY = DurationInMillis.ALWAYS_RETURNED;
    }

    void loadSeries(@LoadType long type, Callback<SeriesListModel> callback);

    void loadDetails(String seriesName, @LoadType long type, Callback<SeriesDetailsModel> callback);

    void loadDetails(Collection<String> seriesNames, @LoadType long type, final Callback<SeriesDetailsModel> callback);

    void loadPoster(String posterUrl, Callback<PosterModel> callback);
}
