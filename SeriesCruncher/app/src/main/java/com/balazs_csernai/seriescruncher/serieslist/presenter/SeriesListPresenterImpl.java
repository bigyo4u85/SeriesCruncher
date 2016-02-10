package com.balazs_csernai.seriescruncher.serieslist.presenter;

import com.balazs_csernai.seriescruncher.rest.SeriesLoader;
import com.balazs_csernai.seriescruncher.rest.api.epguides.model.Series;
import com.balazs_csernai.seriescruncher.rest.loader.Loader;
import com.balazs_csernai.seriescruncher.serieslist.filter.SeriesFilter;
import com.balazs_csernai.seriescruncher.serieslist.model.SeriesListModel;
import com.balazs_csernai.seriescruncher.serieslist.ui.SeriesListScreen;
import com.balazs_csernai.seriescruncher.utils.navigator.SeriesListNavigator;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by ErikKramli on 2016.01.17..
 */
public class SeriesListPresenterImpl implements SeriesListPresenter, Loader.Callback<SeriesListModel>, SeriesListScreen.Callbacks, SeriesFilter.Callback {

    private final SeriesLoader loader;
    private final SeriesFilter filter;
    private final SeriesListScreen screen;
    private final SeriesListNavigator navigator;

    @Inject
    public SeriesListPresenterImpl(SeriesLoader loader, SeriesFilter filter, SeriesListScreen screen, SeriesListNavigator seriesListNavigator) {
        this.loader = loader;
        this.filter = filter;
        this.screen = screen;
        this.navigator = seriesListNavigator;
    }

    @Override
    public void onStart() {
        screen.onCreate(this);
        loader.bind();
        loadSeriesList();
    }

    private void loadSeriesList() {
        screen.displayProgressIndicator();
        loader.loadSeries(this);
    }

    @Override
    public void onStop() {
        loader.unbind();
    }

    @Override
    public void onSettingsMenuRequest() {
        navigator.launchSettings();
    }

    @Override
    public void onSuccess(SeriesListModel model) {
        filter.create(model.getSeriesList(), this);
        screen.displaySeriesList(model);
    }

    @Override
    public void onFailure() {
        screen.showNetworkErrorDialog();
    }

    @Override
    public void onSeriesSelected(Series series) {
        screen.clearFilterAndHideKeyboard();
        navigator.launchSeriesDetails(series.getName(), series.getImdbId());
    }

    @Override
    public void onFilterTextChanged(CharSequence text) {
        filter.applyPrefixFilter(text);
    }

    @Override
    public void onSeriesFiltered(List<Series> filteredSeries) {
        screen.updateSeriesList(filteredSeries);
    }

    @Override
    public void onNetworkErrorRetry() {
        loadSeriesList();
    }

    @Override
    public void onNetworkErrorCancel() {
        navigator.closeSeriesList();
    }
}
