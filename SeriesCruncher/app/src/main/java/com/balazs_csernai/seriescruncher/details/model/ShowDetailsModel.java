package com.balazs_csernai.seriescruncher.details.model;

import com.balazs_csernai.seriescruncher.rest.api.epguides.model.SeasonJson;
import com.balazs_csernai.seriescruncher.rest.api.epguides.model.ShowJson;
import com.balazs_csernai.seriescruncher.rest.api.omdb.model.OmdbDetailModel;

/**
 * Created by Erik_Markus_Kramli on 2016-01-13.
 */
public interface ShowDetailsModel {

    SeasonJson getShowJson();

    OmdbDetailModel getOmdbDetails();
}
