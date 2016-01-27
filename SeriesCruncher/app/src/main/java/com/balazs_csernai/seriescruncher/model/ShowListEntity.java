package com.balazs_csernai.seriescruncher.model;

import java.util.List;

/**
 * Created by ErikKramli on 2016.01.17..
 */
public class ShowListEntity implements ShowListModel {

    private List<ShowModel> shows;

    public ShowListEntity(List<ShowModel> shows) {
        this.shows = shows;
    }

    @Override
    public List<ShowModel> getShowList() {
        return shows;
    }
}
