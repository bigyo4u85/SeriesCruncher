package com.balazs_csernai.seriescruncher.rest.loader;

import android.content.Context;
import android.util.Log;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.exception.NetworkException;
import com.octo.android.robospice.exception.NoNetworkException;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.CachedSpiceRequest;
import com.octo.android.robospice.request.SpiceRequest;
import com.octo.android.robospice.request.listener.RequestListener;

import javax.inject.Inject;

/**
 * Created by ErikKramli on 2016.01.15..
 */
public class RequestLoaderImpl implements RequestLoader {

    private final Context context;
    private final SpiceManager spiceManager;

    @Inject
    public RequestLoaderImpl(Context context, SpiceManager spiceManager) {
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
        spiceManager.shouldStop();
    }

    @Override
    public <MODEL, JSON extends MODEL> void perform(SpiceRequest<JSON> request, final Callback<MODEL> callback) {
        spiceManager.execute(
                request,
                new SimpleRequestListener<MODEL, JSON>(callback)
        );
    }

    @Override
    public <MODEL, JSON extends MODEL> void perform(CachedSpiceRequest<JSON> request, Callback<MODEL> callback) {
        spiceManager.execute(
                request,
                new CacheFallbackRequestListener<>(request, callback)
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
            Log.e("RequestLoaderImpl", spiceException.getMessage());
            if (callback != null) {
                callback.onFailure();
            }
        }
    }

    private final class CacheFallbackRequestListener<MODEL, JSON extends MODEL> extends SimpleRequestListener<MODEL, JSON> {

        private final Class<JSON> resultType;
        private final Object cacheKey;
        private SpiceException networkException;

        private CacheFallbackRequestListener(CachedSpiceRequest<JSON> request, Callback<MODEL> callback) {
            super(callback);
            this.resultType = request.getResultType();
            this.cacheKey = request.getRequestCacheKey();
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
                spiceManager.getFromCache(resultType, cacheKey, DurationInMillis.ALWAYS_RETURNED, this);

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
