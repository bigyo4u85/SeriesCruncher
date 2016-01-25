package com.balazs_csernai.seriescruncher.rest.api.epguides.loader;

import com.balazs_csernai.seriescruncher.details.model.ShowModel;
import com.balazs_csernai.seriescruncher.rest.api.epguides.request.ShowRequest;
import com.balazs_csernai.seriescruncher.rest.loader.RequestLoader;
import com.balazs_csernai.seriescruncher.rest.api.epguides.request.RequestFactory;
import com.balazs_csernai.seriescruncher.model.ShowsModel;
import com.octo.android.robospice.persistence.DurationInMillis;

import javax.inject.Inject;

/**
 * Created by Balazs_Csernai on 2016.01.08..
 */
public class DataLoaderImpl implements DataLoader {

    private final RequestLoader loader;
    private final RequestFactory requestFactory;

    @Inject
    public DataLoaderImpl(RequestLoader loader, RequestFactory requestFactory) {
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
    public void loadShows(Callback<ShowsModel> callback) {
        loader.perform(
                requestFactory.createShowsRequest(),
                callback
        );
    }

    @Override
    public void loadShow(String showName, String imdbId, final Callback<ShowModel> callback) {
        ShowRequest request = requestFactory.createShowRequest(showName, imdbId);
        loader.perform(
                requestFactory.createCachedRequest(request, showName, DurationInMillis.ONE_HOUR),
                callback
        );
    }

}
