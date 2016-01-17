package com.balazs_csernai.seriescruncher.serieslist.model;

import com.balazs_csernai.seriescruncher.rest.api.epguides.model.Show;

import java.util.List;

/**
 * Created by ErikKramli on 2016.01.17..
 */
public interface SeriesModel {

    List<Show> getSeries();
}
