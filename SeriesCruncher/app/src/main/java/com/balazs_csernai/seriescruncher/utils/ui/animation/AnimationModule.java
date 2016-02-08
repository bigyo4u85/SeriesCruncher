package com.balazs_csernai.seriescruncher.utils.ui.animation;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ErikKramli on 2016.02.06..
 */
@Module
public class AnimationModule {

    @Provides
    Animation provideAnimation(AnimationImpl impl) {
        return impl;
    }
}
