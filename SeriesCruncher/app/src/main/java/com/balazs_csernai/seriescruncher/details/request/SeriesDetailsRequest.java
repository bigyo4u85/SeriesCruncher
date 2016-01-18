package com.balazs_csernai.seriescruncher.details.request;

import com.balazs_csernai.seriescruncher.details.model.SeriesDetailsEntity;
import com.balazs_csernai.seriescruncher.rest.api.epguides.EPGuideApi;
import com.balazs_csernai.seriescruncher.rest.api.epguides.model.EpisodeJson;
import com.balazs_csernai.seriescruncher.rest.api.omdb.OmdbApi;
import com.balazs_csernai.seriescruncher.rest.api.omdb.model.OmdbDetailJson;
import com.balazs_csernai.seriescruncher.rest.request.NetworkRequest;

import java.util.List;
import java.util.Map;

/**
 * Created by ErikKramli on 2016.01.11..
 */
public class SeriesDetailsRequest extends NetworkRequest<SeriesDetailsEntity> {

    private final String showName, imdbId;

    public SeriesDetailsRequest(String showName, String imdbId, EPGuideApi epGuideApi, OmdbApi omdbApi) {
        super(SeriesDetailsEntity.class, epGuideApi, omdbApi);
        this.showName = showName;
        this.imdbId = imdbId;
    }

    @Override
    public SeriesDetailsEntity loadDataFromNetwork() throws Exception {
        Map<Integer, List<EpisodeJson>> seasonMap = getEpGuideService().loadDetails(showName);
        OmdbDetailJson detailsJson = getOmdbApi().getShowDetails(imdbId);
        return new SeriesDetailsEntity(seasonMap, detailsJson);
    }
}
