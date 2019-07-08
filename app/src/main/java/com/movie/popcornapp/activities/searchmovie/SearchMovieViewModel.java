package com.movie.popcornapp.activities.searchmovie;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;

import com.movie.popcornapp.components.ResponseCallback;
import com.movie.popcornapp.models.API.response.SearchMoviesResponse;

/**
 * @author george.radu on 2019-07-08.
 */
public class SearchMovieViewModel extends ViewModel {

    private SearchMovieTasksRepositoryInterface searchMovieTasksRepositoryInterface;

    public SearchMovieViewModel(SearchMovieTasksRepositoryInterface searchMovieTasksRepositoryInterface) {
        this.searchMovieTasksRepositoryInterface = searchMovieTasksRepositoryInterface;
        this.searchMovieTasksRepositoryInterface.getMoviesList("batman", new ResponseCallback<SearchMoviesResponse>() {
            @Override
            public void response(Boolean success, @Nullable String message, @Nullable SearchMoviesResponse data) {
                Log.d("API_CALLS", "response: " + data);
            }
        });
    }
}
