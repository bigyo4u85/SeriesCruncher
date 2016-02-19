package com.balazs_csernai.seriescruncher.notification.ui;

import android.app.Application;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.StringRes;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.app.NotificationCompat;

import com.balazs_csernai.seriescruncher.R;
import com.balazs_csernai.seriescruncher.notification.model.TodaysEpisodeModel;
import com.balazs_csernai.seriescruncher.seriesdetails.model.episode.EpisodeModel;
import com.balazs_csernai.seriescruncher.serieslist.SeriesListActivity;

import java.util.List;

import javax.inject.Inject;

import static android.app.Notification.DEFAULT_LIGHTS;
import static android.app.Notification.DEFAULT_SOUND;

/**
 * Created by ErikKramli on 2016.02.14..
 */
public class AndroidNotification implements Notification {

    private final Context context;
    private final NotificationManager notificationManager;
    private NotificationCompat.Builder notificationBuilder;

    @Inject
    public AndroidNotification(Application context, NotificationManager notificationManager) {
        this.context = context;
        this.notificationManager = notificationManager;
    }

    @Override
    public Notification create(List<TodaysEpisodeModel> todaysEpisodes) {
        notificationBuilder = new NotificationCompat.Builder(context)
                .setDefaults(DEFAULT_SOUND | DEFAULT_LIGHTS)
                .setColor(context.getResources().getColor(R.color.colorPrimary))
                .setSmallIcon(R.drawable.ic_tv)
                .setContentTitle(getTitle(todaysEpisodes.size()))
                .setStyle(new BigTextStyle().bigText(getContentMsg(todaysEpisodes)))
                .setContentIntent(createNotificationIntent());
        return this;
    }

    private String getTitle(int episodeCount) {
        if (episodeCount == 1) {
            return getText(R.string.new_episode_airing_today);
        } else {
            return getText(R.string.new_episodes_airing_today);
        }
    }

    private String getContentMsg(List<TodaysEpisodeModel> todaysEpisodes) {
        StringBuilder sb = new StringBuilder();
        EpisodeModel episode;
        for (TodaysEpisodeModel todaysEpisode : todaysEpisodes) {
            episode = todaysEpisode.getEpisode();
            sb.append(getText(R.string.todays_episode, todaysEpisode.getSeriesTitle(), episode.getSeasonNumber(), episode.getEpisodeNumber(), episode.getTitle()));
            sb.append("\n");
        }
        return sb.toString().trim();
    }

    private String getText(@StringRes int res, Object... args) {
        return context.getResources().getString(res, args);
    }

    private PendingIntent createNotificationIntent() {
        Intent intent = new Intent(context, SeriesListActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(SeriesListActivity.class);
        stackBuilder.addNextIntent(intent);
        return stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    @Override
    public void show() {
        if (notificationBuilder != null) {
            notificationManager.notify(0, notificationBuilder.build());
            notificationBuilder = null;
        }
    }
}
