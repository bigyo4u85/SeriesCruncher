package com.balazs_csernai.seriescruncher.preferences.user;

import java.util.Set;

/**
 * Created by Erik_Markus_Kramli on 2016-02-04.
 */
public interface UserPreferencesModel {

    Set<String> getFavoredSeries();

    void addSeriesToFavorites(String seriesName);

    void removeSeriesFromFavorites(String seriesName);

    void clearFavorites();

    boolean areNotificationsEnabled();

    void setNotificationsEnabled(boolean enabled);

    int getNotificationHour();

    int getNotificationMinute();

    void setNotificationTime(int hour, int minute);
}
