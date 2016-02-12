package com.balazs_csernai.seriescruncher.utils.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.balazs_csernai.seriescruncher.notification.NotificationService;
import com.balazs_csernai.seriescruncher.utils.common.DateUtils;

import javax.inject.Inject;

import static android.app.AlarmManager.INTERVAL_DAY;
import static android.app.AlarmManager.RTC;

/**
 * Created by Erik_Markus_Kramli on 2016-02-12.
 */
public class AlarmHandlerImpl implements AlarmHandler {

    private final Context context;
    private final AlarmManager alarmManager;

    @Inject
    public AlarmHandlerImpl(Context context, AlarmManager alarmManager) {
        this.context = context;
        this.alarmManager = alarmManager;
    }

    @Override
    public void registerDailyNotificationAlarm(int hour, int minute) {
        PendingIntent operation = getNotificationServiceIntent();
        long triggerTime = DateUtils.getLocalTimeInMillis(hour, minute);
        alarmManager.setRepeating(RTC, triggerTime, INTERVAL_DAY, operation);
    }

    @Override
    public void cancelDailyNotificationAlarm() {
        alarmManager.cancel(getNotificationServiceIntent());
    }

    private PendingIntent getNotificationServiceIntent() {
        Intent intent = new Intent(context, NotificationService.class);
        return PendingIntent.getService(context, 0, intent, 0);
    }
}
