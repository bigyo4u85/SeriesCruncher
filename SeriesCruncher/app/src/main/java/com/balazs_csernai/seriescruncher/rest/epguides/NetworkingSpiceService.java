package com.balazs_csernai.seriescruncher.rest.epguides;

import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;

/**
 * Created by ErikKramli on 2016.01.09..
 */
public class NetworkingSpiceService extends RetrofitGsonSpiceService {

    @Override
    protected String getServerUrl() {
        return "https://epguides.frecar.no";
    }
}
