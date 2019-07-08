package com.movie.popcornapp.accesslayer;

import com.movie.popcornapp.BuildConfig;
import com.movie.popcornapp.models.API.response.SearchMoviesResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * @author george.radu on 2019-07-08.
 */
public interface MoviesManagementInterface {

    @GET("/3/search/movie?api_key=" + BuildConfig.TheMovieDBApiKey)
    void requestMoviesList(@Query("query") String movieName, Callback<SearchMoviesResponse> callback);
}
