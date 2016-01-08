package com.balazs_csernai.seriescruncher.app.component;

import android.app.Application;

import com.balazs_csernai.seriescruncher.app.SeriesCruncherApplication;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Balazs_Csernai on 2016.01.08..
 */
@Singleton
@Component(
        modules = {SeriesCruncherApplicationModule.class})
public interface SeriesCruncherApplicationComponent {
    void inject(SeriesCruncherApplication application);

    Application application();

    final class Injector {
        private static SeriesCruncherApplicationComponent component;

        private Injector() {
        }

        public static void inject(SeriesCruncherApplication application) {
            component = DaggerSeriesCruncherApplicationComponent.builder()
                    .seriesCruncherApplicationModule(new SeriesCruncherApplicationModule(application))
                    .build();

            component.inject(application);
        }

        public static SeriesCruncherApplicationComponent component() {
            return component;
        }
    }
}
