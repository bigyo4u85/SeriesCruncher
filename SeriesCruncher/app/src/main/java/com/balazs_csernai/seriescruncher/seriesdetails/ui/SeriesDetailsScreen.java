package com.balazs_csernai.seriescruncher.seriesdetails.ui;

import android.graphics.Bitmap;

import com.balazs_csernai.seriescruncher.seriesdetails.model.episode.EpisodeListModel;
import com.balazs_csernai.seriescruncher.utils.ui.color.model.ColorModel;

/**
 * Created by Erik_Markus_Kramli on 2016-01-13.
 */
public interface SeriesDetailsScreen {

    void onCreate();

    void displayProgressIndicator();

    void displaySeriesDetails(EpisodeListModel episodes);

    void setTitle(String title);

    void setColors(ColorModel primaryColor, ColorModel secondaryColor, ColorModel accentColor);

    void setPoster(Bitmap bitmap);

    void setBackground(Bitmap bitmap);

}
