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

    public UserPreferencesEntity() {
        favoredSeries = new LinkedHashSet<>();
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
}
