package com.balazs_csernai.seriescruncher.serieslist.filter;

import android.widget.Filter;

import com.balazs_csernai.seriescruncher.rest.api.epguides.model.Series;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by ErikKramli on 2016.01.25..
 */
public class SeriesFilterImpl implements SeriesFilter {

    private Filter filter;

    @Inject
    public SeriesFilterImpl() {
    }

    @Override
    public void create(List<Series> allSeries, Callback callback) {
        filter = new PrefixFilter(allSeries, callback);
    }

    @Override
    public void applyPrefixFilter(CharSequence constraint) {
        if (filter != null) {
            filter.filter(constraint);
        }
    }

}
