package com.balazs_csernai.seriescruncher.rest.component;

import android.app.Application;
import android.app.NotificationManager;
import android.content.res.Resources;
import android.view.inputmethod.InputMethodManager;

import com.balazs_csernai.seriescruncher.app.component.NetworkingScope;
import com.balazs_csernai.seriescruncher.preferences.Preferences;
import com.balazs_csernai.seriescruncher.rest.SeriesLoader;
import com.balazs_csernai.seriescruncher.utils.alarm.AlarmHandler;
import com.balazs_csernai.seriescruncher.utils.converter.ConverterModule;
import com.balazs_csernai.seriescruncher.utils.converter.EpisodeList;
import com.balazs_csernai.seriescruncher.utils.converter.ModelConverter;
import com.balazs_csernai.seriescruncher.utils.image.ImageModule;

import dagger.Subcomponent;
import retrofit.converter.Converter;

/**
 * Created by Erik_Markus_Kramli on 2016-02-17.
 */
@NetworkingScope    // Subcomponents need a different scope than its parent's by dagger convention
@Subcomponent(
        modules = {
                ApiModule.class,
                RestModule.class,
                ImageModule.class,
                ConverterModule.class
        }
)
public interface NetworkingComponent {

    // We must expose the same dependencies as in the parent component
    Application application();

    Resources resources();

    Converter converter();

    InputMethodManager inputMethodManager();

    NotificationManager notificationManager();

    AlarmHandler alarmHandler();

    Preferences preferences();

    SeriesLoader seriesLoader();

    @EpisodeList
    ModelConverter provideEpisodeListConverter();

}
