package com.balazs_csernai.seriescruncher.utils.ui.animation;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ErikKramli on 2016.02.06..
 */
@Module
public class AnimationModule {

    @Provides
    @Singleton
    Animation provideAnimation(AnimationImpl impl) {
        return impl;
    }
}
