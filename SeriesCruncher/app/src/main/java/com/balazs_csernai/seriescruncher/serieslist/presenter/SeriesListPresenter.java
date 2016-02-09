package com.balazs_csernai.seriescruncher.serieslist.presenter;

import com.balazs_csernai.seriescruncher.utils.dialog.NetworkErrorDialogFragment;

/**
 * Created by ErikKramli on 2016.01.17..
 */
public interface SeriesListPresenter extends NetworkErrorDialogFragment.NetworkErrorDialogCallback {

    void onStart();

    void onStop();
}
