package com.balazs_csernai.seriescruncher.serieslist.model;

import com.balazs_csernai.seriescruncher.rest.api.epguides.model.Series;

import java.util.List;

/**
 * Created by ErikKramli on 2016.01.17..
 */
public interface SeriesListModel {

    List<Series> getSeriesList();
}
