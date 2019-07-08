package com.movie.popcornapp.activities.searchmovie;

import com.movie.popcornapp.components.ResponseCallback;
import com.movie.popcornapp.models.API.response.SearchMoviesResponse;

/**
 * @author george.radu on 2019-07-08.
 */
public interface SearchMovieTasksRepositoryInterface {
    void getMoviesList(String movieToSearch, ResponseCallback<SearchMoviesResponse> callback);
}