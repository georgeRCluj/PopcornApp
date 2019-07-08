package com.movie.popcornapp.models.API.request;

import com.google.gson.annotations.SerializedName;

/**
 * @author george.radu on 2019-07-08.
 */
public class SearchMovieRequest {

    @SerializedName("search_query")
    public String searchQuery;

}
