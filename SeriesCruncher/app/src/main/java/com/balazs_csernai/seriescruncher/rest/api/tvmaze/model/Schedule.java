package com.balazs_csernai.seriescruncher.rest.api.tvmaze.model;

import java.util.List;

/**
 * Created by Balázs on 2016.01.23..
 */
public interface Schedule {

    String getTime();

    List<String> getDays();
}
