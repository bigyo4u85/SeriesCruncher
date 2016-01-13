package com.balazs_csernai.seriescruncher.rest;

import com.balazs_csernai.seriescruncher.details.model.ShowDetailsModel;
import com.balazs_csernai.seriescruncher.rest.api.epguides.model.ShowShortList;

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

    void loadShows(Callback<ShowShortList> callback);

    void loadDetails(String showName, Callback<ShowDetailsModel> callback);
}
