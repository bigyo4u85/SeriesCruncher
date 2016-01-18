package com.balazs_csernai.seriescruncher.rest.request;

import com.balazs_csernai.seriescruncher.details.request.SeriesDetailsRequest;
import com.balazs_csernai.seriescruncher.rest.api.epguides.EPGuideApi;
import com.balazs_csernai.seriescruncher.rest.api.omdb.OmdbApi;
import com.balazs_csernai.seriescruncher.serieslist.request.SeriesRequest;
import com.octo.android.robospice.request.CachedSpiceRequest;
import com.octo.android.robospice.request.SpiceRequest;

import javax.inject.Inject;

/**
 * Created by ErikKramli on 2016.01.14..
 */
public class RequestFactoryImpl implements RequestFactory {

    private final EPGuideApi epGuideApi;
    private final OmdbApi omdbApi;

    @Inject
    public RequestFactoryImpl(EPGuideApi epGuideApi, OmdbApi omdbApi) {
        this.epGuideApi = epGuideApi;
        this.omdbApi = omdbApi;
    }

    @Override
    public SeriesRequest createSeriesRequest() {
        return new SeriesRequest(epGuideApi, omdbApi);
    }

    @Override
    public SeriesDetailsRequest createSeriesDetailsRequest(String seriesName, String imdbId) {
        return new SeriesDetailsRequest(seriesName, imdbId, epGuideApi, omdbApi);
    }

    @Override
    public <JSON> CachedSpiceRequest<JSON> createCachedRequest(SpiceRequest<JSON> request, String cacheKey, long cacheDuration) {
        return new CachedSpiceRequest<>(request, cacheKey, cacheDuration);
    }
}
