package com.movie.popcornapp.activities.searchmovie;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.movie.popcornapp.R;
import com.movie.popcornapp.databinding.FragmentSearchMovieBinding;
import com.movie.popcornapp.extensions.KeyboardUtils;

import java.util.ArrayList;

/**
 * @author george.radu on 2019-07-08.
 */
public class SearchMovieFragment extends Fragment {

    //region Properties
    private FragmentSearchMovieBinding binding;
    //endregion

    //region Lifecycle
    static SearchMovieFragment newInstance() {
        return new SearchMovieFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_movie, null, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Adjust edit field behavior
        adjustSearchField();

        // Load carousel
        ArrayList<Integer> moviesList = new ArrayList<>();
        moviesList.add(R.drawable.solo_star_wars);
        moviesList.add(R.drawable.the_meg);
        moviesList.add(R.drawable.the_purge);
        moviesList.add(R.drawable.truth_or_dare);

        LinearLayoutManager moviesCarouselLayoutManager = new LinearLayoutManager(getActivity());
        moviesCarouselLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.trendingMoviesRecyclerView.setLayoutManager(moviesCarouselLayoutManager);
        binding.trendingMoviesRecyclerView.setHasFixedSize(true);
        binding.trendingMoviesRecyclerView.setAdapter(new MoviesCarouselAdapter(moviesList));

    }
    //endregion

    //region Adjust search field
    private void adjustSearchField() {
        binding.findTheMovieContainer.setOnClickListener(v -> {
            binding.findTheMovieContainer.setVisibility(View.INVISIBLE);
            binding.searchEditTextContainer.setVisibility(View.VISIBLE);
            binding.searchMovieEditText.requestFocus();
            KeyboardUtils.showKeyboard(getActivity(), binding.searchMovieEditText, true);
            addSearchMovieEditTextChangeListener();
        });
    }

    private void addSearchMovieEditTextChangeListener() {
        binding.searchMovieEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    binding.searchEditTextContainer.setVisibility(View.INVISIBLE);
                    binding.findTheMovieContainer.setVisibility(View.VISIBLE);
                    KeyboardUtils.showKeyboard(getActivity(), binding.searchMovieEditText, false);
                    binding.searchMovieEditText.removeTextChangedListener(this);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
    //endregion
}
