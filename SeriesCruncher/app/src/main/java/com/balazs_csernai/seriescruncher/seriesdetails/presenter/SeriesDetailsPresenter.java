package com.balazs_csernai.seriescruncher.seriesdetails.presenter;

import com.balazs_csernai.seriescruncher.utils.dialog.NetworkErrorDialogFragment;

/**
 * Created by Erik_Markus_Kramli on 2016-01-13.
 */
public interface SeriesDetailsPresenter extends NetworkErrorDialogFragment.NetworkErrorDialogCallback {

    void onStart();

    void loadSeriesDetails(String seriesName, String imdbId);

    void onStop();
}
