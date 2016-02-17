package com.balazs_csernai.seriescruncher.rest.api.model;

import com.balazs_csernai.seriescruncher.rest.api.epguides.model.EpisodeJson;
import com.balazs_csernai.seriescruncher.rest.api.omdb.model.OmdbDetailsJson;

import java.util.List;
import java.util.Map;

/**
 * Created by Balazs_Csernai on 2016.01.27..
 */
public class CombinedEntity implements CombinedModel {

    private Map<Integer, List<EpisodeJson>> seasonMap;
    private OmdbDetailsJson omdbDetails;

    public CombinedEntity() {
    }

    public CombinedEntity setSeasonMap(Map<Integer, List<EpisodeJson>> seasonMap) {
        this.seasonMap = seasonMap;
        return this;
    }

    public CombinedEntity setOmdbDetails(OmdbDetailsJson omdbDetails) {
        this.omdbDetails = omdbDetails;
        return this;
    }

    @Override
    public Map<Integer, List<EpisodeJson>> getSeasonMap() {
        return seasonMap;
    }

    @Override
    public OmdbDetailsJson getOmdbDetails() {
        return omdbDetails;
    }
}
