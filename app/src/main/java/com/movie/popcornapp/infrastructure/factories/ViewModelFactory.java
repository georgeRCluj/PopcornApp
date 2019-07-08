package com.movie.popcornapp.infrastructure.factories;

import android.content.res.Resources;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.movie.popcornapp.activities.movieslist.MoviesListDataModel;
import com.movie.popcornapp.activities.movieslist.MoviesListViewModel;
import com.movie.popcornapp.activities.searchmovie.SearchMovieDataModel;
import com.movie.popcornapp.activities.searchmovie.SearchMovieViewModel;
import com.movie.popcornapp.activities.searchmovie.SearchMoviesTasksRepository;

/**
 * @author george.radu on 2019-07-07.
 */

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    //region Properties
    private Object tasksRepository;

    @Nullable
    private Object data;
    //endregion;

    public ViewModelFactory(@Nullable Object tasksRepository, @Nullable Object data) {
        this.tasksRepository = tasksRepository;
        this.data = data;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.getSimpleName().equals(SearchMovieViewModel.class.getSimpleName())) {
            return (T) new SearchMovieViewModel((SearchMoviesTasksRepository) tasksRepository, (SearchMovieDataModel) data);
        }

        if (modelClass.getSimpleName().equals(MoviesListViewModel.class.getSimpleName())) {
            return (T) new MoviesListViewModel((MoviesListDataModel) data);
        }

        throw new Resources.NotFoundException("ViewModelFactory.create should not reach this point");
    }
}
