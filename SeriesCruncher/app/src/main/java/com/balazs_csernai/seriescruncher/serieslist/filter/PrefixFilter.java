package com.balazs_csernai.seriescruncher.serieslist.filter;

import android.text.TextUtils;
import android.widget.Filter;

import com.balazs_csernai.seriescruncher.rest.api.epguides.model.Series;
import com.balazs_csernai.seriescruncher.serieslist.filter.SeriesFilter.Callback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ErikKramli on 2016.01.25..
 */
final class PrefixFilter extends Filter {

    private List<Series> allSeries;
    private Callback callback;

    PrefixFilter(List<Series> allSeries, Callback callback) {
        this.allSeries = new ArrayList<>(allSeries);
        this.callback = callback;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        if (TextUtils.isEmpty(constraint)) {
            return createResult(allSeries);

        } else {
            final String prefix = constraint.toString().toUpperCase().trim();
            List<Series> filteredList = new ArrayList<>();
            for (Series series : allSeries) {
                if (series.getTitle().toUpperCase().startsWith(prefix)) {
                    filteredList.add(series);
                }
            }
            return createResult(filteredList);
        }
    }

    private FilterResults createResult(final List<Series> series) {
        FilterResults filteredResult = new FilterResults();
        filteredResult.values = series;
        filteredResult.count = series.size();
        return filteredResult;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        callback.onSeriesFiltered((List<Series>) results.values);
    }
}
