package com.balazs_csernai.seriescruncher.seriesdetails.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.balazs_csernai.seriescruncher.R;
import com.balazs_csernai.seriescruncher.seriesdetails.model.episode.EpisodeListEntity;
import com.balazs_csernai.seriescruncher.seriesdetails.model.episode.EpisodeListItemModel;
import com.balazs_csernai.seriescruncher.seriesdetails.model.episode.EpisodeListModel;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Balazs_Csernai on 2016.01.18..
 */
public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.SeasonsViewHolder> implements View.OnClickListener {

    private EpisodeListModel episodes;

    @Inject
    public EpisodeAdapter() {
        episodes = new EpisodeListEntity();
    }

    public void setItems(EpisodeListModel episodes) {
        this.episodes = episodes;
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
        holder.episodeNumber.setText(item.getEpisodeNumber());
        holder.title.setText(item.getTitle());
        holder.airDate.setText(item.getAirDate());
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
