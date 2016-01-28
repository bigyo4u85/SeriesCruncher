package com.balazs_csernai.seriescruncher.utils.converter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Balazs_Csernai on 2016.01.27..
 */
@Module
public class ConverterModule {

    @Provides
    @SeriesDetails
    ModelConverter provideSeriesDetailsConverter(SeriesDetailsConverter converter) {
        return converter;
    }

    @Provides
    @EpisodeDetails
    ModelConverter provideEpisodeDetailsConverter(EpisodeDetailsConverter converter) {
        return converter;
    }

    @Provides
    @EpisodeList
    ModelConverter provideEpisodeListConverter(EpisodeListConverter converter) {
        return converter;
    }

    @Provides
    @EpisodeListItem
    ModelConverter provideEpisodeListItemConverter(EpisodeListItemConverter converter) {
        return converter;
    }
}
