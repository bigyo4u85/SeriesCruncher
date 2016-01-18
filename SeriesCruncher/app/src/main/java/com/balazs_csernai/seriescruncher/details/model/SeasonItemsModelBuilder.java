package com.balazs_csernai.seriescruncher.details.model;

import com.balazs_csernai.seriescruncher.rest.api.epguides.model.Episode;

import java.util.List;
import java.util.Map;

/**
 * Created by Balazs_Csernai on 2016.01.18..
 */
public interface SeasonItemsModelBuilder {

    SeasonItemsModel empty();

    SeasonItemsModel build(Map<Integer, List<Episode>> seasonMap);
}
