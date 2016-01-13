package com.balazs_csernai.seriescruncher.serieslist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.balazs_csernai.seriescruncher.R;
import com.balazs_csernai.seriescruncher.details.ShowDetailsActivity;
import com.balazs_csernai.seriescruncher.rest.RestService;
import com.balazs_csernai.seriescruncher.rest.api.epguides.model.ShowShortList;
import com.balazs_csernai.seriescruncher.rest.api.epguides.model.ShowShort;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static com.balazs_csernai.seriescruncher.serieslist.component.SeriesListComponent.Injector.inject;

public class SeriesListActivity extends AppCompatActivity implements RestService.Callback<ShowShortList> {

    @InjectView(R.id.contentTemp)
    TextView contentTemp;

    @Inject
    RestService restService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject(this);
        setContentView(R.layout.activity_series_list);
        ButterKnife.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        restService.bind();
        restService.loadShows(this);
    }

    @Override
    protected void onStop() {
        restService.unbind();
        super.onStop();
    }

    @Override
    public void onSuccess(ShowShortList result) {
        boolean first = true;
        List<String> showNames = new LinkedList<>();
        StringBuilder stringBuilder = new StringBuilder();

        for (ShowShort showShort : result) {
            showNames.add(showShort.getTitle());
        }

        Collections.sort(showNames);

        for (String showName : showNames) {
            if (first) {
                first = !first;
            } else {
                stringBuilder.append("\n");
            }
            stringBuilder.append(showName);
        }

        contentTemp.setText(stringBuilder.toString());
    }

    @Override
    public void onFailure() {

    }

    @OnClick(R.id.to_details_btn)
    void onToDetailsButtonClicked() {
        startActivity(new Intent(this, ShowDetailsActivity.class));
    }
}
