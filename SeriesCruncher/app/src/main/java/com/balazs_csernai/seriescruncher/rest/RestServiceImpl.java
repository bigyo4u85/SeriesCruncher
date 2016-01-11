package com.balazs_csernai.seriescruncher.rest;

import android.app.Activity;

import com.balazs_csernai.seriescruncher.rest.api.epguides.EPGuideApi;
import com.balazs_csernai.seriescruncher.rest.api.omdb.OmdbApi;
import com.balazs_csernai.seriescruncher.rest.api.epguides.model.Show;
import com.balazs_csernai.seriescruncher.rest.api.epguides.model.ShowList;
import com.balazs_csernai.seriescruncher.rest.request.AllShowRequest;
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
    private final EPGuideApi epGuideApi;
    private final OmdbApi omdbApi;

    @Inject
    public RestServiceImpl(Activity activity, SpiceManager spiceManager, EPGuideApi epGuideApi, OmdbApi omdbApi) {
        this.activity = activity;
        this.spiceManager = spiceManager;
        this.epGuideApi = epGuideApi;
        this.omdbApi = omdbApi;
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
    public void loadShows(final Callback<ShowList> callback) {
        spiceManager.execute(new AllShowRequest(epGuideApi, omdbApi), new RequestListener<ShowList>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {
                callback.onFailure();
            }

            @Override
            public void onRequestSuccess(ShowList showShorts) {
                callback.onSuccess(showShorts);
            }
        });
    }

    @Override
    public void loadShow(String showName, Callback<Show> callback) {

    }
}
