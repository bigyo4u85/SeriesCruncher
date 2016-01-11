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
                RestModule.class
        }
)
public interface ShowDetailsComponent {
    void inject(ShowDetailsActivity activity);

    final class Injector {

        public static void inject(ShowDetailsActivity activity) {

        }
    }
}
