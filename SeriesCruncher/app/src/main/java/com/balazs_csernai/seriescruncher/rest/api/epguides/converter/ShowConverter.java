package com.balazs_csernai.seriescruncher.rest.api.epguides.converter;

import com.balazs_csernai.seriescruncher.model.ShowEntity;
import com.balazs_csernai.seriescruncher.model.ShowModel;
import com.balazs_csernai.seriescruncher.rest.api.epguides.model.ShowJSON;

import javax.inject.Inject;

/**
 * Created by Balázs on 2016.01.23..
 */
public class ShowConverter {

    @Inject
    public ShowConverter() {
    }

    public ShowModel convert(ShowJSON show) {
        return new ShowEntity(show.getEPGuideName(), show.getTitle(), show.getIMDBId());
    }
}
