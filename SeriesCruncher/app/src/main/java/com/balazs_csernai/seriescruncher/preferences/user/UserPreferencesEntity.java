package com.balazs_csernai.seriescruncher.preferences.user;

import com.google.gson.annotations.Expose;

import java.util.Collections;
import java.util.List;

/**
 * Created by Erik_Markus_Kramli on 2016-02-04.
 */
public class UserPreferencesEntity implements UserPreferencesModel {

    @Expose
    private List<String> favoredSeries;

    public UserPreferencesEntity() {
        favoredSeries = Collections.emptyList();
    }

    @Override
    public List<String> getFavoredSeries() {
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
