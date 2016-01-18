package com.balazs_csernai.seriescruncher.serieslist.model;

import com.balazs_csernai.seriescruncher.rest.api.epguides.model.Show;
import com.balazs_csernai.seriescruncher.rest.api.epguides.model.ShowJson;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ErikKramli on 2016.01.17..
 */
public class SeriesEntity implements SeriesModel {

    @Expose
    private List<ShowJson> series;

    public SeriesEntity(List<ShowJson> series) {
        this.series = series;
    }

    @Override
    public List<Show> getSeries() {
        return new ArrayList<Show>(series);
    }
}
