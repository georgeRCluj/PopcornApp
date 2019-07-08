package com.movie.popcornapp.activities.movieslist;

import android.os.Bundle;
import android.view.Gravity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.movie.popcornapp.R;
import com.movie.popcornapp.activities.searchmovie.SearchMovieActivity;
import com.movie.popcornapp.databinding.ActivityMoviesListBinding;
import com.movie.popcornapp.databinding.ActivitySearchMovieBinding;
import com.movie.popcornapp.extensions.ActivityUtils;
import com.movie.popcornapp.extensions.AnimationUtils;
import com.movie.popcornapp.models.API.response.MovieResponse;

import java.util.List;

/**
 * @author george.radu on 2019-07-08.
 */
public class MoviesListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set layout
        AnimationUtils.setAnimation(this, Gravity.RIGHT, 400);
        ActivityMoviesListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_movies_list);

        //Customise Toolbar
//        setupToolbar(binding);

        //Initial View (fragment)
//        findOrCreateViewFragment(binding);
        List<MovieResponse> loadedMovies = getIntent().getParcelableArrayListExtra(SearchMovieActivity.SEARCH_MOVIE_FOUND_MOVIES_LIST_KEY);
    }

    //region Setup
//    private void setupToolbar(ActivityTransferFundsBinding binding) {
//        setSupportActionBar(binding.toolbarTransferFunds);
//        binding.toolbarTransferFunds.setNavigationOnClickListener(v -> finish());
//        ActivityUtils.showToolbarButtons(getSupportActionBar());
//    }
}
