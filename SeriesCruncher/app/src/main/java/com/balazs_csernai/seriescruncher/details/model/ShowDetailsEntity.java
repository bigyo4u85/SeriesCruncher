package com.balazs_csernai.seriescruncher.details.model;

import com.balazs_csernai.seriescruncher.rest.api.epguides.model.SeasonJson;
import com.balazs_csernai.seriescruncher.rest.api.epguides.model.ShowJson;
import com.balazs_csernai.seriescruncher.rest.api.omdb.model.OmdbDetailJson;
import com.balazs_csernai.seriescruncher.rest.api.omdb.model.OmdbDetailModel;

/**
 * Created by ErikKramli on 2016.01.11..
 */
public class ShowDetailsEntity implements ShowDetailsModel {

    public SeasonJson showJson;

    public OmdbDetailJson omdbDetailJson;


    @Override
    public SeasonJson getShowJson() {
        return showJson;
    }

    @Override
    public OmdbDetailModel getOmdbDetails() {
        return omdbDetailJson;
    }
}
