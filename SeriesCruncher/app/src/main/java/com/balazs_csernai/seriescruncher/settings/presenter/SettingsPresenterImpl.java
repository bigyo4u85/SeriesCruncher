package com.balazs_csernai.seriescruncher.settings.presenter;

import com.balazs_csernai.seriescruncher.preferences.Preferences;
import com.balazs_csernai.seriescruncher.settings.ui.SettingsScreen;

import javax.inject.Inject;

/**
 * Created by erik_markus_kramli on 2016-02-11.
 */
public class SettingsPresenterImpl implements SettingsPresenter, SettingsScreen.Callbacks {

    private final SettingsScreen screen;
    private final Preferences preferences;

    @Inject
    public SettingsPresenterImpl(SettingsScreen screen, Preferences preferences) {
        this.screen = screen;
        this.preferences = preferences;
    }

    @Override
    public void onCreate() {
        screen.onCreate(this);
    }



    @Override
    public void onNotificationsTapped(boolean enabled) {

    }

    @Override
    public void onTimePicked() {

    }
}
