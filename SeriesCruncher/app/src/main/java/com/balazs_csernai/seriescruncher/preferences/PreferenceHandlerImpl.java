package com.balazs_csernai.seriescruncher.preferences;

import android.content.SharedPreferences;

import com.balazs_csernai.seriescruncher.preferences.user.UserPreferencesModel;
import com.balazs_csernai.seriescruncher.preferences.user.UserPreferencesEntity;
import com.google.gson.Gson;

import javax.inject.Inject;

/**
 * Created by Erik_Markus_Kramli on 2016-02-04.
 */
public class PreferenceHandlerImpl implements PreferenceHandler {

    private static final String KEY_USER_PREFERENCES = "user";

    private final SharedPreferences prefs;
    private final Gson gson;

    @Inject
    public PreferenceHandlerImpl(SharedPreferences prefs, Gson gson) {
        this.prefs = prefs;
        this.gson = gson;
    }

    @Override
    public UserPreferencesModel getUserPreferences() {
        String serializedUserPrefs = prefs.getString(KEY_USER_PREFERENCES, null);
        UserPreferencesModel userPreferences = gson.fromJson(serializedUserPrefs, UserPreferencesEntity.class);
        if (userPreferences == null) {
            userPreferences = new UserPreferencesEntity();
        }
        return userPreferences;
    }

    @Override
    public void updateUserPreferences(UserPreferencesModel userPreferences) {
        prefs.edit().putString(KEY_USER_PREFERENCES, gson.toJson(userPreferences)).apply();
    }
}
