package com.balazs_csernai.seriescruncher.seriesdetails.ui;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.balazs_csernai.seriescruncher.R;
import com.balazs_csernai.seriescruncher.seriesdetails.model.episode.EpisodeListItemModel;
import com.balazs_csernai.seriescruncher.seriesdetails.model.episode.EpisodeListModel;
import com.balazs_csernai.seriescruncher.utils.common.DateUtils;
import com.balazs_csernai.seriescruncher.utils.ui.ViewUtils;
import com.balazs_csernai.seriescruncher.utils.ui.color.model.ColorModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Balazs_Csernai on 2016.01.18..
 */
public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.SeasonsViewHolder> implements View.OnClickListener {

    private List<EpisodeListItemModel> items;

    @ColorInt
    private int primaryTextColor, secondaryTextColor;

    @Inject
    public EpisodeAdapter() {
        items = Collections.emptyList();
        primaryTextColor = secondaryTextColor = Color.WHITE;
    }

    public void setItems(EpisodeListModel model) {
        this.items = new ArrayList<>(model.getEpisodes());
        notifyDataSetChanged();
    }

    public void setColors(ColorModel primaryColor, ColorModel secondaryColor) {
        this.primaryTextColor = primaryColor.getBackgroundColor();
        this.secondaryTextColor = secondaryColor.getBackgroundColor();
        notifyDataSetChanged();
    }

    @Override
    public SeasonsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.episode_list_item, parent, false);
        return new SeasonsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SeasonsViewHolder holder, int position) {
        EpisodeListItemModel item = getItem(position);
        if (item.isEpisode()) {
            holder.episodeNumber.setTextColor(secondaryTextColor);
        } else {
            holder.episodeNumber.setTextColor(primaryTextColor);
        }
        holder.episodeNumber.setText(item.getEpisodeNumber());
        holder.title.setText(item.getTitle());

        if (item.getAirDate() == null) {
            ViewUtils.gone(holder.airDate);
        } else {
            ViewUtils.visible(holder.airDate);
            holder.airDate.setText(DateUtils.parseDate(item.getAirDate()));
        }
        holder.itemView.setTag(mapVisiblePositionToInnerPosition(position));
        holder.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        int visibleItemCount = 0;
        for (EpisodeListItemModel episode : items) {
            if (isVisible(episode)) {
                visibleItemCount++;
            }
        }
        return visibleItemCount;
    }

    @Override
    public void onClick(View view) {
        int position = (int) view.getTag();
        onItemTap(position);
    }

    public class SeasonsViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.episode_number)
        TextView episodeNumber;
        @InjectView(R.id.episode_title)
        TextView title;
        @InjectView(R.id.episode_airdate)
        TextView airDate;

        public SeasonsViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }

    private EpisodeListItemModel getItem(int position) {
        return items.get(mapVisiblePositionToInnerPosition(position));
    }

    private void onItemTap(int position) {
        EpisodeListItemModel item = items.get(position);

        if (!item.isEpisode()) {
            int nextSeasonOrEndPosition = getNextSeasonOrEndPosition(position);

            for (int itemPosition = position; itemPosition < nextSeasonOrEndPosition; itemPosition++) {
                items.get(itemPosition).toggle();
            }

            int firstInsertedOrRemovedItemPosition = position + 1;
            int modifiedItemCount = nextSeasonOrEndPosition - firstInsertedOrRemovedItemPosition;
            int firstInsertedOrRemovedVisibleItemPosition = mapInnerPositionToVisiblePosition(firstInsertedOrRemovedItemPosition);

            notifyAdapter(firstInsertedOrRemovedVisibleItemPosition, modifiedItemCount, item.isExpanded());
        }
    }

    private void notifyAdapter(int position, int count, boolean insertion) {
        if (insertion) {
            notifyItemRangeInserted(position, count);
        } else {
            notifyItemRangeRemoved(position, count);
        }
    }

    private int mapVisiblePositionToInnerPosition(int position) {
        return countItemsBeforeVisiblePosition(position);
    }

    private int mapInnerPositionToVisiblePosition(int position) {
        return position - countInvisibleItemsBeforePosition(position);
    }

    private int countItemsBeforeVisiblePosition(int position) {
        int itemCount = 0;
        int visibleItemCount = 0;
        while (visibleItemCount != position && itemCount < items.size()) {
            if (isVisible(items.get(++itemCount))) {
                visibleItemCount++;
            }
        }
        return itemCount;
    }

    private int countInvisibleItemsBeforePosition(int position) {
        int invisibleItemCount = 0;
        for (int innerPosition = 0; innerPosition < position; innerPosition++) {
            if (!isVisible(items.get(innerPosition))) {
                invisibleItemCount++;
            }
        }
        return invisibleItemCount;
    }

    private int getNextSeasonOrEndPosition(int position) {
        for (int itemPosition = position + 1; itemPosition < items.size(); itemPosition++) {
            if (!items.get(itemPosition).isEpisode()) {
                return itemPosition;
            }
        }
        return items.size();
    }

    private boolean isVisible(EpisodeListItemModel episode) {
        return !episode.isEpisode() || episode.isExpanded();
    }
}