package com.balazs_csernai.seriescruncher.utils.alarm;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Erik_Markus_Kramli on 2016-02-12.
 */
@Module
public class AlarmModule {

    @Provides
    @Singleton
    AlarmHandler provideAlarmHandler(AlarmHandlerImpl impl) {
        return impl;
    }
}
