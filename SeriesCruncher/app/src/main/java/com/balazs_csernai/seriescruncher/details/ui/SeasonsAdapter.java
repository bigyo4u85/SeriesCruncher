package com.balazs_csernai.seriescruncher.details.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.balazs_csernai.seriescruncher.R;
import com.balazs_csernai.seriescruncher.details.model.DetailScreenItemModel;
import com.balazs_csernai.seriescruncher.details.model.DetailsScreenModel;
import com.balazs_csernai.seriescruncher.details.model.SeasonItemsModelBuilder;
import com.balazs_csernai.seriescruncher.details.model.SeasonsModel;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Balazs_Csernai on 2016.01.18..
 */
public class SeasonsAdapter extends RecyclerView.Adapter<SeasonsAdapter.SeasonsViewHolder> implements View.OnClickListener {

    private final SeasonItemsModelBuilder modelBuilder;
    private DetailsScreenModel model;

    @Inject
    public SeasonsAdapter(SeasonItemsModelBuilder modelBuilder) {
        this.modelBuilder = modelBuilder;
        model = modelBuilder.empty();
    }

    public void setItems(SeasonsModel model) {
        this.model = modelBuilder.build(model);
        notifyDataSetChanged();
    }

    @Override
    public SeasonsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.series_list_item, parent, false);
        return new SeasonsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SeasonsViewHolder holder, int position) {
        DetailScreenItemModel seasonItem = model.getItem(position);
        holder.title.setText(seasonItem.getText());
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return model.getItemCount();
    }

    @Override
    public void onClick(View view) {
        int position = (int) view.getTag();
        model.setExpandedOrCollapsed(position);
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
