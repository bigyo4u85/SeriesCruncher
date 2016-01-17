package com.balazs_csernai.seriescruncher.serieslist.controller;

import com.balazs_csernai.seriescruncher.rest.SeriesLoader;
import com.balazs_csernai.seriescruncher.serieslist.ui.SeriesListScreen;

import javax.inject.Inject;

/**
 * Created by ErikKramli on 2016.01.17..
 */
public class SeriesListControllerImpl implements SeriesListController {

    private final SeriesLoader loader;
    private final SeriesListScreen screen;

    @Inject
    public SeriesListControllerImpl(SeriesLoader loader, SeriesListScreen screen) {
        this.loader = loader;
        this.screen = screen;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
