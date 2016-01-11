package com.balazs_csernai.seriescruncher.rest.component;

import com.balazs_csernai.seriescruncher.app.component.ActivityScope;
import com.balazs_csernai.seriescruncher.app.component.ApplicationComponent;
import com.balazs_csernai.seriescruncher.rest.NetworkingService;

import dagger.Component;

/**
 * Created by ErikKramli on 2016.01.11..
 */
@ActivityScope
@Component(
        dependencies = ApplicationComponent.class
)
public interface NetworkingComponent {

    void inject(NetworkingService service);

    final class Injector {

        public static void inject(NetworkingService service) {
            DaggerNetworkingComponent.builder()
                    .applicationComponent(ApplicationComponent.Injector.component())
                    .build()
                    .inject(service);
        }
    }
}
