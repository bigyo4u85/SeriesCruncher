package com.balazs_csernai.seriescruncher.settings.component;

import com.balazs_csernai.seriescruncher.app.component.ActivityScope;
import com.balazs_csernai.seriescruncher.app.component.ApplicationComponent;
import com.balazs_csernai.seriescruncher.app.component.CommonActivityModule;
import com.balazs_csernai.seriescruncher.settings.SettingsActivity;
import com.balazs_csernai.seriescruncher.utils.dialog.DialogModule;
import com.balazs_csernai.seriescruncher.utils.navigator.NavigatorModule;

import dagger.Component;

/**
 * Created by erik_markus_kramli on 2016-02-11.
 */
@ActivityScope
@Component(
        dependencies = ApplicationComponent.class,
        modules = {
                CommonActivityModule.class,
                SettingsModule.class,
                NavigatorModule.class,
                DialogModule.class
        }
)
public interface SettingsComponent {

    void inject(SettingsActivity activity);

    final class Injector {

        private Injector() {}

        public static void inject(SettingsActivity activity) {
            DaggerSettingsComponent.builder()
                    .applicationComponent(ApplicationComponent.Injector.appComponent())
                    .commonActivityModule(new CommonActivityModule(activity))
                    .settingsModule(new SettingsModule())
                    .navigatorModule(new NavigatorModule())
                    .dialogModule(new DialogModule())
                    .build()
                    .inject(activity);
        }
    }
}
