package com.balazs_csernai.seriescruncher.serieslist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.balazs_csernai.seriescruncher.R;
import com.balazs_csernai.seriescruncher.rest.epguides.EPGuideService;
import com.balazs_csernai.seriescruncher.rest.epguides.model.ShowShort;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.balazs_csernai.seriescruncher.serieslist.component.SeriesListComponent.Injector.inject;

public class SeriesListActivity extends AppCompatActivity implements Callback<ShowShort[]> {

    @InjectView(R.id.contentTemp)
    TextView contentTemp;

    @Inject
    EPGuideService epGuideService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject(this);
        setContentView(R.layout.activity_series_list);
        ButterKnife.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        epGuideService.loadShows(this);
    }

    @Override
    public void success(ShowShort[] showShorts, Response response) {
        boolean first = true;
        List<String> showNames = new LinkedList<>();
        StringBuilder stringBuilder = new StringBuilder();

        for (ShowShort showShort : showShorts) {
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
    public void failure(RetrofitError error) {

    }
}
