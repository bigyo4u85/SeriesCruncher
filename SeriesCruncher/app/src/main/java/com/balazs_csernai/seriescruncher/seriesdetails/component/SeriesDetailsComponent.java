package com.balazs_csernai.seriescruncher.seriesdetails.component;

import com.balazs_csernai.seriescruncher.app.component.ActivityScope;
import com.balazs_csernai.seriescruncher.app.component.ApplicationComponent;
import com.balazs_csernai.seriescruncher.app.component.CommonActivityModule;
import com.balazs_csernai.seriescruncher.rest.component.NetworkingComponent;
import com.balazs_csernai.seriescruncher.seriesdetails.SeriesDetailsActivity;
import com.balazs_csernai.seriescruncher.utils.dialog.DialogModule;
import com.balazs_csernai.seriescruncher.utils.navigator.NavigatorModule;
import com.balazs_csernai.seriescruncher.utils.ui.animation.AnimationModule;

import dagger.Component;

/**
 * Created by ErikKramli on 2016.01.10..
 */
@ActivityScope
@Component(
        dependencies = NetworkingComponent.class,
        modules = {
                CommonActivityModule.class,
                SeriesDetailsModule.class,
                NavigatorModule.class,
                DialogModule.class,
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
                    .networkingComponent(ApplicationComponent.Injector.netComponent())
                    .commonActivityModule(new CommonActivityModule(activity))
                    .seriesDetailsModule(new SeriesDetailsModule())
                    .navigatorModule(new NavigatorModule())
                    .dialogModule(new DialogModule())
                    .animationModule(new AnimationModule())
                    .build()
                    .inject(activity);
        }
    }
}
