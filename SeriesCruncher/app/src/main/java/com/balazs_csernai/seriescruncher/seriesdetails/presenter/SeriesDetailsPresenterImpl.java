package com.balazs_csernai.seriescruncher.seriesdetails.presenter;

import com.balazs_csernai.seriescruncher.rest.loader.Loader;
import com.balazs_csernai.seriescruncher.seriesdetails.model.PosterModel;
import com.balazs_csernai.seriescruncher.utils.converter.EpisodeList;
import com.balazs_csernai.seriescruncher.seriesdetails.model.EpisodeListModel;
import com.balazs_csernai.seriescruncher.seriesdetails.model.SeriesDetailsModel;
import com.balazs_csernai.seriescruncher.seriesdetails.ui.SeriesDetailsScreen;
import com.balazs_csernai.seriescruncher.rest.SeriesLoader;
import com.balazs_csernai.seriescruncher.utils.converter.ModelConverter;
import com.balazs_csernai.seriescruncher.image.ImageLoader;

import javax.inject.Inject;

/**
 * Created by Erik_Markus_Kramli on 2016-01-13.
 */
public class SeriesDetailsPresenterImpl implements SeriesDetailsPresenter, SeriesLoader.Callback<SeriesDetailsModel> {

    private final SeriesLoader seriesLoader;
    private final SeriesDetailsScreen screen;
    private final ModelConverter converter;
    private final ImageLoader imageLoader;

    @Inject
    public SeriesDetailsPresenterImpl(SeriesLoader seriesLoader, SeriesDetailsScreen screen, @EpisodeList ModelConverter converter, ImageLoader imageLoader) {
        this.seriesLoader = seriesLoader;
        this.screen = screen;
        this.converter = converter;
        this.imageLoader = imageLoader;
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
    public void onSuccess(SeriesDetailsModel model) {
        screen.show(model.getTitle());
        screen.show((EpisodeListModel) converter.convert(model));

        seriesLoader.loadPoster(model.getImageUrl(), new Loader.Callback<PosterModel>() {
            @Override
            public void onSuccess(PosterModel result) {
                screen.show(result);
            }

            @Override
            public void onFailure() {

            }
        });
    }

    @Override
    public void onFailure() {
    }
}
