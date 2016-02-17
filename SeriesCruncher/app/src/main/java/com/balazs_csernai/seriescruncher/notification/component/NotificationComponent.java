package com.balazs_csernai.seriescruncher.notification.component;

import com.balazs_csernai.seriescruncher.app.component.ApplicationComponent;
import com.balazs_csernai.seriescruncher.app.component.ServiceScope;
import com.balazs_csernai.seriescruncher.notification.NotificationService;
import com.balazs_csernai.seriescruncher.rest.component.NetworkingComponent;

import dagger.Component;

/**
 * Created by Erik_Markus_Kramli on 2016-02-12.
 */
@ServiceScope
@Component(
        dependencies = NetworkingComponent.class,
        modules = {
                NotificationModule.class
        }
)
public interface NotificationComponent {

    void inject(NotificationService service);

    final class Injector {

        private Injector() {}

        public static void inject(NotificationService service) {
            DaggerNotificationComponent.builder()
                    .networkingComponent(ApplicationComponent.Injector.netComponent())
                    .notificationModule(new NotificationModule())
                    .build()
                    .inject(service);
        }
    }
}
