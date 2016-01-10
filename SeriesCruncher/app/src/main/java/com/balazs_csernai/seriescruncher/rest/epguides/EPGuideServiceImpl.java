package com.balazs_csernai.seriescruncher.rest.epguides;

import android.app.Activity;

import com.balazs_csernai.seriescruncher.rest.epguides.model.Show;
import com.balazs_csernai.seriescruncher.rest.epguides.model.ShowList;
import com.balazs_csernai.seriescruncher.rest.request.AllShowRequest;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import javax.inject.Inject;

/**
 * Created by Balazs_Csernai on 2016.01.08..
 */
public class EPGuideServiceImpl implements EPGuideService {

    private final Activity activity;
    private final SpiceManager spiceManager;

    @Inject
    public EPGuideServiceImpl(Activity activity, SpiceManager spiceManager) {
        this.activity = activity;
        this.spiceManager = spiceManager;
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
        spiceManager.execute(new AllShowRequest(), new RequestListener<ShowList>() {
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
