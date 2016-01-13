package com.balazs_csernai.seriescruncher.details.model;

import com.balazs_csernai.seriescruncher.rest.api.epguides.model.EpisodeJson;
import com.balazs_csernai.seriescruncher.rest.api.omdb.model.OmdbDetailJson;
import com.balazs_csernai.seriescruncher.rest.api.omdb.model.OmdbDetailModel;
import com.google.gson.annotations.Expose;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ErikKramli on 2016.01.11..
 */
public class ShowDetailsEntity implements ShowDetailsModel {

    @Expose
    private Map<Integer, List<EpisodeJson>> seasonMap;

    @Expose
    private OmdbDetailJson omdbDetailJson;

    public ShowDetailsEntity(Map<Integer, List<EpisodeJson>> seasonMap, OmdbDetailJson omdbDetailJson) {
        this.seasonMap = seasonMap;
        this.omdbDetailJson = omdbDetailJson;
    }

    @Override
    public Map<Integer, List<EpisodeJson>> getSeasonMap() {
        return seasonMap;
    }

    @Override
    public OmdbDetailModel getOmdbDetails() {
        return omdbDetailJson;
    }
}
