package com.movie.popcornapp.activities.moviedetails;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.movie.popcornapp.models.API.response.MovieResponse;

/**
 * @author george.radu on 2019-07-08.
 */
public class MovieDetailsViewModel extends ViewModel {
    private MovieResponse movieResponse;

    //region Observables
    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<String> year = new ObservableField<>();
    public ObservableField<String> url = new ObservableField<>();
    public ObservableField<String> averageRating = new ObservableField<>();
    public ObservableField<String> voteCount = new ObservableField<>();
    public ObservableField<String> overview = new ObservableField<>();
    //endregion;

    public MovieDetailsViewModel(MovieResponse movieResponse) {
        this.movieResponse = movieResponse;
        setMovieDetails();
    }

    private void setMovieDetails() {
        title.set(movieResponse.getTitle());
        year.set(String.valueOf(movieResponse.getReleaseYear()));
        url.set(movieResponse.getBackdropPath());
        averageRating.set(String.valueOf(movieResponse.getAverageRating()));
        voteCount.set(String.format("%,d", movieResponse.getVoteCount()));
        overview.set(movieResponse.getOverview());
    }
}
