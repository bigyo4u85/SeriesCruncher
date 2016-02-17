package com.balazs_csernai.seriescruncher.utils.alarm;

/**
 * Created by Erik_Markus_Kramli on 2016-02-12.
 */
public interface AlarmHandler {

    void registerDailyNotificationAlarm(int hour, int minute);

    void cancelDailyNotificationAlarm();
}
