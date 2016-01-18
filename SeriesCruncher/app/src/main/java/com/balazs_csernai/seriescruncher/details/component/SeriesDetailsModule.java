package com.balazs_csernai.seriescruncher.details.component;

import com.balazs_csernai.seriescruncher.details.presenter.SeriesDetailsPresenter;
import com.balazs_csernai.seriescruncher.details.presenter.SeriesDetailsPresenterImpl;
import com.balazs_csernai.seriescruncher.details.ui.SeriesDetailsScreen;
import com.balazs_csernai.seriescruncher.details.ui.SeriesDetailsScreenImpl;

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
}
