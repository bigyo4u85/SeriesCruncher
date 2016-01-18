package com.balazs_csernai.seriescruncher.serieslist.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.balazs_csernai.seriescruncher.R;
import com.balazs_csernai.seriescruncher.rest.api.epguides.model.Show;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ErikKramli on 2016.01.17..
 */
public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder> {

    public interface OnShowListener {
        void onShowTapped(Show show);
    }

    private List<Show> series;
    private OnShowListener listener;

    @Inject
    public SeriesAdapter() {
        series = Collections.emptyList();
    }

    public void setItems(List<Show> series) {
        this.series = series;
        notifyDataSetChanged();
    }

    public void setOnShowListener(OnShowListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return series.size();
    }

    @Override
    public SeriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.series_list_item, parent, false);
        return new SeriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SeriesViewHolder holder, int position) {
        Show show = series.get(position);
        bind(holder, show);
    }

    private void bind(SeriesViewHolder holder, final Show show) {
        holder.title.setText(show.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onShowTapped(show);
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
