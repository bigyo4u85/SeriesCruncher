package com.balazs_csernai.seriescruncher.rest;

import com.balazs_csernai.seriescruncher.seriesdetails.model.SeriesDetailsModel;
import com.balazs_csernai.seriescruncher.seriesdetails.request.SeriesDetailsRequest;
import com.balazs_csernai.seriescruncher.rest.loader.RequestLoader;
import com.balazs_csernai.seriescruncher.rest.request.RequestFactory;
import com.balazs_csernai.seriescruncher.serieslist.model.SeriesListModel;
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
        loader.perform(
                requestFactory.createSeriesRequest(),
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

}
