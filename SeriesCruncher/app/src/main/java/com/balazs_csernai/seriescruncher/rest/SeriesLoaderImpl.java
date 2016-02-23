package com.balazs_csernai.seriescruncher.rest;

import com.balazs_csernai.seriescruncher.rest.interactor.CacheType;
import com.balazs_csernai.seriescruncher.rest.loader.RequestLoader;
import com.balazs_csernai.seriescruncher.rest.interactor.InteractorFactory;
import com.balazs_csernai.seriescruncher.seriesdetails.model.SeriesDetailsModel;
import com.balazs_csernai.seriescruncher.seriesdetails.model.poster.PosterModel;
import com.balazs_csernai.seriescruncher.serieslist.model.SeriesListModel;

import java.util.Collection;

import javax.inject.Inject;

/**
 * Created by Balazs_Csernai on 2016.01.08..
 */
public class SeriesLoaderImpl implements SeriesLoader {

    private final RequestLoader loader;
    private final InteractorFactory interactorFactory;

    @Inject
    public SeriesLoaderImpl(RequestLoader loader, InteractorFactory interactorFactory) {
        this.loader = loader;
        this.interactorFactory = interactorFactory;
    }

    @Override
    public void bind() {
        loader.bind();
    }

    @Override
    public void unbind() {
        loader.unbind();
    }

    @Override
    public void loadSeries(@CacheType long cacheType, final Callback<SeriesListModel> callback) {
        loader.perform(
                interactorFactory.createSeriesListInteractor(),
                "series",
                cacheType,
                callback
        );
    }

    @Override
    public void loadDetails(String seriesName, @CacheType long cacheType, final Callback<SeriesDetailsModel> callback) {
        loader.perform(
                interactorFactory.createSeriesDetailsInteractor(seriesName),
                seriesName,
                cacheType,
                callback
        );
    }

    @Override
    public void loadDetails(Collection<String> seriesNames, @CacheType long cacheType, final Callback<SeriesDetailsModel> callback) {
        for (String seriesName : seriesNames) {
            loader.perform(
                    interactorFactory.createSeriesDetailsInteractor(seriesName),
                    seriesName,
                    cacheType,
                    callback
            );
        }
    }

    @Override
    public void loadPoster(String posterUrl, Callback<PosterModel> callback) {
        loader.perform(
                interactorFactory.createPosterInteractor(posterUrl),
                callback
        );
    }
}
