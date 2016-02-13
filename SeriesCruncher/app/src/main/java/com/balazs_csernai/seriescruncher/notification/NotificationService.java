package com.balazs_csernai.seriescruncher.notification;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.balazs_csernai.seriescruncher.notification.component.NotificationComponent;
import com.balazs_csernai.seriescruncher.notification.controller.NotificationController;

import javax.inject.Inject;

/**
 * Created by Erik_Markus_Kramli on 2016-02-12.
 */
public class NotificationService extends Service implements NotificationController.Callback {

    @Inject
    NotificationController controller;

    @Override
    public void onCreate() {
        super.onCreate();
        NotificationComponent.Injector.inject(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        controller.showNewEpisodeNotifications(this);
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onFinish() {
        stopSelf();
    }
}
