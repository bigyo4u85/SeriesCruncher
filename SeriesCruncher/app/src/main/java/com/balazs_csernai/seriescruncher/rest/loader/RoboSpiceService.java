package com.balazs_csernai.seriescruncher.rest.loader;

import android.app.Application;

import com.balazs_csernai.seriescruncher.rest.component.RoboSpiceComponent;
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
public class RoboSpiceService extends SpiceService {

    @Inject
    Converter converter;

    @Override
    public void onCreate() {
        RoboSpiceComponent.Injector.inject(this);
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
