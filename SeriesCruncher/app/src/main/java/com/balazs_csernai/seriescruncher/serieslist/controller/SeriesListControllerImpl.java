package com.balazs_csernai.seriescruncher.serieslist.controller;

import com.balazs_csernai.seriescruncher.rest.SeriesLoader;
import com.balazs_csernai.seriescruncher.rest.api.epguides.model.Show;
import com.balazs_csernai.seriescruncher.rest.loader.Loader;
import com.balazs_csernai.seriescruncher.serieslist.model.SeriesModel;
import com.balazs_csernai.seriescruncher.serieslist.ui.SeriesListScreen;

import javax.inject.Inject;

/**
 * Created by ErikKramli on 2016.01.17..
 */
public class SeriesListControllerImpl implements SeriesListController, Loader.Callback<SeriesModel>, SeriesListScreen.Callbacks {

    private final SeriesLoader loader;
    private final SeriesListScreen screen;

    @Inject
    public SeriesListControllerImpl(SeriesLoader loader, SeriesListScreen screen) {
        this.loader = loader;
        this.screen = screen;
    }

    @Override
    public void onStart() {
        screen.onCrate(this);
        loader.bind();

        screen.displayLoadingView();
        loader.loadSeries(this);
    }

    @Override
    public void onStop() {
        loader.unbind();
    }

    @Override
    public void onSuccess(SeriesModel series) {
        screen.displaySeriesList(series);
    }

    @Override
    public void onFailure() {
    }

    @Override
    public void onShowTapped(Show show) {
    }
}
