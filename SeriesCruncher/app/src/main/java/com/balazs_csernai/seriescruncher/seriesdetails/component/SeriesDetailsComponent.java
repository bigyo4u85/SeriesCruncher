package com.balazs_csernai.seriescruncher.seriesdetails.component;

import com.balazs_csernai.seriescruncher.app.component.ActivityScope;
import com.balazs_csernai.seriescruncher.app.component.ApplicationComponent;
import com.balazs_csernai.seriescruncher.app.component.CommonActivityModule;
import com.balazs_csernai.seriescruncher.rest.component.RestModule;
import com.balazs_csernai.seriescruncher.seriesdetails.SeriesDetailsActivity;
import com.balazs_csernai.seriescruncher.utils.converter.ConverterModule;
import com.balazs_csernai.seriescruncher.utils.image.ImageModule;
import com.balazs_csernai.seriescruncher.utils.ui.animation.AnimationModule;

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
                ImageModule.class,
                AnimationModule.class
        }
)
public interface SeriesDetailsComponent {
    void inject(SeriesDetailsActivity activity);

    final class Injector {

        private Injector() {
        }

        public static void inject(SeriesDetailsActivity activity) {
            DaggerSeriesDetailsComponent.builder()
                    .applicationComponent(ApplicationComponent.Injector.component())
                    .commonActivityModule(new CommonActivityModule(activity))
                    .restModule(new RestModule())
                    .seriesDetailsModule(new SeriesDetailsModule())
                    .converterModule(new ConverterModule())
                    .imageModule(new ImageModule())
                    .animationModule(new AnimationModule())
                    .build()
                    .inject(activity);
        }
    }
}
