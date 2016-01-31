package com.balazs_csernai.seriescruncher.rest;

import com.balazs_csernai.seriescruncher.rest.loader.RequestLoader;
import com.balazs_csernai.seriescruncher.rest.request.RequestFactory;
import com.balazs_csernai.seriescruncher.seriesdetails.model.PosterModel;
import com.balazs_csernai.seriescruncher.seriesdetails.model.SeriesDetailsModel;
import com.balazs_csernai.seriescruncher.seriesdetails.request.SeriesDetailsRequest;
import com.balazs_csernai.seriescruncher.serieslist.model.SeriesListModel;
import com.balazs_csernai.seriescruncher.serieslist.request.SeriesRequest;
import com.octo.android.robospice.persistence.DurationInMillis;

import javax.inject.Inject;

/**
 * Created by Balazs_Csernai on 2016.01.08..
 */
public class SeriesLoaderImpl implements SeriesLoader {

    private final RequestLoader loader;
    private final RequestFactory requestFactory;

    @Inject
    public SeriesLoaderImpl(RequestLoader loader, RequestFactory requestFactory) {
        this.loader = loader;
        this.requestFactory = requestFactory;
    }

    @Override
    public void bind() {
        loader.bind();
    }

    @Override
    public void unbind() {
        loader.unbind();
    }

    @Override
    public void loadSeries(Callback<SeriesListModel> callback) {
        SeriesRequest request = requestFactory.createSeriesRequest();
        loader.perform(
                requestFactory.createCachedRequest(request, "series", DurationInMillis.ONE_HOUR),
                callback
        );
    }

    @Override
    public void loadDetails(String showName, String imdbId, final Callback<SeriesDetailsModel> callback) {
        SeriesDetailsRequest request = requestFactory.createSeriesDetailsRequest(showName, imdbId);
        loader.perform(
                requestFactory.createCachedRequest(request, showName, DurationInMillis.ONE_HOUR),
                callback
        );
    }

    @Override
    public void loadPoster(String posterUrl, Callback<PosterModel> callback) {
        loader.perform(
                requestFactory.createPosterRequest(posterUrl),
                callback
        );
    }
}
