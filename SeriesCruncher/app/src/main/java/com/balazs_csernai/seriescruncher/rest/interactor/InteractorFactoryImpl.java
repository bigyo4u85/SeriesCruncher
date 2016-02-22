package com.balazs_csernai.seriescruncher.rest.interactor;

import com.balazs_csernai.seriescruncher.seriesdetails.interactor.PosterInteractor;
import com.balazs_csernai.seriescruncher.seriesdetails.interactor.SeriesDetailsInteractor;
import com.balazs_csernai.seriescruncher.serieslist.interactor.SeriesListInteractor;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Created by ErikKramli on 2016.01.14..
 */
public class InteractorFactoryImpl implements InteractorFactory {

    private final Provider<SeriesListInteractor> seriesListInteractorProvider;
    private final Provider<SeriesDetailsInteractor> seriesDetailsInteractorProvider;
    private final Provider<PosterInteractor> posterInteractorProvider;

    @Inject
    public InteractorFactoryImpl(Provider<SeriesListInteractor> seriesListInteractorProvider,
                                 Provider<SeriesDetailsInteractor> seriesDetailsInteractorProvider,
                                 Provider<PosterInteractor> posterInteractorProvider) {
        this.seriesListInteractorProvider = seriesListInteractorProvider;
        this.seriesDetailsInteractorProvider = seriesDetailsInteractorProvider;
        this.posterInteractorProvider = posterInteractorProvider;
    }

    @Override
    public SeriesListInteractor createSeriesListInteractor() {
        return seriesListInteractorProvider.get();
    }

    @Override
    public SeriesDetailsInteractor createSeriesDetailsInteractor(String seriesName) {
        SeriesDetailsInteractor interactor = seriesDetailsInteractorProvider.get();
        interactor.setRequestParam(seriesName);
        return interactor;
    }

    @Override
    public PosterInteractor createPosterInteractor(String url) {
        PosterInteractor interactor = posterInteractorProvider.get();
        interactor.setRequestParam(url);
        return interactor;
    }
}
