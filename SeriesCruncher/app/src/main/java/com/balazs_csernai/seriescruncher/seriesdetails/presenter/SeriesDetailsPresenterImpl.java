package com.balazs_csernai.seriescruncher.seriesdetails.presenter;

import com.balazs_csernai.seriescruncher.utils.converter.EpisodeList;
import com.balazs_csernai.seriescruncher.seriesdetails.model.EpisodeListModel;
import com.balazs_csernai.seriescruncher.seriesdetails.model.SeriesDetailsModel;
import com.balazs_csernai.seriescruncher.seriesdetails.ui.SeriesDetailsScreen;
import com.balazs_csernai.seriescruncher.rest.SeriesLoader;
import com.balazs_csernai.seriescruncher.utils.converter.ModelConverter;

import javax.inject.Inject;

/**
 * Created by Erik_Markus_Kramli on 2016-01-13.
 */
public class SeriesDetailsPresenterImpl implements SeriesDetailsPresenter, SeriesLoader.Callback<SeriesDetailsModel> {

    private final SeriesLoader seriesLoader;
    private final SeriesDetailsScreen screen;
    private final ModelConverter converter;
    private SeriesDetailsModel model;

    @Inject
    public SeriesDetailsPresenterImpl(SeriesLoader seriesLoader, SeriesDetailsScreen screen, @EpisodeList ModelConverter converter) {
        this.seriesLoader = seriesLoader;
        this.screen = screen;
        this.converter = converter;
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
        model = result;
        screen.show(model.getTitle());
        screen.show((EpisodeListModel) converter.convert(result));
    }

    @Override
    public void onFailure() {

    }
}
