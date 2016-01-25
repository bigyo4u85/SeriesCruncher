package com.balazs_csernai.seriescruncher.details.presenter;

import com.balazs_csernai.seriescruncher.details.model.ShowModel;
import com.balazs_csernai.seriescruncher.details.ui.SeriesDetailsScreen;
import com.balazs_csernai.seriescruncher.rest.api.epguides.loader.DataLoader;

import javax.inject.Inject;

/**
 * Created by Erik_Markus_Kramli on 2016-01-13.
 */
public class SeriesDetailsPresenterImpl implements SeriesDetailsPresenter, DataLoader.Callback<ShowModel> {

    private final DataLoader dataLoader;
    private final SeriesDetailsScreen screen;

    @Inject
    public SeriesDetailsPresenterImpl(DataLoader dataLoader, SeriesDetailsScreen screen) {
        this.dataLoader = dataLoader;
        this.screen = screen;
    }

    @Override
    public void onStart() {
        screen.onCreate();
        dataLoader.bind();
    }

    @Override
    public void loadShowDetails(String showName, String imdbId) {
        dataLoader.loadShow(showName, imdbId, this);
    }

    @Override
    public void onStop() {
        dataLoader.unbind();
    }

    @Override
    public void onSuccess(ShowModel result) {
        screen.show("loaded \n " + result.getOmdbDetails().getPosterUrl());
        screen.show(result);
    }

    @Override
    public void onFailure() {

    }
}
