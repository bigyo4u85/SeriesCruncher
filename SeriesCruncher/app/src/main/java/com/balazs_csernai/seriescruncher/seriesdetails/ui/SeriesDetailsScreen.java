package com.balazs_csernai.seriescruncher.seriesdetails.ui;

import android.graphics.Bitmap;
import android.support.annotation.ColorInt;

import com.balazs_csernai.seriescruncher.seriesdetails.model.episode.EpisodeListModel;

/**
 * Created by Erik_Markus_Kramli on 2016-01-13.
 */
public interface SeriesDetailsScreen {

    void onCreate();

    void setTitle(String title);

    void setColors(@ColorInt int primaryBackgroundColor, @ColorInt int secondaryBackgroundColor, @ColorInt int primaryTextColor, @ColorInt int secondaryTextColor);

    void setPoster(Bitmap bitmap);

    void setBackground(Bitmap bitmap);

    void setEpisodes(EpisodeListModel episodes);

    void showNetworkErrorDialog();
}
