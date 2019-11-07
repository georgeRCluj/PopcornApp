package com.movie.popcornapp.activities.movieslist;

import com.movie.popcornapp.models.API.response.MovieResponse;

import java.util.List;

/**
 * @author george.radu on 2019-07-08.
 */
public class MoviesListDataModel {

    private String searchText;
    private List<MovieResponse> moviesList;

    MoviesListDataModel(String searchText, List<MovieResponse> moviesList) {
        this.searchText = searchText;
        this.moviesList = moviesList;
    }

    public String getSearchText() {
        return searchText;
    }

    List<MovieResponse> getMoviesList() {
        return moviesList;
    }
}
