package com.balazs_csernai.seriescruncher.details.component;

import com.balazs_csernai.seriescruncher.app.component.ActivityScope;
import com.balazs_csernai.seriescruncher.app.component.ApplicationComponent;
import com.balazs_csernai.seriescruncher.app.component.CommonActivityModule;
import com.balazs_csernai.seriescruncher.details.ShowDetailsActivity;
import com.balazs_csernai.seriescruncher.rest.component.RestModule;

import dagger.Component;

/**
 * Created by ErikKramli on 2016.01.10..
 */
@ActivityScope
@Component(
        dependencies = ApplicationComponent.class,
        modules = {
                CommonActivityModule.class,
                RestModule.class,
                DetailsModule.class
        }
)
public interface DetailsComponent {
    void inject(ShowDetailsActivity activity);

    final class Injector {

        private Injector() {}

        public static void inject(ShowDetailsActivity activity) {
            DaggerDetailsComponent.builder()
                    .applicationComponent(ApplicationComponent.Injector.component())
                    .commonActivityModule(new CommonActivityModule(activity))
                    .restModule(new RestModule())
                    .detailsModule(new DetailsModule())
                    .build()
                    .inject(activity);
        }
    }
}
