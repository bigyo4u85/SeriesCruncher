package com.balazs_csernai.seriescruncher.details.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.balazs_csernai.seriescruncher.R;
import com.balazs_csernai.seriescruncher.details.model.SeasonItemModel;
import com.balazs_csernai.seriescruncher.details.model.SeasonItemsModel;
import com.balazs_csernai.seriescruncher.details.model.SeasonItemsModelBuilder;
import com.balazs_csernai.seriescruncher.rest.api.epguides.model.Episode;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Balazs_Csernai on 2016.01.18..
 */
public class SeasonsAdapter extends RecyclerView.Adapter<SeasonsAdapter.SeasonsViewHolder> implements View.OnClickListener {

    private final SeasonItemsModelBuilder modelBuilder;
    private SeasonItemsModel seasonsModel;

    @Inject
    public SeasonsAdapter(SeasonItemsModelBuilder modelBuilder) {
        this.modelBuilder = modelBuilder;
        seasonsModel = modelBuilder.empty();
    }

    public void setItems(Map<Integer, List<Episode>> seasonsMap) {
        seasonsModel = modelBuilder.build(seasonsMap);
        notifyDataSetChanged();
    }

    @Override
    public SeasonsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.series_list_item, parent, false);
        return new SeasonsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SeasonsViewHolder holder, int position) {
        SeasonItemModel seasonItem = seasonsModel.getVisibleItem(position);
        holder.title.setText(seasonItem.getTitle());
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return seasonsModel.getVisibleItemsCount();
    }

    @Override
    public void onClick(View view) {
        int position = (int) view.getTag();
        seasonsModel.setExpanded(position);
        notifyDataSetChanged();
    }

    public class SeasonsViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.series_title)
        TextView title;

        public SeasonsViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }
}
