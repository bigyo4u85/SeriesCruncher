package com.balazs_csernai.seriescruncher.serieslist.model;

import com.balazs_csernai.seriescruncher.rest.api.epguides.model.Series;
import com.balazs_csernai.seriescruncher.rest.api.epguides.model.SeriesJson;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ErikKramli on 2016.01.17..
 */
public class SeriesListEntity implements SeriesListModel {

    @Expose
    private Set<SeriesJson> seriesSet;

    public SeriesListEntity(List<SeriesJson> seriesList) {
        seriesSet = new LinkedHashSet<>(seriesList);
    }

    @Override
    public List<Series> getSeriesList() {
        return new ArrayList<Series>(seriesSet);
    }
}
