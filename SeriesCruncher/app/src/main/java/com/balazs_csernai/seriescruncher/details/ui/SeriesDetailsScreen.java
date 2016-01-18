package com.balazs_csernai.seriescruncher.details.ui;

import com.balazs_csernai.seriescruncher.rest.api.epguides.model.Episode;

import java.util.List;
import java.util.Map;

/**
 * Created by Erik_Markus_Kramli on 2016-01-13.
 */
public interface SeriesDetailsScreen {

    void onCreate();

    void show(String url);

    void show(Map<Integer, List<Episode>> seasonsMap);
}
