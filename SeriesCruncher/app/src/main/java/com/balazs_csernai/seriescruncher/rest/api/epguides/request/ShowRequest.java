package com.balazs_csernai.seriescruncher.rest.api.epguides.request;

import com.balazs_csernai.seriescruncher.details.model.ShowEntity;
import com.balazs_csernai.seriescruncher.rest.api.epguides.EPGuideApi;
import com.balazs_csernai.seriescruncher.rest.api.epguides.model.EpisodeJson;
import com.balazs_csernai.seriescruncher.rest.api.omdb.OmdbApi;
import com.balazs_csernai.seriescruncher.rest.api.omdb.model.OmdbDetailJson;
import com.balazs_csernai.seriescruncher.rest.api.epguides.request.NetworkRequest;

import java.util.List;
import java.util.Map;

/**
 * Created by ErikKramli on 2016.01.11..
 */
public class ShowRequest extends NetworkRequest<ShowEntity> {

    private final String showName, imdbId;

    public ShowRequest(String showName, String imdbId, EPGuideApi epGuideApi, OmdbApi omdbApi) {
        super(ShowEntity.class, epGuideApi, omdbApi);
        this.showName = showName;
        this.imdbId = imdbId;
    }

    @Override
    public ShowEntity loadDataFromNetwork() throws Exception {
        Map<Integer, List<EpisodeJson>> seasonMap = getEpGuideService().loadEpisodes(showName);
        OmdbDetailJson detailsJson = getOmdbApi().getShowDetails(imdbId);
        return new ShowEntity(seasonMap, detailsJson);
    }
}
