package com.balazs_csernai.seriescruncher.serieslist.component;

import com.balazs_csernai.seriescruncher.app.component.ActivityScope;
import com.balazs_csernai.seriescruncher.app.component.ApplicationComponent;
import com.balazs_csernai.seriescruncher.app.component.CommonActivityModule;
import com.balazs_csernai.seriescruncher.rest.component.RestModule;
import com.balazs_csernai.seriescruncher.serieslist.SeriesListActivity;

import dagger.Component;

/**
 * Created by Balazs_Csernai on 2016.01.08..
 */
@ActivityScope
@Component(
        dependencies = ApplicationComponent.class,
        modules = {
                CommonActivityModule.class,
                RestModule.class,
                SeriesListModule.class
        }
)
public interface SeriesListComponent {

    void inject(SeriesListActivity activity);

    final class Injector {
        public static void inject(SeriesListActivity activity) {
            DaggerSeriesListComponent.builder()
                    .applicationComponent(ApplicationComponent.Injector.component())
                    .commonActivityModule(new CommonActivityModule(activity))
                    .restModule(new RestModule())
                    .seriesListModule(new SeriesListModule())
                    .build()
                    .inject(activity);
        }
    }
}
