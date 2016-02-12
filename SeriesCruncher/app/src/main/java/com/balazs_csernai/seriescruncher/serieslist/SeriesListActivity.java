package com.balazs_csernai.seriescruncher.serieslist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.balazs_csernai.seriescruncher.R;
import com.balazs_csernai.seriescruncher.serieslist.presenter.SeriesListPresenter;
import com.balazs_csernai.seriescruncher.utils.dialog.NetworkErrorDialogFragment;

import javax.inject.Inject;

import static com.balazs_csernai.seriescruncher.serieslist.component.SeriesListComponent.Injector.inject;

public class SeriesListActivity extends AppCompatActivity implements NetworkErrorDialogFragment.NetworkErrorDialogCallback {

    @Inject
    SeriesListPresenter presenter;

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

    @Override
    public void onNetworkErrorRetry() {
        presenter.onNetworkErrorRetry();
    }

    @Override
    public void onNetworkErrorCancel() {
        presenter.onNetworkErrorCancel();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.settings) {
            presenter.onSettingsMenuRequest();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
