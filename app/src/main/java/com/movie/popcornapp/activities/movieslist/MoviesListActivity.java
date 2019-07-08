package com.movie.popcornapp.activities.movieslist;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.movie.popcornapp.R;
import com.movie.popcornapp.activities.moviedetails.MovieDetailsActivity;
import com.movie.popcornapp.activities.searchmovie.SearchMovieActivity;
import com.movie.popcornapp.databinding.ActivityMoviesListBinding;
import com.movie.popcornapp.extensions.ActivityUtils;
import com.movie.popcornapp.extensions.AnimationUtils;
import com.movie.popcornapp.models.API.response.MovieResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author george.radu on 2019-07-08.
 */
public class MoviesListActivity extends AppCompatActivity implements MoviesListFragment.OnMoviesListFragmentInteractionListener {

    static final String MOVIES_LIST_FRAGMENT_MOVIES_KEY = "movies_list_fragment_movies_key";
    static final String MOVIES_LIST_FRAGMENT_SEARCH_TEXT_KEY = "movies_list_fragment_search_text_key";
    public static final String MOVIES_LIST_GO_TO_MOVIE_DETAILS_KEY = "movies_list_fragment_go_to_movie_details_key";

    //region Lifecycle methods
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set layout
        AnimationUtils.setAnimation(this, Gravity.RIGHT, 400);
        ActivityMoviesListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_movies_list);

        //Customise Toolbar
        setupToolbar(binding);

        // Retrieve data from previous activity
        String searchText = getIntent().getStringExtra(SearchMovieActivity.SEARCH_MOVIE_SEARCH_TEXT_KEY);
        List<MovieResponse> loadedMovies = getIntent().getParcelableArrayListExtra(SearchMovieActivity.SEARCH_MOVIE_FOUND_MOVIES_LIST_KEY);

        //Initial View (fragment)
        findOrCreateViewFragment(binding, searchText, loadedMovies);
    }
    //endregion

    //region Setup
    private void setupToolbar(ActivityMoviesListBinding binding) {
        setSupportActionBar(binding.toolbarMoviesList);
        binding.backArrow.setOnClickListener(v -> finish());
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);
    }

    private void findOrCreateViewFragment(ActivityMoviesListBinding binding, String searchText, List<MovieResponse> loadedMovies) {
        MoviesListFragment searchMovieFragment = (MoviesListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container_movies_list);
        if (searchMovieFragment == null) {
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(),
                    MoviesListFragment.newInstance(searchText, loadedMovies),
                    binding.fragmentContainerMoviesList.getId(),
                    false
            );
        }
    }
    //endregion

    //region MoviesListFragment.OnMoviesListFragmentInteractionListener interface
    @Override
    public void onGoToMovieDetails(MoviesListItemViewModel movieSelected) {
        // we have to get the data from viewModel and pass it back to a MovieResponse model, since this implements Parcelable
        MovieResponse selectedMovie = new MovieResponse(
                movieSelected.getTitle(),
                movieSelected.getReleaseDate(),
                movieSelected.getUrl(),
                movieSelected.getBackdropPath(),
                movieSelected.getAverageRatingAsDouble(),
                movieSelected.getVoteCountAsInt(),
                movieSelected.getOverView()
        );

        // wrap the selected item into an array list, since it is the only way to send it through parcelable
        ArrayList<MovieResponse> selectedMovieList = new ArrayList<>();
        selectedMovieList.add(selectedMovie);
        startActivity(new Intent(this, MovieDetailsActivity.class).putParcelableArrayListExtra(MOVIES_LIST_GO_TO_MOVIE_DETAILS_KEY, selectedMovieList));
    }
    //endregion
}
