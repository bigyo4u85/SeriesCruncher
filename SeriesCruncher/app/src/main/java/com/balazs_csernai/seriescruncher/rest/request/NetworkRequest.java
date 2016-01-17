package com.balazs_csernai.seriescruncher.rest.request;

import com.balazs_csernai.seriescruncher.rest.api.omdb.OmdbApi;
import com.balazs_csernai.seriescruncher.rest.api.epguides.EPGuideApi;
import com.octo.android.robospice.request.SpiceRequest;

/**
 * Created by ErikKramli on 2016.01.11..
 */
public abstract class NetworkRequest<JSON> extends SpiceRequest<JSON> {

    private final EPGuideApi epGuideApi;
    private final OmdbApi omdbApi;

    public NetworkRequest(Class<JSON> clazz, EPGuideApi epGuideApi, OmdbApi omdbApi) {
        super(clazz);
        this.epGuideApi = epGuideApi;
        this.omdbApi = omdbApi;
    }

    protected EPGuideApi getEpGuideService() {
        return epGuideApi;
    }

    protected OmdbApi getOmdbApi() {
        return omdbApi;
    }

}
