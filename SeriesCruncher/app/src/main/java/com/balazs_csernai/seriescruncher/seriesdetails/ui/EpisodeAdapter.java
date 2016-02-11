package com.balazs_csernai.seriescruncher.seriesdetails.ui;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.balazs_csernai.seriescruncher.R;
import com.balazs_csernai.seriescruncher.seriesdetails.model.episode.EpisodeListEntity;
import com.balazs_csernai.seriescruncher.seriesdetails.model.episode.EpisodeListItemModel;
import com.balazs_csernai.seriescruncher.seriesdetails.model.episode.EpisodeListModel;
import com.balazs_csernai.seriescruncher.utils.common.DateUtils;
import com.balazs_csernai.seriescruncher.utils.ui.ViewUtils;
import com.balazs_csernai.seriescruncher.utils.ui.color.model.ColorModel;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Balazs_Csernai on 2016.01.18..
 */
public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.SeasonsViewHolder> implements View.OnClickListener {

    private EpisodeListModel episodes;
    @ColorInt
    private int primaryBackgroundColor, primaryTextColor, secondaryTextColor, secondaryBackgroundColor;

    @Inject
    public EpisodeAdapter() {
        episodes = new EpisodeListEntity();
        primaryBackgroundColor = primaryTextColor = secondaryBackgroundColor = secondaryTextColor = Color.WHITE;
    }

    public void setItems(EpisodeListModel episodes) {
        this.episodes = episodes;
        notifyDataSetChanged();
    }

    public void setColors(ColorModel primaryColor, ColorModel secondaryColor) {
        this.primaryBackgroundColor = primaryColor.getBackgroundColor();
        this.secondaryBackgroundColor = secondaryColor.getBackgroundColor();
        this.primaryTextColor = primaryColor.getForegroundColor();
        this.secondaryTextColor = secondaryColor.getForegroundColor();
        notifyDataSetChanged();
    }

    @Override
    public SeasonsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.episode_list_item, parent, false);
        return new SeasonsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SeasonsViewHolder holder, int position) {
        EpisodeListItemModel item = episodes.getItem(position);
        if (item.isEpisode()) {
            holder.episodeNumberBackground.setColorFilter(secondaryBackgroundColor);
            holder.episodeNumber.setTextColor(secondaryTextColor);
        } else {
            holder.episodeNumberBackground.setColorFilter(primaryBackgroundColor);
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
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return episodes.getItemCount();
    }

    @Override
    public void onClick(View view) {
        int position = (int) view.getTag();
        episodes.toggle(position);
        notifyDataSetChanged();
    }

    public class SeasonsViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.episode_number)
        TextView episodeNumber;
        @InjectView(R.id.episode_number_background)
        ImageView episodeNumberBackground;
        @InjectView(R.id.episode_title)
        TextView title;
        @InjectView(R.id.episode_airdate)
        TextView airDate;

        public SeasonsViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }
}
