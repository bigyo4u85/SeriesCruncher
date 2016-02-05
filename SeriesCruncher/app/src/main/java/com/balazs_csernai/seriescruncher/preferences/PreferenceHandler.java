package com.balazs_csernai.seriescruncher.preferences;

import com.balazs_csernai.seriescruncher.preferences.user.UserPreferencesModel;

/**
 * Created by Erik_Markus_Kramli on 2016-02-04.
 */
public interface PreferenceHandler {

    UserPreferencesModel getUserPreferences();

    void updateUserPreferences(UserPreferencesModel userPreferences);
}
