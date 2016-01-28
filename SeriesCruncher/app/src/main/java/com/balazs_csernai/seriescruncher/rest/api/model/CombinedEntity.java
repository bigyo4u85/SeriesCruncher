package com.balazs_csernai.seriescruncher.rest.api.model;

import com.balazs_csernai.seriescruncher.rest.api.epguides.model.EpisodeJson;
import com.balazs_csernai.seriescruncher.rest.api.omdb.model.OmdbDetailsJson;

import java.util.List;
import java.util.Map;

/**
 * Created by Balazs_Csernai on 2016.01.27..
 */
public class CombinedEntity implements CombinedModel {

    private Map<Integer, List<EpisodeJson>> episodeMap;
    private OmdbDetailsJson omdbDetails;

    public CombinedEntity() {
    }

    public CombinedEntity setEpisodeMap(Map<Integer, List<EpisodeJson>> episodeMap) {
        this.episodeMap = episodeMap;
        return this;
    }

    public CombinedEntity setOmdbDetails(OmdbDetailsJson omdbDetails) {
        this.omdbDetails = omdbDetails;
        return this;
    }

    @Override
    public Map<Integer, List<EpisodeJson>> getEpisodeMap() {
        return episodeMap;
    }

    @Override
    public OmdbDetailsJson getOmdbDetails() {
        return omdbDetails;
    }
}
