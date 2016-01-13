package com.balazs_csernai.seriescruncher.details.request;

import com.balazs_csernai.seriescruncher.details.model.ShowDetailsEntity;
import com.balazs_csernai.seriescruncher.rest.api.epguides.EPGuideApi;
import com.balazs_csernai.seriescruncher.rest.api.epguides.model.SeasonJson;
import com.balazs_csernai.seriescruncher.rest.api.epguides.model.ShowJson;
import com.balazs_csernai.seriescruncher.rest.api.omdb.OmdbApi;
import com.balazs_csernai.seriescruncher.rest.api.omdb.model.OmdbDetailJson;
import com.balazs_csernai.seriescruncher.rest.request.NetworkRequest;

/**
 * Created by ErikKramli on 2016.01.11..
 */
public class ShowDetailsRequest extends NetworkRequest<ShowDetailsEntity> {

    private final String showName;

    public ShowDetailsRequest(String showName, EPGuideApi epGuideApi, OmdbApi omdbApi) {
        super(ShowDetailsEntity.class, epGuideApi, omdbApi);
        this.showName = showName;
    }

    @Override
    public ShowDetailsEntity loadDataFromNetwork() throws Exception {

        SeasonJson showJsonJson = getEpGuideService().loadShow(showName);

        OmdbDetailJson detailsJson = getOmdbApi().getShowDetails("tt0898266");

        ShowDetailsEntity entity = new ShowDetailsEntity();
        entity.showJson = showJsonJson;
        entity.omdbDetailJson = detailsJson;

        return entity;
    }
}
