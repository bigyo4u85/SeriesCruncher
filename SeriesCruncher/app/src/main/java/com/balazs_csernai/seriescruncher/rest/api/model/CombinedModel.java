package com.balazs_csernai.seriescruncher.rest.api.model;

import com.balazs_csernai.seriescruncher.rest.api.epguides.model.EpisodeJson;
import com.balazs_csernai.seriescruncher.rest.api.omdb.model.OmdbDetailsJson;

import java.util.List;
import java.util.Map;

/**
 * Created by Balazs_Csernai on 2016.01.27..
 */
public interface CombinedModel {

    Map<Integer, List<EpisodeJson>> getSeasonMap();

    OmdbDetailsJson getOmdbDetails();
}
