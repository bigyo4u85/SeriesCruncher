package com.balazs_csernai.seriescruncher.showlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.balazs_csernai.seriescruncher.R;
import com.balazs_csernai.seriescruncher.showlist.presenter.ShowListPresenter;

import javax.inject.Inject;

import static com.balazs_csernai.seriescruncher.showlist.component.ShowListComponent.Injector.inject;

public class ShowListActivity extends AppCompatActivity {

    @Inject
    ShowListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_list);
        inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    protected void onStop() {
        presenter.onStop();
        super.onStop();
    }
}
