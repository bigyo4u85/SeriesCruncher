package com.balazs_csernai.seriescruncher.rest;

import android.app.Application;

import com.balazs_csernai.seriescruncher.rest.component.NetworkingComponent;
import com.octo.android.robospice.SpiceService;
import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.persistence.exception.CacheCreationException;
import com.octo.android.robospice.persistence.retrofit.GsonRetrofitObjectPersisterFactory;

import java.io.File;

import javax.inject.Inject;

import retrofit.converter.Converter;

/**
 * Created by ErikKramli on 2016.01.09..
 */
public class NetworkingService extends SpiceService {

    @Inject
    Converter converter;

    @Override
    public void onCreate() {
        NetworkingComponent.Injector.inject(this);
        super.onCreate();
    }

    @Override
    public CacheManager createCacheManager(Application application) throws CacheCreationException {
        CacheManager cacheManager = new CacheManager();
        cacheManager.addPersister(new GsonRetrofitObjectPersisterFactory(application, converter, getCacheFolder()));
        return cacheManager;
    }

    public File getCacheFolder() {
        return null;
    }
}
