package com.balazs_csernai.seriescruncher.details.model;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;

import static com.balazs_csernai.seriescruncher.details.model.SeasonItemModel.ItemState.COLLAPSED;
import static com.balazs_csernai.seriescruncher.details.model.SeasonItemModel.ItemState.EXPANDED;
import static com.balazs_csernai.seriescruncher.details.model.SeasonItemModel.ItemType.EPISODE;
import static com.balazs_csernai.seriescruncher.details.model.SeasonItemModel.ItemType.HEADER;
import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Created by Balazs_Csernai on 2016.01.18..
 */
public interface SeasonItemModel {

    @Retention(SOURCE)
    @IntDef({HEADER, EPISODE})
    @interface ItemType {
        int HEADER = 0;
        int EPISODE = 1;
    }

    @Retention(SOURCE)
    @IntDef({COLLAPSED, EXPANDED})
    @interface ItemState {
        int COLLAPSED = 0;
        int EXPANDED = 1;
    }

    @ItemType
    int getType();

    @ItemState
    int getState();

    void setState(@ItemState int state);

    String getTitle();
}
