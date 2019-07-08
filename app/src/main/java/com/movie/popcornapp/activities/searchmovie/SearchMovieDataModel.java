package com.movie.popcornapp.activities.searchmovie;

/**
 * @author george.radu on 2019-07-08.
 */
public class SearchMovieDataModel {
    private String validationErrorMessage;
    private String movieListEmptyErrorMessage;

    SearchMovieDataModel(String validationErrorMessage, String movieListEmptyErrorMessage) {
        this.validationErrorMessage = validationErrorMessage;
        this.movieListEmptyErrorMessage = movieListEmptyErrorMessage;
    }

    String getValidationErrorMessage() {
        return validationErrorMessage;
    }

    String getNoMovieFoundErrorMessage() {
        return movieListEmptyErrorMessage;
    }
}
