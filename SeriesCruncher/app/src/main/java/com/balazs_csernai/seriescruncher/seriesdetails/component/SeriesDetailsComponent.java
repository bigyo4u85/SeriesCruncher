package com.balazs_csernai.seriescruncher.seriesdetails.component;

import com.balazs_csernai.seriescruncher.app.component.ActivityScope;
import com.balazs_csernai.seriescruncher.app.component.ApplicationComponent;
import com.balazs_csernai.seriescruncher.app.component.CommonActivityModule;
import com.balazs_csernai.seriescruncher.seriesdetails.SeriesDetailsActivity;
import com.balazs_csernai.seriescruncher.rest.component.RestModule;
import com.balazs_csernai.seriescruncher.utils.color.ColorModule;
import com.balazs_csernai.seriescruncher.utils.converter.ConverterModule;
import com.balazs_csernai.seriescruncher.image.ImageLoaderModule;

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
                SeriesDetailsModule.class,
                ConverterModule.class,
                ImageLoaderModule.class,
                ColorModule.class
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
                    .converterModule(new ConverterModule())
                    .imageLoaderModule(new ImageLoaderModule())
                    .colorModule(new ColorModule())
                    .build()
                    .inject(activity);
        }
    }
}
