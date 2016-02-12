package com.balazs_csernai.seriescruncher.utils.common;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by erik_markus_kramli on 2016-02-09.
 */
public final class DateUtils {

    public static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat(DATE_PATTERN, Locale.ENGLISH);

    public static Date parseDate(String rawDate) {
        try {
            return DateUtils.DATE_FORMAT.parse(rawDate);

        } catch (ParseException e) {
            Log.e(DateUtils.class.getSimpleName(), "Error parsing date: " + rawDate);
            return null;
        }
    }

    public static String parseDate(Date date) {
        return date == null ? null : DATE_FORMAT.format(date);
    }

    public static Date getTodayDate() {
        return Calendar.getInstance().getTime();
    }

    public static long getTimeInMillis(int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        return calendar.getTimeInMillis();
    }

    private DateUtils() {}
}
