package com.balazs_csernai.seriescruncher.preferences.component;

import com.balazs_csernai.seriescruncher.preferences.PreferenceHandler;
import com.balazs_csernai.seriescruncher.preferences.PreferenceHandlerImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Erik_Markus_Kramli on 2016-02-04.
 */
@Module
public class PreferenceModule {

    @Provides
    @Singleton
    PreferenceHandler providePreferenceHandler(PreferenceHandlerImpl impl) {
        return impl;
    }
}
