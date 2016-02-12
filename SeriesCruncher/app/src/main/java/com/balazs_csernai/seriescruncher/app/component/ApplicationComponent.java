package com.balazs_csernai.seriescruncher.app.component;

import android.app.Application;
import android.content.res.Resources;
import android.view.inputmethod.InputMethodManager;

import com.balazs_csernai.seriescruncher.app.SeriesCruncherApplication;
import com.balazs_csernai.seriescruncher.preferences.Preferences;
import com.balazs_csernai.seriescruncher.preferences.component.PreferencesModule;
import com.balazs_csernai.seriescruncher.rest.api.epguides.EPGuideApi;
import com.balazs_csernai.seriescruncher.rest.api.omdb.OmdbApi;
import com.balazs_csernai.seriescruncher.rest.component.ApiModule;
import com.balazs_csernai.seriescruncher.utils.alarm.AlarmHandler;
import com.balazs_csernai.seriescruncher.utils.alarm.AlarmModule;

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
                ApiModule.class,
                AlarmModule.class,
                PreferencesModule.class
        }
)
public interface ApplicationComponent {

    void inject(SeriesCruncherApplication application);

    Application application();
    Resources resources();
    InputMethodManager inputMethodManager();
    Converter converter();
    EPGuideApi epGuideApi();
    OmdbApi omdbApi();
    AlarmHandler alarmHandler();
    Preferences preferences();

    final class Injector {
        private static ApplicationComponent component;

        private Injector() {
        }

        public static void inject(SeriesCruncherApplication application) {
            component = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(application))
                    .apiModule(new ApiModule())
                    .alarmModule(new AlarmModule())
                    .preferencesModule(new PreferencesModule())
                    .build();

            component.inject(application);
        }

        public static ApplicationComponent component() {
            return component;
        }
    }
}
