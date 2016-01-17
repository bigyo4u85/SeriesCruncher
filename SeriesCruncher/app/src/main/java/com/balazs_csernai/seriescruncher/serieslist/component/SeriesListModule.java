package com.balazs_csernai.seriescruncher.serieslist.component;

import com.balazs_csernai.seriescruncher.serieslist.controller.SeriesListController;
import com.balazs_csernai.seriescruncher.serieslist.controller.SeriesListControllerImpl;
import com.balazs_csernai.seriescruncher.serieslist.ui.SeriesListScreen;
import com.balazs_csernai.seriescruncher.serieslist.ui.SeriesListScreenImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ErikKramli on 2016.01.17..
 */
@Module
public class SeriesListModule {

    @Provides
    SeriesListScreen provideScreen(SeriesListScreenImpl impl) {
        return impl;
    }

    @Provides
    SeriesListController provideController(SeriesListControllerImpl impl) {
        return impl;
    }
}
