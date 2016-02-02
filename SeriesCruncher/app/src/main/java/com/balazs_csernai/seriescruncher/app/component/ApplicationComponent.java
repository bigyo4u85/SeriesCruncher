package com.balazs_csernai.seriescruncher.app.component;

import android.app.Application;
import android.content.res.Resources;

import com.balazs_csernai.seriescruncher.app.SeriesCruncherApplication;
import com.balazs_csernai.seriescruncher.rest.api.omdb.OmdbApi;
import com.balazs_csernai.seriescruncher.rest.api.epguides.EPGuideApi;
import com.balazs_csernai.seriescruncher.rest.component.ApiModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit.converter.Converter;

/**
 * Created by Balazs_Csernai on 2016.01.08..
 */
@Singleton
@Component(
        modules = {
                ApplicationModule.class,
                ApiModule.class
        }
)
public interface ApplicationComponent {
    void inject(SeriesCruncherApplication application);

    Application application();

    Resources resources();

    Converter converter();

    EPGuideApi epGuideApi();

    OmdbApi omdbApi();

    final class Injector {
        private static ApplicationComponent component;

        private Injector() {
        }

        public static void inject(SeriesCruncherApplication application) {
            component = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(application))
                    .apiModule(new ApiModule())
                    .build();

            component.inject(application);
        }

        public static ApplicationComponent component() {
            return component;
        }
    }
}
