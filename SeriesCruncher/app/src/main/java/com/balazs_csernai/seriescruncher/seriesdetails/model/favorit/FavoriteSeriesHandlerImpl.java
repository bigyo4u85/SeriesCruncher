package com.balazs_csernai.seriescruncher.seriesdetails.model.favorit;

import com.balazs_csernai.seriescruncher.preferences.Preferences;
import com.balazs_csernai.seriescruncher.preferences.user.UserPreferencesModel;
import com.balazs_csernai.seriescruncher.utils.alarm.AlarmHandler;

import javax.inject.Inject;

/**
 * Created by Erik_Markus_Kramli on 2016-02-12.
 */
public class FavoriteSeriesHandlerImpl implements FavoriteSeriesHandler {

    private final Preferences preferences;
    private final AlarmHandler alarmHandler;

    @Inject
    public FavoriteSeriesHandlerImpl(Preferences preferences, AlarmHandler alarmHandler) {
        this.preferences = preferences;
        this.alarmHandler = alarmHandler;
    }

    @Override
    public boolean isFavorite(String seriesName) {
        return preferences.getUserPreferences().getFavoredSeries().contains(seriesName);
    }

    @Override
    public boolean hasFavorites() {
        return !preferences.getUserPreferences().getFavoredSeries().isEmpty();
    }

    @Override
    public void addToFavorites(String seriesName) {
        UserPreferencesModel userPreferences = preferences.getUserPreferences();
        userPreferences.addSeriesToFavorites(seriesName);
        preferences.updateUserPreferences(userPreferences);

        handleNotificationAlarm();
    }

    @Override
    public void removeFromFavorites(String seriesName) {
        UserPreferencesModel userPreferences = preferences.getUserPreferences();
        userPreferences.removeSeriesFromFavorites(seriesName);
        preferences.updateUserPreferences(userPreferences);

        handleNotificationAlarm();
    }

    private void handleNotificationAlarm() {
        UserPreferencesModel userPreferences = preferences.getUserPreferences();
        if (userPreferences.areNotificationsEnabled()) {
            alarmHandler.cancelDailyNotificationAlarm();
            if (hasFavorites()) {
                alarmHandler.registerDailyNotificationAlarm(userPreferences.getNotificationHour(), userPreferences.getNotificationMinute());
            }
        }
    }
}
