package com.balazs_csernai.seriescruncher.model;

import java.util.List;

/**
 * Created by ErikKramli on 2016.01.17..
 */
public class ShowsEntity implements ShowsModel {

    private List<ShowModel> shows;

    public ShowsEntity(List<ShowModel> shows) {
        this.shows = shows;
    }

    @Override
    public List<ShowModel> getShows() {
        return shows;
    }
}
