package com.balazs_csernai.seriescruncher.details.request;

import com.balazs_csernai.seriescruncher.details.model.ShowDetailsEntity;
import com.balazs_csernai.seriescruncher.rest.api.epguides.EPGuideApi;
import com.balazs_csernai.seriescruncher.rest.api.epguides.model.Show;
import com.balazs_csernai.seriescruncher.rest.api.omdb.OmdbApi;
import com.balazs_csernai.seriescruncher.rest.api.omdb.model.ImdbDetailJson;
import com.balazs_csernai.seriescruncher.rest.request.NetworkRequest;

/**
 * Created by ErikKramli on 2016.01.11..
 */
public class ShowDetailsRequest extends NetworkRequest<ShowDetailsEntity> {

    public ShowDetailsRequest(EPGuideApi epGuideApi, OmdbApi omdbApi) {
        super(ShowDetailsEntity.class, epGuideApi, omdbApi);
    }

    @Override
    public ShowDetailsEntity loadDataFromNetwork() throws Exception {

        Show showJson = getEpGuideService().loadShow("blacksails");

        ImdbDetailJson detailsJson = getOmdbApi().getShowDetails("blacksails_imdb_id");

        //todo: build ShowDetailsEntity from showJson and detailsJson

        return null;
    }
}
