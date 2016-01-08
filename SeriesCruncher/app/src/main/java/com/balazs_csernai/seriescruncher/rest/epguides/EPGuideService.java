package com.balazs_csernai.seriescruncher.rest.epguides;

import com.balazs_csernai.seriescruncher.rest.epguides.model.Show;
import com.balazs_csernai.seriescruncher.rest.epguides.model.ShowShort;

import retrofit.Callback;

/**
 * Created by Balazs_Csernai on 2016.01.08..
 */
public interface EPGuideService {

    void loadShows(Callback<ShowShort[]> callback);

    void loadShow(String showName, Callback<Show> callback);
}
