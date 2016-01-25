package com.balazs_csernai.seriescruncher.details.model;

/**
 * Created by Balazs_Csernai on 2016.01.18..
 */
public interface DetailScreenItemModel {

    boolean isHeader();

    boolean isExpanded();

    void setExpanded(boolean expanded);

    String getText();
}
