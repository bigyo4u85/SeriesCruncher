package com.balazs_csernai.seriescruncher.rest.epguides;

import com.balazs_csernai.seriescruncher.rest.epguides.model.Show;
import com.balazs_csernai.seriescruncher.rest.epguides.model.ShowList;

/**
 * Created by Balazs_Csernai on 2016.01.08..
 */
public interface EPGuideService {

    interface Callback<T> {
        void onSuccess(T result);
        void onFailure();
    }

    void bind();

    void unbind();

    void loadShows(Callback<ShowList> callback);

    void loadShow(String showName, Callback<Show> callback);
}
