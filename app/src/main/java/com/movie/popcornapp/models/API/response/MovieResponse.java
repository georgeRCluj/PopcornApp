package com.movie.popcornapp.models.API.response;

import com.google.gson.annotations.SerializedName;

/**
 * @author george.radu on 2019-07-08.
 */
public class MovieResponse {
    @SerializedName("vote_average")
    private double voteAverage;

    @SerializedName("title")
    private String title;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("overview")
    private String overview;

    @SerializedName("vote_count")
    private int voteCount;

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public int getVoteCount() {
        return voteCount;
    }
}
