package com.balazs_csernai.seriescruncher.preferences.component;

import com.balazs_csernai.seriescruncher.preferences.Preferences;
import com.balazs_csernai.seriescruncher.preferences.PreferencesImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Erik_Markus_Kramli on 2016-02-04.
 */
@Module
public class PreferencesModule {

    @Provides
    @Singleton
    Preferences providePreferences(PreferencesImpl impl) {
        return impl;
    }
}
