package com.balazs_csernai.seriescruncher.details.component;

import com.balazs_csernai.seriescruncher.details.controller.DetailsController;
import com.balazs_csernai.seriescruncher.details.controller.DetailsControllerImpl;
import com.balazs_csernai.seriescruncher.details.ui.DetailsScreen;
import com.balazs_csernai.seriescruncher.details.ui.DetailsScreenImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Erik_Markus_Kramli on 2016-01-13.
 */
@Module
public class DetailsModule {

    @Provides
    DetailsController provideController(DetailsControllerImpl impl) {
        return impl;
    }

    @Provides
    DetailsScreen provideScreen(DetailsScreenImpl impl) {
        return impl;
    }
}
