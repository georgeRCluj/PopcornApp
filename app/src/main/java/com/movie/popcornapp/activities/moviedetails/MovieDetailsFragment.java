package com.movie.popcornapp.activities.moviedetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.movie.popcornapp.R;
import com.movie.popcornapp.databinding.FragmentMovieDetailsBinding;
import com.movie.popcornapp.infrastructure.factories.ViewModelFactory;
import com.movie.popcornapp.models.API.response.MovieResponse;

import java.util.ArrayList;

/**
 * @author george.radu on 2019-07-08.
 */
public class MovieDetailsFragment extends Fragment {

    //region Properties
    private FragmentMovieDetailsBinding binding;
    //endregion

    //region Lifecycle
    public MovieDetailsFragment() {
    }

    static MovieDetailsFragment newInstance(ArrayList<MovieResponse> selectedMovie) {
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(MovieDetailsActivity.MOVIE_DETAILS_FRAGMENT_SELECTED_MOVIE_KEY, selectedMovie);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, null, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // get arguments and inject in xml
        MovieResponse selectedMovie = null;
        if (getArguments() != null) {
            selectedMovie = (MovieResponse) getArguments().getParcelableArrayList(MovieDetailsActivity.MOVIE_DETAILS_FRAGMENT_SELECTED_MOVIE_KEY).get(0);
        }

        MovieDetailsViewModel viewModel = ViewModelProviders.of(this, new ViewModelFactory(null, selectedMovie)).get(MovieDetailsViewModel.class);
        binding.setViewModel(viewModel);
    }
    //endregion
}
