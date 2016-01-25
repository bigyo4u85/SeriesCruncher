package com.balazs_csernai.seriescruncher.showlist.component;

import com.balazs_csernai.seriescruncher.app.component.ActivityScope;
import com.balazs_csernai.seriescruncher.app.component.ApplicationComponent;
import com.balazs_csernai.seriescruncher.app.component.CommonActivityModule;
import com.balazs_csernai.seriescruncher.rest.component.RestModule;
import com.balazs_csernai.seriescruncher.showlist.ShowListActivity;
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
                ShowListModule.class
        }
)
public interface ShowListComponent {

    void inject(ShowListActivity activity);

    final class Injector {
        public static void inject(ShowListActivity activity) {
            DaggerShowListComponent.builder()
                    .applicationComponent(ApplicationComponent.Injector.component())
                    .commonActivityModule(new CommonActivityModule(activity))
                    .navigatorModule(new NavigatorModule())
                    .restModule(new RestModule())
                    .showListModule(new ShowListModule())
                    .build()
                    .inject(activity);
        }
    }
}
