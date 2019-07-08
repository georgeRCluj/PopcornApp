package com.movie.popcornapp.activities.searchmovie;

import androidx.annotation.Nullable;

import com.movie.popcornapp.components.ResponseCallback;
import com.movie.popcornapp.models.API.response.SearchMoviesResponse;
import com.movie.popcornapp.workers.searchmovies.SearchMoviesWorkerInterface;

/**
 * @author george.radu on 2019-07-08.
 */
public class SearchMoviesTasksRepository implements SearchMovieTasksRepositoryInterface {

    //region Interfaces
    private SearchMoviesWorkerInterface searchMoviesWorker;
    //endregion

    //region Constructors
    public SearchMoviesTasksRepository(@Nullable SearchMoviesWorkerInterface searchMoviesWorker) {
        this.searchMoviesWorker = searchMoviesWorker;
    }

    @Override
    public void getMoviesList(String movieToSearch, ResponseCallback<SearchMoviesResponse> callback) {
        searchMoviesWorker.searchMovies(movieToSearch, (success, message, data) -> {
            if (success && data != null) {
                callback.response(true, null, data);
            } else {
                callback.response(false, message, null);
            }
        });

    }
    //endregion
}
