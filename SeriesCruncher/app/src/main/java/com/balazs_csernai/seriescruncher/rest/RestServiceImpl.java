package com.balazs_csernai.seriescruncher.rest;

import android.app.Activity;

import com.balazs_csernai.seriescruncher.details.model.ShowDetailsModel;
import com.balazs_csernai.seriescruncher.details.request.ShowDetailsRequest;
import com.balazs_csernai.seriescruncher.rest.api.epguides.model.ShowShortList;
import com.balazs_csernai.seriescruncher.rest.request.RequestFactory;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.exception.NetworkException;
import com.octo.android.robospice.exception.NoNetworkException;
import com.octo.android.robospice.exception.RequestCancelledException;
import com.octo.android.robospice.persistence.DurationInMillis;
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
        ShowDetailsRequest request = requestFactory.createShowDetailsRequest(showName, imdbId);
        spiceManager.execute(request, showName, DurationInMillis.ALWAYS_EXPIRED, new CacheFallbackRequestListener<>(request.getResultType(), showName, callback));
    }

    final class CacheFallbackRequestListener<MODEL, JSON extends MODEL> implements RequestListener<JSON> {

        private final Class<JSON> resultType;
        private final String cacheKey;
        private final Callback<MODEL> callback;

        CacheFallbackRequestListener(Class<JSON> resultType, String cacheKey, Callback<MODEL> callback) {
            this.resultType = resultType;
            this.cacheKey = cacheKey;
            this.callback = callback;
        }

        @Override
        public void onRequestSuccess(JSON json) {
            if (json == null) {
                callback.onFailure();
            } else {
                callback.onSuccess(json);
            }
        }

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            if (isNetworkException(spiceException)) {
                spiceManager.getFromCache(resultType, cacheKey, DurationInMillis.ALWAYS_RETURNED, this);

            } else {
                callback.onFailure();
            }
        }

        private boolean isNetworkException(SpiceException spiceException) {
            return spiceException instanceof NetworkException
                    || spiceException instanceof NoNetworkException
                    || spiceException instanceof RequestCancelledException;
        }
    }
}
