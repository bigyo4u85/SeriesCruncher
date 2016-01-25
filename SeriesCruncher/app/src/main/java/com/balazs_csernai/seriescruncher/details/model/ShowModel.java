package com.balazs_csernai.seriescruncher.details.model;

import com.balazs_csernai.seriescruncher.rest.api.omdb.model.OmdbDetailModel;

/**
 * Created by Erik_Markus_Kramli on 2016-01-13.
 */
public interface ShowModel {

    SeasonsModel getSeasons();

    OmdbDetailModel getOmdbDetails();
}
