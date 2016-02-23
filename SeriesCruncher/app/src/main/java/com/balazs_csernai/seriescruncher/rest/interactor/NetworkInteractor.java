package com.balazs_csernai.seriescruncher.rest.interactor;

import com.balazs_csernai.seriescruncher.rest.api.epguides.EPGuideApi;
import com.balazs_csernai.seriescruncher.rest.api.omdb.OmdbApi;

/**
 * Created by Erik_Markus_Kramli on 2016-02-22.
 */
public abstract class NetworkInteractor<RESULT> implements Interactor<RESULT> {

    private final EPGuideApi epGuideApi;
    private final OmdbApi omdbApi;

    public NetworkInteractor(EPGuideApi epGuideApi, OmdbApi omdbApi) {
        this.epGuideApi = epGuideApi;
        this.omdbApi = omdbApi;
    }

    protected EPGuideApi getEpGuideApi() {
        return epGuideApi;
    }

    protected OmdbApi getOmdbApi() {
        return omdbApi;
    }

    @Override
    public void cancel() {
    }
}
