package com.balazs_csernai.seriescruncher.rest.request;

import com.balazs_csernai.seriescruncher.seriesdetails.request.PosterRequest;
import com.balazs_csernai.seriescruncher.seriesdetails.request.SeriesDetailsRequest;
import com.balazs_csernai.seriescruncher.serieslist.request.SeriesRequest;
import com.octo.android.robospice.request.CachedSpiceRequest;
import com.octo.android.robospice.request.SpiceRequest;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Created by ErikKramli on 2016.01.14..
 */
public class RequestFactoryImpl implements RequestFactory {

    private final Provider<SeriesRequest> seriesRequestProvider;
    private final Provider<SeriesDetailsRequest> seriesDetailsRequestProvider;
    private final Provider<PosterRequest> posterRequestProvider;

    @Inject
    public RequestFactoryImpl(Provider<SeriesRequest> seriesRequestProvider,
                              Provider<SeriesDetailsRequest> seriesDetailsRequestProvider,
                              Provider<PosterRequest> posterRequestProvider) {
        this.seriesRequestProvider = seriesRequestProvider;
        this.seriesDetailsRequestProvider = seriesDetailsRequestProvider;
        this.posterRequestProvider = posterRequestProvider;
    }

    @Override
    public SeriesRequest createSeriesRequest() {
        return seriesRequestProvider.get();
    }

    @Override
    public SeriesDetailsRequest createSeriesDetailsRequest(String seriesName, String imdbId) {
        SeriesDetailsRequest request = seriesDetailsRequestProvider.get();
        request.setRequestParams(seriesName, imdbId);
        return request;
    }

    @Override
    public PosterRequest createPosterRequest(String url) {
        PosterRequest request = posterRequestProvider.get();
        request.setRequestParam(url);
        return request;
    }

    @Override
    public <JSON> CachedSpiceRequest<JSON> createCachedRequest(SpiceRequest<JSON> request, String cacheKey, long cacheDuration) {
        return new CachedSpiceRequest<>(request, cacheKey, cacheDuration);
    }
}
