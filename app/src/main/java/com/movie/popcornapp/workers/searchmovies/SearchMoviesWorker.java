package com.movie.popcornapp.workers.searchmovies;

import com.movie.popcornapp.accesslayer.NetworkEngine;
import com.movie.popcornapp.components.ResponseCallback;
import com.movie.popcornapp.models.API.response.SearchMoviesResponse;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * @author george.radu on 2019-07-08.
 */
public class SearchMoviesWorker implements SearchMoviesWorkerInterface {

    private String noInternetConnectionMessage;

    public SearchMoviesWorker(String noInternetConnectionMessage) {
        this.noInternetConnectionMessage = noInternetConnectionMessage;
    }

    //region SearchMoviesWorkerInterface
    @Override
    public void searchMovies(String movieToSearch, ResponseCallback<SearchMoviesResponse> callback) {
        new NetworkEngine().getMoviesList(movieToSearch, new Callback<SearchMoviesResponse>() {
            @Override
            public void success(SearchMoviesResponse searchMoviesResponse, Response response) {
                if (response.getStatus() == 200) {
                    callback.response(true, null, searchMoviesResponse);
                } else {
                    callback.response(false, response.getReason(), null);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                if (error.getKind() == RetrofitError.Kind.NETWORK) {
                    callback.response(false, noInternetConnectionMessage, null);
                } else {
                    String errorMessage = NetworkEngine.getInstance().processErrorResponse(error);
                    callback.response(false, errorMessage, null);
                }
            }
        });
    }
    //endregion

}
