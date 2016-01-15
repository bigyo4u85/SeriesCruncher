package com.balazs_csernai.seriescruncher.rest.request;

import com.balazs_csernai.seriescruncher.details.request.ShowDetailsRequest;
import com.balazs_csernai.seriescruncher.rest.api.epguides.EPGuideApi;
import com.balazs_csernai.seriescruncher.rest.api.omdb.OmdbApi;
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
    public AllShowRequest createAllShowRequest() {
        return new AllShowRequest(epGuideApi, omdbApi);
    }

    @Override
    public ShowDetailsRequest createShowDetailsRequest(String showName, String imdbId) {
        return new ShowDetailsRequest(showName, imdbId, epGuideApi, omdbApi);
    }

    @Override
    public <JSON> CachedSpiceRequest<JSON> createCachedRequest(SpiceRequest<JSON> request, String cacheKey, long cacheDuration) {
        return new CachedSpiceRequest<>(request, cacheKey, cacheDuration);
    }
}
