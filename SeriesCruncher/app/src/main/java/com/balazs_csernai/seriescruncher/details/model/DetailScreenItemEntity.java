package com.balazs_csernai.seriescruncher.details.model;

/**
 * Created by Balazs_Csernai on 2016.01.18..
 */
public class DetailScreenItemEntity implements DetailScreenItemModel {

    private final boolean header;
    private boolean expanded;
    private final String text;

    public DetailScreenItemEntity(boolean header, boolean expanded, String text) {
        this.header = header;
        this.expanded = expanded;
        this.text = text;
    }

    @Override
    public boolean isHeader() {
        return header;
    }

    @Override
    public boolean isExpanded() {
        return expanded;
    }

    @Override
    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    @Override
    public String getText() {
        return text;
    }
}
