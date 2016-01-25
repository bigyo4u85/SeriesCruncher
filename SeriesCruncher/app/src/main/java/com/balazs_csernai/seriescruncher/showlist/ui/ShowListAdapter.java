package com.balazs_csernai.seriescruncher.showlist.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.balazs_csernai.seriescruncher.R;
import com.balazs_csernai.seriescruncher.model.ShowModel;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ErikKramli on 2016.01.17..
 */
public class ShowListAdapter extends RecyclerView.Adapter<ShowListAdapter.SeriesViewHolder> {

    public interface OnShowSelectedListener {
        void onShowSelected(ShowModel show);
    }

    private List<ShowModel> shows;
    private OnShowSelectedListener listener;

    @Inject
    public ShowListAdapter() {
        shows = Collections.emptyList();
    }

    public void setItems(List<ShowModel> shows) {
        this.shows = shows;
        notifyDataSetChanged();
    }

    public void setOnShowListener(OnShowSelectedListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return shows.size();
    }

    @Override
    public SeriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.series_list_item, parent, false);
        return new SeriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SeriesViewHolder holder, int position) {
        ShowModel show = shows.get(position);
        bind(holder, show);
    }

    private void bind(SeriesViewHolder holder, final ShowModel show) {
        holder.title.setText(show.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onShowSelected(show);
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
