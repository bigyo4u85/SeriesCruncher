package com.balazs_csernai.seriescruncher.details.model;

import com.balazs_csernai.seriescruncher.rest.api.epguides.model.EpisodeJson;
import com.balazs_csernai.seriescruncher.rest.api.omdb.model.OmdbDetailJson;
import com.balazs_csernai.seriescruncher.rest.api.omdb.model.OmdbDetailModel;
import com.balazs_csernai.seriescruncher.utils.data.DataTransformUtil;

import java.util.List;
import java.util.Map;

/**
 * Created by ErikKramli on 2016.01.11..
 */
public class ShowEntity implements ShowModel {

    private SeasonsModel seasons;

    private OmdbDetailJson omdbDetailJson;

    public ShowEntity(Map<Integer, List<EpisodeJson>> seasons, OmdbDetailJson omdbDetailJson) {
        this.seasons = DataTransformUtil.getSeasons(seasons);
        this.omdbDetailJson = omdbDetailJson;
    }

    @Override
    public SeasonsModel getSeasons() {
        return seasons;
    }

    @Override
    public OmdbDetailModel getOmdbDetails() {
        return omdbDetailJson;
    }
}
