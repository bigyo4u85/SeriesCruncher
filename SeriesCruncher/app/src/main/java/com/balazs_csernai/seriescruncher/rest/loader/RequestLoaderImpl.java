package com.balazs_csernai.seriescruncher.rest.loader;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.balazs_csernai.seriescruncher.rest.interactor.Interactor;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.exception.NetworkException;
import com.octo.android.robospice.exception.NoNetworkException;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import javax.inject.Inject;

/**
 * Created by ErikKramli on 2016.01.15..
 */
public class RequestLoaderImpl implements RequestLoader {

    private final Context context;
    private final SpiceManager spiceManager;

    @Inject
    public RequestLoaderImpl(Application context, SpiceManager spiceManager) {
        this.context = context;
        this.spiceManager = spiceManager;
    }

    @Override
    public void bind() {
        if (!spiceManager.isStarted()) {
            spiceManager.start(context);
        }
    }

    @Override
    public void unbind() {
        spiceManager.cancelAllRequests();
        spiceManager.shouldStop();
    }

    @Override
    public <MODEL, JSON extends MODEL> void perform(Interactor<JSON> interactor, Callback<MODEL> callback) {
        spiceManager.execute(
                new InteractorRequest<>(interactor),
                new SimpleRequestListener<MODEL, JSON>(callback)
        );
    }

    @Override
    public <MODEL, JSON extends MODEL> void perform(Interactor<JSON> interactor, String cacheKey, long cacheDuration, final Callback<MODEL> callback) {
        spiceManager.execute(
                new InteractorRequest<>(interactor),
                cacheKey,
                cacheDuration,
                new CacheFallbackRequestListener<>(interactor.getResultType(), cacheKey, callback)
        );
    }

    private class SimpleRequestListener<MODEL, JSON extends MODEL> implements RequestListener<JSON> {

        private final Callback<MODEL> callback;

        private SimpleRequestListener(Callback<MODEL> callback) {
            this.callback = callback;
        }

        @Override
        public void onRequestSuccess(JSON result) {
            if (callback != null) {
                callback.onSuccess(result);
            }
        }

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            Log.e("RequestLoader", spiceException.getMessage());
            if (callback != null) {
                callback.onFailure();
            }
        }
    }

    private final class CacheFallbackRequestListener<MODEL, JSON extends MODEL> extends SimpleRequestListener<MODEL, JSON> {

        private final Class<JSON> resultType;
        private final String cacheKey;
        private SpiceException networkException;

        private CacheFallbackRequestListener(Class<JSON> resultType, String cacheKey, Callback<MODEL> callback) {
            super(callback);
            this.resultType = resultType;
            this.cacheKey = cacheKey;
        }

        @Override
        public void onRequestSuccess(JSON result) {
            if (result == null) {
                super.onRequestFailure(networkException);
            } else {
                super.onRequestSuccess(result);
            }
        }

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            if (isNetworkException(spiceException)) {
                networkException = spiceException;
                spiceManager.getFromCache(
                        resultType,
                        cacheKey,
                        DurationInMillis.ALWAYS_RETURNED,
                        this
                );
            } else {
                super.onRequestFailure(spiceException);
            }
        }

        private boolean isNetworkException(SpiceException spiceException) {
            return spiceException instanceof NetworkException
                    || spiceException instanceof NoNetworkException;
        }
    }
}
