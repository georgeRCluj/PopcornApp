package com.movie.popcornapp.models.API.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * @author george.radu on 2019-07-08.
 */
public class SearchMoviesResponse {
    @SerializedName("page")
    private int page;

    @SerializedName("total_results")
    private int totalResults;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("results")
    private ArrayList<MovieResponse> moviesResponse;

    public int getPage() {
        return page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public ArrayList<MovieResponse> getMoviesResponse() {
        return moviesResponse;
    }
}
