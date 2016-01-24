package com.balazs_csernai.seriescruncher.serieslist.ui;

import android.widget.Filter;

import com.balazs_csernai.seriescruncher.rest.api.epguides.model.Series;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ErikKramli on 2016.01.24..
 */
final class PrefixFilter extends Filter {

    private final SeriesListAdapter adapter;

    public PrefixFilter(SeriesListAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();

        if (constraint == null || constraint.length() < 3) {
            results.values = adapter.getItems();
            results.count = adapter.getItemCount();

        } else {
            final String prefix = constraint.toString().toUpperCase().trim();
            List<Series> filteredList = new ArrayList<>();
            for (Series series : adapter.getItems()) {
                if (series.getTitle().toUpperCase().startsWith(prefix)) {
                    filteredList.add(series);
                }
            }
            results.values = filteredList;
            results.count = filteredList.size();
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.setItems((List<Series>) results.values);
    }
}
