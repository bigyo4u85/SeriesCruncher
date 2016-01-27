package com.balazs_csernai.seriescruncher.rest.api.epguides.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Balazs_Csernai on 2016.01.08..
 */
public class SeriesJson implements Series, Comparable<SeriesJson> {

    @SerializedName("imdb_id")
    private String imdbId;

    @SerializedName("title")
    private String title;

    @SerializedName("epguide_name")
    private String epGuideName;

    @SerializedName("epguides_url")
    private String epGuidesURL;

    @SerializedName("episodes")
    private String episodesURL;

    @SerializedName("first_episode")
    private String firstEpisodeURL;

    @SerializedName("next_episode")
    private String nextEpisodeURL;

    @SerializedName("last_episode")
    private String lastEpisodeURL;

    @Override
    public String getImdbId() {
        return imdbId;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getEpGuideName() {
        return epGuideName;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        SeriesJson otherShowJson = (SeriesJson) other;

        return imdbId.equals(otherShowJson.imdbId);
    }

    @Override
    public int hashCode() {
        return imdbId.hashCode();
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public int compareTo(@NonNull SeriesJson another) {
        return title.compareTo(another.title);
    }
}
