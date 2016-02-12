package com.balazs_csernai.seriescruncher.notification;

/**
 * Created by Erik_Markus_Kramli on 2016-02-12.
 */
public interface FavoriteSeriesHandler {

    boolean isFavorite(String seriesName);

    boolean hasFavorites();

    void addToFavorites(String seriesName);

    void removeFromFavorites(String seriesName);
}
