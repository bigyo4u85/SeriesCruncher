package com.balazs_csernai.seriescruncher.rest.loader;

import com.balazs_csernai.seriescruncher.rest.interactor.Interactor;

/**
 * Created by ErikKramli on 2016.01.15..
 */
public interface RequestLoader extends Loader {

    <MODEL, JSON extends MODEL> void perform(Interactor<JSON> interactor, final Callback<MODEL> callback);

    <MODEL, JSON extends MODEL> void perform(Interactor<JSON> interactor, String cacheKey, long cacheDuration, final Callback<MODEL> callback);

}
