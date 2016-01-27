package com.balazs_csernai.seriescruncher.seriesdetails.presenter;

/**
 * Created by Erik_Markus_Kramli on 2016-01-13.
 */
public interface SeriesDetailsPresenter {

    void onStart();

    void loadShowDetails(String showName, String imdbId);

    void onStop();
}
