package com.movie.popcornapp.activities.searchmovie;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Gravity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.movie.popcornapp.R;
import com.movie.popcornapp.activities.movieslist.MoviesListActivity;
import com.movie.popcornapp.databinding.ActivitySearchMovieBinding;
import com.movie.popcornapp.extensions.ActivityUtils;
import com.movie.popcornapp.extensions.AnimationUtils;
import com.movie.popcornapp.models.API.response.SearchMoviesResponse;

import java.util.ArrayList;

/**
 * @author george.radu on 2019-07-07.
 */
public class SearchMovieActivity extends AppCompatActivity implements SearchMovieFragment.OnSearchMovieFragmentInteractionListener {

    static final String SEARCH_MOVIE_FOUND_MOVIES_LIST_KEY = "search_movie_found_movies_list_key";

    //region Lifecycle
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set layout
        AnimationUtils.setAnimation(this, Gravity.RIGHT, 400);
        ActivitySearchMovieBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_search_movie);

        //Initial View (fragment)
        findOrCreateViewFragment(binding);
    }
    //endregion

    //region Setup
    private void findOrCreateViewFragment(ActivitySearchMovieBinding binding) {
        SearchMovieFragment searchMovieFragment = (SearchMovieFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container_search_movie);
        if (searchMovieFragment == null) {
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(),
                    SearchMovieFragment.newInstance(),
                    binding.fragmentContainerSearchMovie.getId(),
                    false
            );
        }
    }
    //endregion

    //region SearchMovieFragment.OnSearchMovieFragmentInteractionListener interface
    @Override
    public void onGoToMoviesList(SearchMoviesResponse searchMoviesResponse) {
        startActivity(new Intent(this, MoviesListActivity.class)
                .putParcelableArrayListExtra(SEARCH_MOVIE_FOUND_MOVIES_LIST_KEY, searchMoviesResponse.getMoviesResponse())
        );
    }
    //endregion

}
