package com.balazs_csernai.seriescruncher.notification;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Erik_Markus_Kramli on 2016-02-12.
 */
public class NotificationService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("notification", "onCreate");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("notification", "onBind");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("notification", "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("notification", "onDestroy");
    }

}
