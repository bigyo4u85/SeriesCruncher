package com.balazs_csernai.seriescruncher.serieslist.model;

import com.balazs_csernai.seriescruncher.rest.api.epguides.model.Series;
import com.balazs_csernai.seriescruncher.rest.api.epguides.model.SeriesJson;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ErikKramli on 2016.01.17..
 */
public class SeriesListEntity implements SeriesListModel {

    @Expose
    private List<SeriesJson> seriesList;

    public SeriesListEntity(List<SeriesJson> seriesList) {
        this.seriesList = seriesList;
    }

    @Override
    public List<Series> getSeriesList() {
        return new ArrayList<Series>(seriesList);
    }
}
