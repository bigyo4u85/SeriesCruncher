package com.balazs_csernai.seriescruncher.app.component;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Balazs_Csernai on 2016.01.08..
 */
@Module
public class CommonActivityModule {

    private final Activity activity;

    public CommonActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    Activity provideActivity() {
        return activity;
    }

    @Provides
    Context provideContext() {
        return activity;
    }
}
