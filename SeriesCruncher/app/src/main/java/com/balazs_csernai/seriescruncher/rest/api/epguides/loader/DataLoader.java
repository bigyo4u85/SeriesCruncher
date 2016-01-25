package com.balazs_csernai.seriescruncher.rest.api.epguides.loader;

import com.balazs_csernai.seriescruncher.details.model.ShowModel;
import com.balazs_csernai.seriescruncher.rest.loader.Loader;
import com.balazs_csernai.seriescruncher.model.ShowsModel;

/**
 * Created by Balazs_Csernai on 2016.01.08..
 */
public interface DataLoader extends Loader {

    void loadShows(Callback<ShowsModel> callback);

    void loadShow(String showName, String imdbId, Callback<ShowModel> callback);
}
