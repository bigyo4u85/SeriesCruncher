package com.balazs_csernai.seriescruncher.seriesdetails.model;

/**
 * Created by Balazs_Csernai on 2016.01.27..
 */
public interface EpisodeListModel {

    int getItemCount();

    EpisodeListItemModel getItem(int position);

    void toggle(int position);
}
