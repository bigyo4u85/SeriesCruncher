package com.balazs_csernai.seriescruncher.rest;

import android.app.Activity;

import com.balazs_csernai.seriescruncher.details.model.ShowDetailsEntity;
import com.balazs_csernai.seriescruncher.details.model.ShowDetailsModel;
import com.balazs_csernai.seriescruncher.details.request.ShowDetailsRequest;
import com.balazs_csernai.seriescruncher.rest.api.epguides.EPGuideApi;
import com.balazs_csernai.seriescruncher.rest.api.omdb.OmdbApi;
import com.balazs_csernai.seriescruncher.rest.api.epguides.model.ShowShortList;
import com.balazs_csernai.seriescruncher.rest.request.AllShowRequest;
import com.balazs_csernai.seriescruncher.rest.request.RequestFactory;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import javax.inject.Inject;

/**
 * Created by Balazs_Csernai on 2016.01.08..
 */
public class RestServiceImpl implements RestService {

    private final Activity activity;
    private final SpiceManager spiceManager;
    private final RequestFactory requestFactory;

    @Inject
    public RestServiceImpl(Activity activity, SpiceManager spiceManager, RequestFactory requestFactory) {
        this.activity = activity;
        this.spiceManager = spiceManager;
        this.requestFactory = requestFactory;
    }

    @Override
    public void bind() {
        if (!spiceManager.isStarted()) {
            spiceManager.start(activity);
        }
    }

    @Override
    public void unbind() {
        spiceManager.shouldStop();
    }

    @Override
    public void loadShows(final Callback<ShowShortList> callback) {
        spiceManager.execute(requestFactory.createAllShowRequest(), new RequestListener<ShowShortList>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {
                callback.onFailure();
            }

            @Override
            public void onRequestSuccess(ShowShortList showShorts) {
                callback.onSuccess(showShorts);
            }
        });
    }

    @Override
    public void loadDetails(String showName, String imdbId, final Callback<ShowDetailsModel> callback) {
        spiceManager.execute(requestFactory.createShowDetailsRequest(showName, imdbId), new RequestListener<ShowDetailsEntity>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {
                callback.onFailure();
            }

            @Override
            public void onRequestSuccess(ShowDetailsEntity showDetailsEntity) {
                callback.onSuccess(showDetailsEntity);
            }
        });
    }
}
