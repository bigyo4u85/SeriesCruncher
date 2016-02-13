package com.balazs_csernai.seriescruncher.notification.component;

import com.balazs_csernai.seriescruncher.app.component.ActivityScope;
import com.balazs_csernai.seriescruncher.app.component.ApplicationComponent;
import com.balazs_csernai.seriescruncher.app.component.CommonServiceModule;
import com.balazs_csernai.seriescruncher.notification.NotificationService;
import com.balazs_csernai.seriescruncher.rest.component.RestModule;
import com.balazs_csernai.seriescruncher.utils.converter.ConverterModule;
import com.balazs_csernai.seriescruncher.utils.image.ImageModule;

import dagger.Component;

/**
 * Created by Erik_Markus_Kramli on 2016-02-12.
 */
@ActivityScope
@Component(
        dependencies = ApplicationComponent.class,
        modules = {
                CommonServiceModule.class,
                NotificationModule.class,
                RestModule.class,
                ConverterModule.class,
                ImageModule.class // todo remove, its unused in this scope
        }
)
public interface NotificationComponent {

    void inject(NotificationService service);

    final class Injector {

        private Injector() {}

        public static void inject(NotificationService service) {
            DaggerNotificationComponent.builder()
                    .applicationComponent(ApplicationComponent.Injector.component())
                    .commonServiceModule(new CommonServiceModule(service))
                    .notificationModule(new NotificationModule())
                    .restModule(new RestModule())
                    .converterModule(new ConverterModule())
                    .build()
                    .inject(service);
        }
    }
}
