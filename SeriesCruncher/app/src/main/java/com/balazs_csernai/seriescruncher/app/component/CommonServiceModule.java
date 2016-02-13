package com.balazs_csernai.seriescruncher.app.component;

import android.app.Service;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ErikKramli on 2016.02.12..
 */
@Module
public class CommonServiceModule {

    private final Service service;

    public CommonServiceModule(Service service) {
        this.service = service;
    }

    @Provides
    Context provideContext() {
        return service;
    }
}
