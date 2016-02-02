package com.balazs_csernai.seriescruncher.serieslist.component;

import com.balazs_csernai.seriescruncher.app.component.ActivityScope;
import com.balazs_csernai.seriescruncher.app.component.ApplicationComponent;
import com.balazs_csernai.seriescruncher.app.component.CommonActivityModule;
import com.balazs_csernai.seriescruncher.image.ImageLoader;
import com.balazs_csernai.seriescruncher.image.ImageLoaderModule;
import com.balazs_csernai.seriescruncher.rest.component.RestModule;
import com.balazs_csernai.seriescruncher.serieslist.SeriesListActivity;
import com.balazs_csernai.seriescruncher.utils.color.ColorModule;
import com.balazs_csernai.seriescruncher.utils.converter.ConverterModule;
import com.balazs_csernai.seriescruncher.utils.navigator.NavigatorModule;

import dagger.Component;

/**
 * Created by Balazs_Csernai on 2016.01.08..
 */
@ActivityScope
@Component(
        dependencies = ApplicationComponent.class,
        modules = {
                CommonActivityModule.class,
                NavigatorModule.class,
                RestModule.class,
                SeriesListModule.class,
                ConverterModule.class,
                ImageLoaderModule.class,
                ColorModule.class
        }
)
public interface SeriesListComponent {

    void inject(SeriesListActivity activity);

    final class Injector {
        public static void inject(SeriesListActivity activity) {
            DaggerSeriesListComponent.builder()
                    .applicationComponent(ApplicationComponent.Injector.component())
                    .commonActivityModule(new CommonActivityModule(activity))
                    .navigatorModule(new NavigatorModule())
                    .restModule(new RestModule())
                    .seriesListModule(new SeriesListModule())
                    .converterModule(new ConverterModule())
                    .build()
                    .inject(activity);
        }
    }
}
