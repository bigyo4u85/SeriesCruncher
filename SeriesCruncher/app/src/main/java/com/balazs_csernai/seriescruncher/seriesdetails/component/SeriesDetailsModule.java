package com.balazs_csernai.seriescruncher.seriesdetails.component;

import com.balazs_csernai.seriescruncher.seriesdetails.model.favorit.FavoriteSeriesHandler;
import com.balazs_csernai.seriescruncher.seriesdetails.model.favorit.FavoriteSeriesHandlerImpl;
import com.balazs_csernai.seriescruncher.seriesdetails.model.finder.EpisodeFinder;
import com.balazs_csernai.seriescruncher.seriesdetails.model.finder.EpisodeFinderImpl;
import com.balazs_csernai.seriescruncher.seriesdetails.presenter.SeriesDetailsPresenter;
import com.balazs_csernai.seriescruncher.seriesdetails.presenter.SeriesDetailsPresenterImpl;
import com.balazs_csernai.seriescruncher.seriesdetails.ui.SeriesDetailsScreen;
import com.balazs_csernai.seriescruncher.seriesdetails.ui.SeriesDetailsScreenImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Erik_Markus_Kramli on 2016-01-13.
 */
@Module
public class SeriesDetailsModule {

    @Provides
    SeriesDetailsPresenter providePresenter(SeriesDetailsPresenterImpl impl) {
        return impl;
    }

    @Provides
    SeriesDetailsScreen provideScreen(SeriesDetailsScreenImpl impl) {
        return impl;
    }

    @Provides
    EpisodeFinder provideEpisodeFinder(EpisodeFinderImpl impl) {
        return impl;
    }

    @Provides
    FavoriteSeriesHandler provideFavoriteHandler(FavoriteSeriesHandlerImpl impl) {
        return impl;
    }
}
