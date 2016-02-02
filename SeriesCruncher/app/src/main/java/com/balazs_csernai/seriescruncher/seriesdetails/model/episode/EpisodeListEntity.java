package com.balazs_csernai.seriescruncher.seriesdetails.model.episode;

import java.util.Collections;
import java.util.List;

/**
 * Created by Balazs_Csernai on 2016.01.27..
 */
public class EpisodeListEntity implements EpisodeListModel {

    private List<EpisodeListItemModel> items;

    public EpisodeListEntity() {
        items = Collections.emptyList();
    }

    public EpisodeListEntity setItems(List<EpisodeListItemModel> items) {
        this.items = items;
        return this;
    }

    @Override
    public int getItemCount() {
        int visibleItemCount = 0;

        for (EpisodeListItemModel item : items) {
            if (isVisible(item)) {
                visibleItemCount++;
            }
        }

        return visibleItemCount;
    }

    @Override
    public EpisodeListItemModel getItem(int visiblePosition) {
        return items.get(getPosition(visiblePosition));
    }

    @Override
    public void toggle(int visiblePosition) {
        int position = getPosition(visiblePosition);
        EpisodeListItemModel item = items.get(position);

        if (!item.isEpisode()) {
            int lastEpisodePosition = getLastEpisodePosition(position);

            for (int itemPosition = position; itemPosition < lastEpisodePosition; itemPosition++) {
                items.get(itemPosition).toggle();
            }
        }
    }

    private int getPosition(int visiblePosition) {
        int visibleCount = -1;
        for (int itemPosition = 0; itemPosition < items.size(); itemPosition++) {
            if (isVisible(itemPosition)) {
                visibleCount++;
            }
            if (visibleCount == visiblePosition) {
                return itemPosition;
            }
        }
        return visibleCount;
    }

    private int getLastEpisodePosition(int seasonPosition) {
        for (int itemPosition = seasonPosition; itemPosition < items.size(); itemPosition++) {
            if (itemPosition > seasonPosition && !items.get(itemPosition).isEpisode()) {
                return itemPosition;
            }
        }
        return items.size();
    }

    private boolean isVisible(int position) {
        return isVisible(items.get(position));
    }

    private boolean isVisible(EpisodeListItemModel item) {
        return !item.isEpisode() || item.isExpanded();
    }
}
