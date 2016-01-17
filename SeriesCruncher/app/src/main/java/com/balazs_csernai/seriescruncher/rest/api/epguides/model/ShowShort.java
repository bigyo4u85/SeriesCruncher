package com.balazs_csernai.seriescruncher.rest.api.epguides.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Balazs_Csernai on 2016.01.08..
 */
public class ShowShort {
    @SerializedName("first_episode")
    private String firstEpisodeURL;
    @SerializedName("epguides_url")
    private String epGuidesURL;
    @SerializedName("episodes")
    private String episodesURL;
    @SerializedName("imdb_id")
    private String imdbID;
    @SerializedName("title")
    private String title;
    @SerializedName("epguide_name")
    private String epGuidesName;
    @SerializedName("next_episode")
    private String nextEpisodeURL;
    @SerializedName("last_episode")
    private String lastEpisodeURL;

    public String getTitle() {
        return title;
    }
}
