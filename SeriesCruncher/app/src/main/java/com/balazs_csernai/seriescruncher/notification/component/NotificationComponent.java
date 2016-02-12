package com.balazs_csernai.seriescruncher.notification.component;

import com.balazs_csernai.seriescruncher.app.component.ActivityScope;
import com.balazs_csernai.seriescruncher.app.component.ApplicationComponent;
import com.balazs_csernai.seriescruncher.notification.NotificationService;
import com.balazs_csernai.seriescruncher.rest.component.RestModule;

import dagger.Component;

/**
 * Created by Erik_Markus_Kramli on 2016-02-12.
 */
@ActivityScope
@Component(
        dependencies = ApplicationComponent.class,
        modules = {
                NotificationModule.class,
                RestModule.class
        }
)
public interface NotificationComponent {

    void inject(NotificationService service);

    final class Injector {

        private Injector() {}

        public static void inject(NotificationService service) {

        }
    }
}
