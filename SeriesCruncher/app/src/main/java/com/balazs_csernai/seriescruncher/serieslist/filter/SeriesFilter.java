package com.balazs_csernai.seriescruncher.serieslist.filter;

import com.balazs_csernai.seriescruncher.rest.api.epguides.model.Series;

import java.util.List;

/**
 * Created by ErikKramli on 2016.01.25..
 */
public interface SeriesFilter {

    interface Callback {
        void onSeriesFiltered(List<Series> filteredSeries);
    }

    void create(List<Series> allSeries, Callback callback);

    void applyPrefixFilter(CharSequence constraint);
}
