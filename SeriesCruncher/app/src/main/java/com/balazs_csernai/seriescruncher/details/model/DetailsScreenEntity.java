package com.balazs_csernai.seriescruncher.details.model;

import java.util.List;

/**
 * Created by Balazs_Csernai on 2016.01.18..
 */
public class DetailsScreenEntity implements DetailsScreenModel {
    private final List<DetailScreenItemModel> items;

    public DetailsScreenEntity(List<DetailScreenItemModel> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        int visibleItemCount = 0;
        for (DetailScreenItemModel item : items) {
            visibleItemCount += (item.isHeader() || item.isExpanded()) ? 1 : 0;
        }
        return visibleItemCount;
    }

    @Override
    public DetailScreenItemModel getItem(int visiblePosition) {
        return items.get(getPosition(visiblePosition));
    }

    @Override
    public void setExpandedOrCollapsed(int visiblePosition) {
        switchState(visiblePosition);
    }

    private void switchState(int visiblePosition) {
        int position = getPosition(visiblePosition);
        DetailScreenItemModel item = items.get(position);

        if (item.isHeader()) {
            int headerPosition = position;
            int lastEpisodePosition = getLastEpisodePosition(headerPosition);

            for (int episodePosition = headerPosition; episodePosition < lastEpisodePosition; episodePosition++) {
                getItem(episodePosition).setExpanded(!item.isExpanded());
            }
        }
    }

    private int getPosition(int visiblePosition) {
        int visibleItemCounter = -1;
        for (int position = 0; position < items.size(); position++) {
            if (items.get(position).isHeader()
                    || items.get(position).isExpanded()) {
                visibleItemCounter++;
            }
            if (visibleItemCounter == visiblePosition) {
                return position;
            }
        }
        return visibleItemCounter;
    }

    private int getLastEpisodePosition(int seasonHeaderPosition) {
        for (int position = seasonHeaderPosition; position < items.size(); position++) {
            if (position > seasonHeaderPosition && items.get(position).isHeader()) {
                return position;
            }
        }
        return items.size();
    }
}
