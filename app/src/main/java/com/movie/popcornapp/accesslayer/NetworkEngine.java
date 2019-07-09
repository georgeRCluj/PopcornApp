package com.movie.popcornapp.accesslayer;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.movie.popcornapp.BuildConfig;
import com.movie.popcornapp.R;
import com.movie.popcornapp.extensions.GetErrorMessage;
import com.movie.popcornapp.infrastructure.App;
import com.movie.popcornapp.models.API.response.SearchMoviesResponse;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.converter.GsonConverter;
import retrofit.mime.TypedByteArray;

/**
 * @author george.radu on 2019-07-08.
 */
public class NetworkEngine {

    //region Properties
    private static final String ENDPOINT_URL = BuildConfig.Endpoint;

    private static NetworkEngine _instance;
    private MoviesManagementInterface moviesManagementInterface;
    //endregion;

    //region Instance
    public NetworkEngine() {
        Gson gson = new GsonBuilder().create();
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT_URL)
                .setConverter(new GsonConverter(gson))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setRequestInterceptor(requestFacade -> requestFacade.addHeader("Content-Type", "application/json"))
                .build();

        moviesManagementInterface = restAdapter.create(MoviesManagementInterface.class);
    }

    public static NetworkEngine getInstance() {
        if (_instance == null) {
            _instance = new NetworkEngine();
        }
        return _instance;
    }
    //endregion

    //region Network Engine Helpers
    public String processErrorResponse(RetrofitError error) {
        Context c = App.getContext();
        String errMessage = c.getResources().getString(R.string.dialog_general_error_response_message);
        String jsonString = "";
        if (error.getResponse() != null) {
            try {
                if (error.getResponse().getBody() != null) {
                    jsonString = new String(((TypedByteArray) error.getResponse().getBody()).getBytes());
                }
                GetErrorMessage errorMessage = new GetErrorMessage();
                errMessage = errorMessage.processErrorMessage(jsonString);
            } catch (Exception ex) {
                errMessage = c.getResources().getString(R.string.dialog_general_error_response_message);
            }
            if (errMessage.length() == 0) {
                errMessage = c.getResources().getString(R.string.dialog_general_error_response_message);
            }
        }
        return errMessage;
    }
    //endregion;

    //region CardsManagementInterface
    public void getMoviesList(String movieName, Callback<SearchMoviesResponse> callback) {
        if (moviesManagementInterface == null) {
            return;
        }
        moviesManagementInterface.requestMoviesList(movieName, callback);
    }
    //endregion;

}