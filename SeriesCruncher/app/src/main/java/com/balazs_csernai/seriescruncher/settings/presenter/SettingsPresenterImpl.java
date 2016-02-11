package com.balazs_csernai.seriescruncher.settings.presenter;

import com.balazs_csernai.seriescruncher.preferences.Preferences;
import com.balazs_csernai.seriescruncher.preferences.user.UserPreferencesModel;
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

        UserPreferencesModel userPreferences = preferences.getUserPreferences();
        screen.setPreferenceValues(userPreferences.areNotificationsEnabled(), 12, 0);
    }

    @Override
    public void onNotificationCheckChanged(boolean isChecked) {

    }

    @Override
    public void onNotificationTimeTapped() {
        screen.showTimePickerDialog();
    }

    @Override
    public void onTimePicked(int hour, int minute) {

    }
}
