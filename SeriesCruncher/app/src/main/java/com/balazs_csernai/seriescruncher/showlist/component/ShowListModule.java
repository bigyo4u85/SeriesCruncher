package com.balazs_csernai.seriescruncher.showlist.component;

import com.balazs_csernai.seriescruncher.showlist.presenter.ShowListPresenter;
import com.balazs_csernai.seriescruncher.showlist.presenter.ShowListPresenterImpl;
import com.balazs_csernai.seriescruncher.showlist.ui.ShowListScreen;
import com.balazs_csernai.seriescruncher.showlist.ui.ShowListScreenImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ErikKramli on 2016.01.17..
 */
@Module
public class ShowListModule {

    @Provides
    ShowListScreen provideScreen(ShowListScreenImpl impl) {
        return impl;
    }

    @Provides
    ShowListPresenter providePresenter(ShowListPresenterImpl impl) {
        return impl;
    }
}
