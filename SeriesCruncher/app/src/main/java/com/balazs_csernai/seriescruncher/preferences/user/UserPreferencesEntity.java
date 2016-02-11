package com.balazs_csernai.seriescruncher.preferences.user;

import com.google.gson.annotations.Expose;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Erik_Markus_Kramli on 2016-02-04.
 */
public class UserPreferencesEntity implements UserPreferencesModel {

    @Expose
    private Set<String> favoredSeries;

    @Expose
    private boolean areNotificationsEnabled;

    @Expose
    private int notificationHour;

    @Expose
    private int notificationMinute;

    public UserPreferencesEntity() {
        favoredSeries = new LinkedHashSet<>();
        areNotificationsEnabled = true;
        notificationHour = 12;
        notificationMinute = 0;
    }

    @Override
    public Set<String> getFavoredSeries() {
        return favoredSeries;
    }

    @Override
    public void addSeriesToFavorites(String seriesName) {
        favoredSeries.add(seriesName);
    }

    @Override
    public void removeSeriesFromFavorites(String seriesName) {
        favoredSeries.remove(seriesName);
    }

    @Override
    public void clearFavorites() {
        favoredSeries.clear();
    }

    @Override
    public boolean areNotificationsEnabled() {
        return areNotificationsEnabled;
    }

    @Override
    public void setNotificationsEnabled(boolean enabled) {
        areNotificationsEnabled = enabled;
    }

    @Override
    public int getNotificationHour() {
        return notificationHour;
    }

    @Override
    public int getNotificationMinute() {
        return notificationMinute;
    }

    @Override
    public void setNotificationTime(int hour, int minute) {
        notificationHour = hour;
        notificationMinute = minute;
    }
}
