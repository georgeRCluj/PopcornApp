package com.movie.popcornapp.activities.moviedetails;

import android.os.Bundle;
import android.view.Gravity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.movie.popcornapp.R;
import com.movie.popcornapp.activities.movieslist.MoviesListActivity;
import com.movie.popcornapp.databinding.ActivityMovieDetailsBinding;
import com.movie.popcornapp.extensions.ActivityUtils;
import com.movie.popcornapp.extensions.AnimationUtils;
import com.movie.popcornapp.models.API.response.MovieResponse;

import java.util.ArrayList;

/**
 * @author george.radu on 2019-07-08.
 */
public class MovieDetailsActivity extends AppCompatActivity {
    static final String MOVIE_DETAILS_FRAGMENT_SELECTED_MOVIE_KEY = "movie_details_fragment_selected_movie_key";

    //region Lifecycle methods
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set layout
        AnimationUtils.setAnimation(this, Gravity.RIGHT, 400);
        ActivityMovieDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details);

        //Customise Toolbar
        setupToolbar(binding);

        // Retrieve data from previous activity
        ArrayList<MovieResponse> selectedMovie = getIntent().getParcelableArrayListExtra(MoviesListActivity.MOVIES_LIST_GO_TO_MOVIE_DETAILS_KEY);

        //Initial View (fragment)
        findOrCreateViewFragment(binding, selectedMovie);
    }
    //endregion

    //region Setup
    private void setupToolbar(ActivityMovieDetailsBinding binding) {
        setSupportActionBar(binding.toolbarMovieDetails);
        binding.backArrow.setOnClickListener(v -> finish());
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);
    }

    private void findOrCreateViewFragment(ActivityMovieDetailsBinding binding, ArrayList<MovieResponse> selectedMovie) {
        MovieDetailsFragment movieDetailsFragment = (MovieDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container_movie_details);
        if (movieDetailsFragment == null) {
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(),
                    MovieDetailsFragment.newInstance(selectedMovie),
                    binding.fragmentContainerMovieDetails.getId(),
                    false
            );
        }
    }
    //endregion
}
