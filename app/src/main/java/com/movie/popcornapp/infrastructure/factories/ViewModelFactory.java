package com.movie.popcornapp.infrastructure.factories;

import android.content.res.Resources;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.movie.popcornapp.activities.searchmovie.SearchMovieViewModel;
import com.movie.popcornapp.activities.searchmovie.SearchMoviesTasksRepository;

/**
 * @author george.radu on 2019-07-07.
 */

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    //region Properties
    private Object tasksRepository;
    //endregion;

    public ViewModelFactory(@Nullable Object tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.getSimpleName().equals(SearchMovieViewModel.class.getSimpleName())) {
            return (T) new SearchMovieViewModel((SearchMoviesTasksRepository) tasksRepository);
        }

        throw new Resources.NotFoundException("ViewModelFactory.create should not reach this point");
    }
}
