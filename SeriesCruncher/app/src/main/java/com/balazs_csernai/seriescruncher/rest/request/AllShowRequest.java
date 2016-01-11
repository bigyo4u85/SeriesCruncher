package com.balazs_csernai.seriescruncher.rest.request;

import com.balazs_csernai.seriescruncher.rest.api.omdb.OmdbApi;
import com.balazs_csernai.seriescruncher.rest.api.epguides.EPGuideApi;
import com.balazs_csernai.seriescruncher.rest.api.epguides.model.ShowList;

/**
 * Created by ErikKramli on 2016.01.10..
 */
public class AllShowRequest extends NetworkRequest<ShowList> {

    public AllShowRequest(EPGuideApi epGuideApi, OmdbApi omdbApi) {
        super(ShowList.class, epGuideApi, omdbApi);
    }

    @Override
    public ShowList loadDataFromNetwork() throws Exception {
        return getEpGuideService().loadShows();
    }
}
