package com.balazs_csernai.seriescruncher.details.model;

/**
 * Created by Balazs_Csernai on 2016.01.18..
 */
public class SeasonItemEntity implements SeasonItemModel {

    private final int position;
    @ItemType
    private final int type;
    @ItemState
    private int state;
    private final String title;

    public SeasonItemEntity(int position, @ItemType int type, @ItemState int state, String title) {
        this.position = position;
        this.type = type;
        this.state = state;
        this.title = title;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public int getState() {
        return state;
    }

    @Override
    public void setState(@ItemState int state) {
        this.state = state;
    }

    @Override
    public String getTitle() {
        return title;
    }
}
