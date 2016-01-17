package com.balazs_csernai.seriescruncher.app;

import android.app.Application;

import static com.balazs_csernai.seriescruncher.app.component.ApplicationComponent.Injector.inject;

/**
 * Created by Balazs_Csernai on 2016.01.08..
 */
public class SeriesCruncherApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        inject(this);
    }
}
