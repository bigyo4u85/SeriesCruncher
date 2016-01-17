package com.balazs_csernai.seriescruncher.details.controller;

import com.balazs_csernai.seriescruncher.details.model.ShowDetailsModel;
import com.balazs_csernai.seriescruncher.details.ui.DetailsScreen;
import com.balazs_csernai.seriescruncher.rest.SeriesLoader;

import javax.inject.Inject;

/**
 * Created by Erik_Markus_Kramli on 2016-01-13.
 */
public class DetailsControllerImpl implements DetailsController, SeriesLoader.Callback<ShowDetailsModel> {

    private final SeriesLoader seriesLoader;
    private final DetailsScreen screen;

    @Inject
    public DetailsControllerImpl(SeriesLoader seriesLoader, DetailsScreen screen) {
        this.seriesLoader = seriesLoader;
        this.screen = screen;
    }

    @Override
    public void onStart() {
        screen.onCreate();
        seriesLoader.bind();
    }

    @Override
    public void loadShowDetails(String showName, String imdbId) {
        seriesLoader.loadDetails(showName, imdbId, this);
    }

    @Override
    public void onStop() {
        seriesLoader.unbind();
    }

    @Override
    public void onSuccess(ShowDetailsModel result) {
        String text = result.getSeasonMap().get(1).get(0).getTitle() + "\n" + result.getOmdbDetails().getPosterUrl();

        screen.show("loaded \n " + text);
    }

    @Override
    public void onFailure() {

    }
}
