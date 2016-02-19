package com.balazs_csernai.seriescruncher.serieslist.component;

import com.balazs_csernai.seriescruncher.app.component.ActivityScope;
import com.balazs_csernai.seriescruncher.app.component.ApplicationComponent;
import com.balazs_csernai.seriescruncher.app.component.CommonActivityModule;
import com.balazs_csernai.seriescruncher.rest.component.NetworkingComponent;
import com.balazs_csernai.seriescruncher.serieslist.SeriesListActivity;
import com.balazs_csernai.seriescruncher.utils.dialog.DialogModule;
import com.balazs_csernai.seriescruncher.utils.navigator.NavigatorModule;

import dagger.Component;

/**
 * Created by Balazs_Csernai on 2016.01.08..
 */
@ActivityScope
@Component(
        dependencies = NetworkingComponent.class,
        modules = {
                CommonActivityModule.class,
                NavigatorModule.class,
                SeriesListModule.class,
                DialogModule.class
        }
)
public interface SeriesListComponent {

    void inject(SeriesListActivity activity);

    final class Injector {
        public static void inject(SeriesListActivity activity) {
            DaggerSeriesListComponent.builder()
                    .networkingComponent(ApplicationComponent.Injector.netComponent())
                    .commonActivityModule(new CommonActivityModule(activity))
                    .navigatorModule(new NavigatorModule())
                    .seriesListModule(new SeriesListModule())
                    .dialogModule(new DialogModule())
                    .build()
                    .inject(activity);
        }
    }
}
