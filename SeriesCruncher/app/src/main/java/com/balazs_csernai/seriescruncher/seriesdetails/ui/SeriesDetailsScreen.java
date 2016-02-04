package com.balazs_csernai.seriescruncher.seriesdetails.ui;

import android.graphics.Bitmap;

import com.balazs_csernai.seriescruncher.seriesdetails.model.episode.EpisodeListModel;

/**
 * Created by Erik_Markus_Kramli on 2016-01-13.
 */
public interface SeriesDetailsScreen {

    void onCreate();

    void setTitle(String title);

    void setPrimaryBackgroundColor(int color);

    void setPrimaryTextColor(int color);

    void setSecondaryBackgroundColor(int color);

    void setSecondaryTextColor(int color);

    void setPoster(Bitmap bitmap);

    void setBackground(Bitmap bitmap);

    void setEpisodes(EpisodeListModel episodes);
}
