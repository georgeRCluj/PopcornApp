package com.movie.popcornapp.activities.moviedetails

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.movie.popcornapp.models.API.response.MovieResponse

class MovieDetailsViewModel(
        private val movieResponse: MovieResponse
) : ViewModel() {

    //region Observables
    var title = ObservableField<String>()
    var year = ObservableField<String>()
    var url = ObservableField<String>()
    var averageRating = ObservableField<String>()
    var voteCount = ObservableField<String>()
    var overview = ObservableField<String>()
    //endregion;

    fun setMovieDetails() {
        title.set(movieResponse.title)
        year.set(movieResponse.releaseYear.toString())
        url.set(movieResponse.backdropPath)
        averageRating.set(movieResponse.averageRating.toString())
        voteCount.set(String.format("%,d", movieResponse.voteCount))
        overview.set(movieResponse.overview)
    }
}