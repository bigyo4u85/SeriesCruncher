package com.balazs_csernai.seriescruncher.showlist.ui;

import com.balazs_csernai.seriescruncher.model.ShowModel;
import com.balazs_csernai.seriescruncher.model.ShowsModel;

/**
 * Created by ErikKramli on 2016.01.17..
 */
public interface ShowListScreen {

    void onCreate(Callbacks callbacks);

    void displayProgressIndicator();

    void displaySeriesList(ShowsModel model);

    interface Callbacks {

        void onShowSelected(ShowModel show);
    }
}
