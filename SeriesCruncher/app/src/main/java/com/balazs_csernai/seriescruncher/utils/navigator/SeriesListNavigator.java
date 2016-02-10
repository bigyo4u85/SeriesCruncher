package com.balazs_csernai.seriescruncher.utils.navigator;

/**
 * Created by ErikKramli on 2016.01.17..
 */
public interface SeriesListNavigator {

    void launchSeriesDetails(String seriesName, String imdbId);

    void launchSettings();

    void closeSeriesList();
}
