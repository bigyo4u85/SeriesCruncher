package com.balazs_csernai.seriescruncher.details.model;

/**
 * Created by Balazs_Csernai on 2016.01.18..
 */
public interface DetailsScreenModel {

    int getItemCount();

    DetailScreenItemModel getItem(int position);

    void setExpandedOrCollapsed(int position);
}
