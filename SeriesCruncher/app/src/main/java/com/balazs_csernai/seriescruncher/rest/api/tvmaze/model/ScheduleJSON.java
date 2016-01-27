package com.balazs_csernai.seriescruncher.rest.api.tvmaze.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Balázs on 2016.01.23..
 */
public class ScheduleJSON implements Schedule {

    @SerializedName("time")
    private String time;

    @SerializedName("days")
    private List<String> days;

    @Override
    public String getTime() {
        return time;
    }

    @Override
    public List<String> getDays() {
        return days;
    }
}
