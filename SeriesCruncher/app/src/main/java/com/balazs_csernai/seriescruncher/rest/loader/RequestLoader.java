package com.balazs_csernai.seriescruncher.rest.loader;

import com.octo.android.robospice.request.CachedSpiceRequest;
import com.octo.android.robospice.request.SpiceRequest;

/**
 * Created by ErikKramli on 2016.01.15..
 */
public interface RequestLoader extends Loader {

    <MODEL, JSON extends MODEL> void perform(SpiceRequest<JSON> request, Callback<MODEL> callback);

    <MODEL, JSON extends MODEL> void perform(CachedSpiceRequest<JSON> request, Callback<MODEL> callback);

}
