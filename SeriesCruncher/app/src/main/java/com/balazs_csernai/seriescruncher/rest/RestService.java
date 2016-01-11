package com.balazs_csernai.seriescruncher.rest;

import com.balazs_csernai.seriescruncher.rest.api.epguides.model.Show;
import com.balazs_csernai.seriescruncher.rest.api.epguides.model.ShowList;

/**
 * Created by Balazs_Csernai on 2016.01.08..
 */
public interface RestService {

    interface Callback<T> {
        void onSuccess(T result);
        void onFailure();
    }

    void bind();

    void unbind();

    void loadShows(Callback<ShowList> callback);

    void loadShow(String showName, Callback<Show> callback);
}
