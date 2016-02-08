package com.balazs_csernai.seriescruncher.serieslist.ui;

import com.balazs_csernai.seriescruncher.rest.api.epguides.model.Series;
import com.balazs_csernai.seriescruncher.serieslist.model.SeriesListModel;

import java.util.List;

/**
 * Created by ErikKramli on 2016.01.17..
 */
public interface SeriesListScreen {

    void onCreate(Callbacks callbacks);

    void displayProgressIndicator();

    void displaySeriesList(SeriesListModel model);

    void updateSeriesList(List<Series> series);

    void clearFilterAndHideKeyboard();

    void showNetworkErrorDialog();

    interface Callbacks {

        void onSeriesSelected(Series series);

        void onFilterTextChanged(CharSequence text);
    }
}
