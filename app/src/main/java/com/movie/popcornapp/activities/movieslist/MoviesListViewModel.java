package com.movie.popcornapp.activities.movieslist;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.movie.popcornapp.models.API.response.MovieResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author george.radu on 2019-07-08.
 */
public class MoviesListViewModel extends ViewModel implements MoviesListItemViewModelInterface {

    private MoviesListDataModel moviesListDataModel;

    //region Observables
    public ObservableField<String> searchText = new ObservableField<>();
    public MutableLiveData<List<MoviesListItemViewModel>> movies = new MutableLiveData<>();
    //endregion;

    //region Selection commands
    public MutableLiveData<MoviesListItemViewModel> selectedItem = new MutableLiveData<>();
    //endregion

    public MoviesListViewModel(MoviesListDataModel moviesListDataModel) {
        this.moviesListDataModel = moviesListDataModel;
        setTitle();
        loadFilteredMovies();
    }

    private void setTitle() {
        searchText.set(moviesListDataModel.getSearchText());
    }

    private void loadFilteredMovies() {
        // there are movies with no poster or no release date; we filter them out
        List<MoviesListItemViewModel> list = new ArrayList<>();
        for (MovieResponse movie : moviesListDataModel.getMoviesList()) {
            if (movie.getReleaseDate().length() > 0 && movie.getPosterPath() != null) {
                list.add(new MoviesListItemViewModel(
                        movie.getId(),
                        movie.getPosterPath(),
                        movie.getBackdropPath(),
                        movie.getTitle(),
                        movie.getReleaseDate(),
                        movie.getReleaseYear(),
                        movie.getAverageRating(),
                        movie.getVoteCount(),
                        movie.getOverview(),
                        this));
            }
        }
        movies.postValue(list);
    }

    @Override
    public void itemSelected(MoviesListItemViewModel movieItemSelected) {
        selectedItem.postValue(movieItemSelected);
    }
}
