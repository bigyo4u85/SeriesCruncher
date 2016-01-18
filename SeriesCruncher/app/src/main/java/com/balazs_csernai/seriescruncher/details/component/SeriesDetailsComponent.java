package com.balazs_csernai.seriescruncher.details.component;

import com.balazs_csernai.seriescruncher.app.component.ActivityScope;
import com.balazs_csernai.seriescruncher.app.component.ApplicationComponent;
import com.balazs_csernai.seriescruncher.app.component.CommonActivityModule;
import com.balazs_csernai.seriescruncher.details.SeriesDetailsActivity;
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
                SeriesDetailsModule.class
        }
)
public interface SeriesDetailsComponent {
    void inject(SeriesDetailsActivity activity);

    final class Injector {

        private Injector() {}

        public static void inject(SeriesDetailsActivity activity) {
            DaggerSeriesDetailsComponent.builder()
                    .applicationComponent(ApplicationComponent.Injector.component())
                    .commonActivityModule(new CommonActivityModule(activity))
                    .restModule(new RestModule())
                    .seriesDetailsModule(new SeriesDetailsModule())
                    .build()
                    .inject(activity);
        }
    }
}
