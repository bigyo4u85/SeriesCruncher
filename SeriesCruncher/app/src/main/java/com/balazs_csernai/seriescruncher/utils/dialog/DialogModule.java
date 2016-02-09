package com.balazs_csernai.seriescruncher.utils.dialog;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Balazs_Csernai on 2016.02.08..
 */
@Module
public class DialogModule {

    @Provides
    DialogFactory provideDialogFactory(DialogFactoryImpl impl) {
        return impl;
    }
}
