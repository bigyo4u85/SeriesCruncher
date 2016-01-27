package com.balazs_csernai.seriescruncher.details.model;

import java.util.ArrayList;
import java.util.List;

import static com.balazs_csernai.seriescruncher.details.model.SeasonItemModel.ItemState;
import static com.balazs_csernai.seriescruncher.details.model.SeasonItemModel.ItemState.COLLAPSED;
import static com.balazs_csernai.seriescruncher.details.model.SeasonItemModel.ItemState.EXPANDED;
import static com.balazs_csernai.seriescruncher.details.model.SeasonItemModel.ItemType.HEADER;

/**
 * Created by Balazs_Csernai on 2016.01.18..
 */
public class SeasonItemsEntity implements SeasonItemsModel {
    private final List<SeasonItemModel> items;

    public SeasonItemsEntity(List<SeasonItemModel> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getVisibleItemsCount() {
        int visibleItems = 0;
        for (int position = 0; position < getItemCount(); position++) {
            visibleItems += (isHeader(position) || isExpanded(position)) ? 1 : 0;
        }
        return visibleItems;
    }

    @Override
    public SeasonItemModel getItem(int position) {
        return items.get(position);
    }

    @Override
    public SeasonItemModel getVisibleItem(int position) {
        List<SeasonItemModel> visibleItems = new ArrayList<>(getVisibleItemsCount());
        for (int itemPosition = 0; itemPosition < getItemCount(); itemPosition++) {
            if (isHeader(itemPosition) || isExpanded(itemPosition)) {
                visibleItems.add(items.get(itemPosition));
            }
        }
        return visibleItems.get(position);
    }

    @Override
    public boolean isHeader(int position) {
        return items.get(position).getType() == HEADER;
    }

    @Override
    public boolean isExpanded(int position) {
        return items.get(position).getState() == EXPANDED;
    }

    @Override
    public void setExpanded(int position) {
        switchState(position);
    }

    @Override
    public void setCollapsed(int position) {
        switchState(position);
    }

    private void switchState(int position) {
        int innerPosition = getInnerPosition(position);
        if (isHeader(innerPosition)) {
            @ItemState int newState = isExpanded(innerPosition) ? COLLAPSED : EXPANDED;
            int seasonHeaderPosition = innerPosition;
            int lastEpisodePosition = getSeasonLastEpisodePosition(seasonHeaderPosition);

            for (int itemPosition = seasonHeaderPosition; itemPosition < lastEpisodePosition; itemPosition++) {
                getItem(itemPosition).setState(newState);
            }
        }
    }

    private int getInnerPosition(int visibleItemPosition) {
        int itemPosition = -1;
        for (int position = 0; position < getItemCount(); position++) {
            if (isHeader(position) || isExpanded(position)) {
                itemPosition++;
            }
            if (itemPosition == visibleItemPosition) {
                return position;
            }

        }
        return itemPosition;
    }

    private int getSeasonLastEpisodePosition(int seasonHeaderPosition) {
        for (int position = seasonHeaderPosition; position < getItemCount(); position++) {
            if (position > seasonHeaderPosition && isHeader(position)) {
                return position;
            }
        }
        return getItemCount();
    }
}
