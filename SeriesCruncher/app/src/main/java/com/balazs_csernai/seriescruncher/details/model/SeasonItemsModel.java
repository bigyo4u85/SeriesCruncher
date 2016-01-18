package com.balazs_csernai.seriescruncher.details.model;

/**
 * Created by Balazs_Csernai on 2016.01.18..
 */
public interface SeasonItemsModel {

    int getItemCount();

    int getVisibleItemsCount();

    SeasonItemModel getItem(int position);

    SeasonItemModel getVisibleItem(int position);

    boolean isHeader(int position);

    boolean isExpanded(int position);

    void setExpanded(int position);

    void setCollapsed(int position);
}
