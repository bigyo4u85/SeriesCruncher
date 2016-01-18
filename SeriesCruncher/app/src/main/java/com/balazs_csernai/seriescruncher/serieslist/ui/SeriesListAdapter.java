package com.balazs_csernai.seriescruncher.serieslist.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.balazs_csernai.seriescruncher.R;
import com.balazs_csernai.seriescruncher.rest.api.epguides.model.Series;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ErikKramli on 2016.01.17..
 */
public class SeriesListAdapter extends RecyclerView.Adapter<SeriesListAdapter.SeriesViewHolder> {

    public interface OnShowListener {
        void onSeriesSelected(Series series);
    }

    private List<Series> seriesList;
    private OnShowListener listener;

    @Inject
    public SeriesListAdapter() {
        seriesList = Collections.emptyList();
    }

    public void setItems(List<Series> seriesList) {
        this.seriesList = seriesList;
        notifyDataSetChanged();
    }

    public void setOnShowListener(OnShowListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return seriesList.size();
    }

    @Override
    public SeriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.series_list_item, parent, false);
        return new SeriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SeriesViewHolder holder, int position) {
        Series series = seriesList.get(position);
        bind(holder, series);
    }

    private void bind(SeriesViewHolder holder, final Series series) {
        holder.title.setText(series.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onSeriesSelected(series);
                }
            }
        });
    }

    static class SeriesViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.series_title)
        TextView title;

        public SeriesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
