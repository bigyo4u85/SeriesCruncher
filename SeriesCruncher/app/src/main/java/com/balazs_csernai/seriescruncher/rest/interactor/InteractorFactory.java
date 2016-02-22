package com.balazs_csernai.seriescruncher.rest.interactor;

import com.balazs_csernai.seriescruncher.seriesdetails.interactor.PosterInteractor;
import com.balazs_csernai.seriescruncher.seriesdetails.interactor.SeriesDetailsInteractor;
import com.balazs_csernai.seriescruncher.serieslist.interactor.SeriesListInteractor;

/**
 * Created by ErikKramli on 2016.01.14..
 */
public interface InteractorFactory {

    SeriesListInteractor createSeriesListInteractor();

    SeriesDetailsInteractor createSeriesDetailsInteractor(String seriesName);

    PosterInteractor createPosterInteractor(String url);

}
