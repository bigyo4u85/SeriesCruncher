package com.balazs_csernai.seriescruncher.details.model;

import com.balazs_csernai.seriescruncher.rest.api.epguides.model.Episode;
import com.balazs_csernai.seriescruncher.rest.api.omdb.model.OmdbDetailModel;

import java.util.List;
import java.util.Map;

/**
 * Created by Erik_Markus_Kramli on 2016-01-13.
 */
public interface SeriesDetailsModel {

    Map<Integer, List<Episode>> getSeasonMap();

    OmdbDetailModel getOmdbDetails();
}
