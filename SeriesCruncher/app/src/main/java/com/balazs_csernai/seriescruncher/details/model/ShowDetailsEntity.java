package com.balazs_csernai.seriescruncher.details.model;

import com.balazs_csernai.seriescruncher.rest.api.epguides.model.Episode;
import com.balazs_csernai.seriescruncher.rest.api.epguides.model.EpisodeJson;
import com.balazs_csernai.seriescruncher.rest.api.omdb.model.OmdbDetailJson;
import com.balazs_csernai.seriescruncher.rest.api.omdb.model.OmdbDetailModel;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ErikKramli on 2016.01.11..
 */
public class ShowDetailsEntity implements ShowDetailsModel {

    @Expose
    private Map<Integer, List<EpisodeJson>> seasonMapJson;

    @Expose
    private OmdbDetailJson omdbDetailJson;

    public ShowDetailsEntity(Map<Integer, List<EpisodeJson>> seasonMapJson, OmdbDetailJson omdbDetailJson) {
        this.seasonMapJson = seasonMapJson;
        this.omdbDetailJson = omdbDetailJson;
    }

    @Override
    public Map<Integer, List<Episode>> getSeasonMap() {
        return transformSeasonMapJson();
    }

    private Map<Integer, List<Episode>> transformSeasonMapJson() {
        Map<Integer, List<Episode>> seasonMapModel = new HashMap<>();
        for (Map.Entry<Integer, List<EpisodeJson>> seasonJson : seasonMapJson.entrySet()) {
            seasonMapModel.put(seasonJson.getKey(), new ArrayList<Episode>(seasonJson.getValue()));
        }
        return seasonMapModel;
    }

    @Override
    public OmdbDetailModel getOmdbDetails() {
        return omdbDetailJson;
    }
}
