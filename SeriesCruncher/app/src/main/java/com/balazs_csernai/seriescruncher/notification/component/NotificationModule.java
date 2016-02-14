package com.balazs_csernai.seriescruncher.notification.component;

import com.balazs_csernai.seriescruncher.notification.controller.NotificationController;
import com.balazs_csernai.seriescruncher.notification.controller.NotificationControllerImpl;
import com.balazs_csernai.seriescruncher.notification.ui.AndroidNotification;
import com.balazs_csernai.seriescruncher.notification.ui.Notification;
import com.balazs_csernai.seriescruncher.seriesdetails.model.finder.EpisodeFinder;
import com.balazs_csernai.seriescruncher.seriesdetails.model.finder.EpisodeFinderImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Erik_Markus_Kramli on 2016-02-12.
 */
@Module
public class NotificationModule {

    @Provides
    NotificationController provideController(NotificationControllerImpl impl) {
        return impl;
    }

    @Provides
    EpisodeFinder provideEpisodeFinder(EpisodeFinderImpl impl) {
        return impl;
    }

    @Provides
    Notification provideNotification(AndroidNotification impl) {
        return impl;
    }
}
