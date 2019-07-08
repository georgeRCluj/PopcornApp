package com.movie.popcornapp.activities.movieslist;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

/**
 * @author george.radu on 2019-07-08.
 */
public class MoviesListViewModel extends ViewModel {

    private MoviesListDataModel moviesListDataModel;

    //region Observables
    public ObservableField<String> searchText = new ObservableField<>();
    //endregion;

    public MoviesListViewModel(MoviesListDataModel moviesListDataModel) {
        this.moviesListDataModel = moviesListDataModel;
        setTitle();
    }

    private void setTitle() {
        searchText.set(moviesListDataModel.getSearchText());
    }
}
