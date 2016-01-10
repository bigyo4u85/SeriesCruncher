package com.balazs_csernai.seriescruncher.rest.request;

import com.balazs_csernai.seriescruncher.rest.epguides.EPGuideApi;
import com.balazs_csernai.seriescruncher.rest.epguides.model.ShowList;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

/**
 * Created by ErikKramli on 2016.01.10..
 */
public class AllShowRequest extends RetrofitSpiceRequest<ShowList, EPGuideApi> {

    public AllShowRequest() {
        super(ShowList.class, EPGuideApi.class);
    }

    @Override
    public ShowList loadDataFromNetwork() throws Exception {
        return getService().loadShows();
    }
}
