package com.balazs_csernai.seriescruncher.app.component;

import android.app.Application;
import android.content.res.Resources;

import com.balazs_csernai.seriescruncher.app.SeriesCruncherApplication;
import com.balazs_csernai.seriescruncher.preferences.Preferences;
import com.balazs_csernai.seriescruncher.preferences.component.PreferencesModule;
import com.balazs_csernai.seriescruncher.rest.component.ApiModule;
import com.balazs_csernai.seriescruncher.rest.component.NetworkingComponent;
import com.balazs_csernai.seriescruncher.rest.component.RestModule;
import com.balazs_csernai.seriescruncher.utils.alarm.AlarmHandler;
import com.balazs_csernai.seriescruncher.utils.alarm.AlarmModule;
import com.balazs_csernai.seriescruncher.utils.converter.ConverterModule;
import com.balazs_csernai.seriescruncher.utils.image.ImageModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Balazs_Csernai on 2016.01.08..
 */
@Singleton
@Component(
        modules = {
                ApplicationModule.class,
                AlarmModule.class,
                PreferencesModule.class
        }
)
public interface ApplicationComponent {

    void inject(SeriesCruncherApplication application);

    NetworkingComponent networkingComponent(ApiModule apiModule, RestModule restModule, ImageModule imageModule, ConverterModule converterModule);

    Application application();

    Resources resources();

    AlarmHandler alarmHandler();

    Preferences preferences();

    final class Injector {
        private static ApplicationComponent appComponent;
        private static NetworkingComponent netComponent;

        private Injector() {
        }

        public static void inject(SeriesCruncherApplication application) {
            appComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(application))
                    .alarmModule(new AlarmModule())
                    .preferencesModule(new PreferencesModule())
                    .build();

            netComponent = appComponent.networkingComponent(
                    new ApiModule(),
                    new RestModule(),
                    new ImageModule(),
                    new ConverterModule()
            );

            appComponent.inject(application);
        }

        public static ApplicationComponent appComponent() {
            return appComponent;
        }

        public static NetworkingComponent netComponent() {
            return netComponent;
        }
    }
}
