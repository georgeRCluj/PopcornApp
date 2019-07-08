package com.movie.popcornapp.workers.searchmovies;

import com.movie.popcornapp.components.ResponseCallback;
import com.movie.popcornapp.models.API.response.SearchMoviesResponse;

/**
 * @author george.radu on 2019-07-08.
 */

public interface SearchMoviesWorkerInterface {
    void searchMovies(String movieToSearch, ResponseCallback<SearchMoviesResponse> callback);
}
