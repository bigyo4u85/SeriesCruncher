package com.balazs_csernai.seriescruncher.rest;

import com.balazs_csernai.seriescruncher.details.model.ShowDetailsModel;
import com.balazs_csernai.seriescruncher.details.request.ShowDetailsRequest;
import com.balazs_csernai.seriescruncher.rest.loader.RequestLoader;
import com.balazs_csernai.seriescruncher.rest.request.RequestFactory;
import com.balazs_csernai.seriescruncher.serieslist.model.SeriesModel;
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
    public void loadSeries(Callback<SeriesModel> callback) {
        loader.perform(
                requestFactory.createSeriesRequest(),
                callback
        );
    }

    @Override
    public void loadDetails(String showName, String imdbId, final Callback<ShowDetailsModel> callback) {
        ShowDetailsRequest request = requestFactory.createShowDetailsRequest(showName, imdbId);
        loader.perform(
                requestFactory.createCachedRequest(request, showName, DurationInMillis.ONE_HOUR),
                callback
        );
    }

}
