package com.balazs_csernai.seriescruncher.rest.request;

import com.balazs_csernai.seriescruncher.rest.api.omdb.OmdbApi;
import com.balazs_csernai.seriescruncher.rest.api.epguides.EPGuideApi;
import com.balazs_csernai.seriescruncher.rest.api.epguides.model.ShowShortList;

/**
 * Created by ErikKramli on 2016.01.10..
 */
public class AllShowRequest extends NetworkRequest<ShowShortList> {

    public AllShowRequest(EPGuideApi epGuideApi, OmdbApi omdbApi) {
        super(ShowShortList.class, epGuideApi, omdbApi);
    }

    @Override
    public ShowShortList loadDataFromNetwork() throws Exception {
        return getEpGuideService().loadShows();
    }
}
