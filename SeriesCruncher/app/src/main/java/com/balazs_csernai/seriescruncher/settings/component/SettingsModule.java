package com.balazs_csernai.seriescruncher.settings.component;

import com.balazs_csernai.seriescruncher.settings.presenter.SettingsPresenter;
import com.balazs_csernai.seriescruncher.settings.presenter.SettingsPresenterImpl;
import com.balazs_csernai.seriescruncher.settings.ui.SettingsScreen;
import com.balazs_csernai.seriescruncher.settings.ui.SettingsScreenImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by erik_markus_kramli on 2016-02-11.
 */
@Module
public class SettingsModule {

    @Provides
    SettingsPresenter providePresenter(SettingsPresenterImpl impl) {
        return impl;
    }

    @Provides
    SettingsScreen providScreen(SettingsScreenImpl impl) {
        return impl;
    }
}
