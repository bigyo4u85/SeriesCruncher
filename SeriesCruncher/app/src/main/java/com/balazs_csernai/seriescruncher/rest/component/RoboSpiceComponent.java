package com.balazs_csernai.seriescruncher.rest.component;

import com.balazs_csernai.seriescruncher.app.component.ApplicationComponent;
import com.balazs_csernai.seriescruncher.app.component.ServiceScope;
import com.balazs_csernai.seriescruncher.rest.loader.RoboSpiceService;

import dagger.Component;

/**
 * Created by ErikKramli on 2016.01.11..
 */
@ServiceScope
@Component(
        dependencies = NetworkingComponent.class
)
public interface RoboSpiceComponent {

    void inject(RoboSpiceService service);

    final class Injector {

        public static void inject(RoboSpiceService service) {
            DaggerRoboSpiceComponent.builder()
                    .networkingComponent(ApplicationComponent.Injector.netComponent())
                    .build()
                    .inject(service);
        }
    }
}
