package com.balazs_csernai.seriescruncher.details.presenter;

import com.balazs_csernai.seriescruncher.details.model.SeriesDetailsModel;
import com.balazs_csernai.seriescruncher.details.ui.SeriesDetailsScreen;
import com.balazs_csernai.seriescruncher.rest.SeriesLoader;

import javax.inject.Inject;

/**
 * Created by Erik_Markus_Kramli on 2016-01-13.
 */
public class SeriesDetailsPresenterImpl implements SeriesDetailsPresenter, SeriesLoader.Callback<SeriesDetailsModel> {

    private final SeriesLoader seriesLoader;
    private final SeriesDetailsScreen screen;

    @Inject
    public SeriesDetailsPresenterImpl(SeriesLoader seriesLoader, SeriesDetailsScreen screen) {
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
    public void onSuccess(SeriesDetailsModel result) {
        String text = result.getSeasonMap().get(1).get(0).getTitle() + "\n" + result.getOmdbDetails().getPosterUrl();

        screen.show("loaded \n " + text);
        screen.show(result.getSeasonMap());
    }

    @Override
    public void onFailure() {

    }
}
