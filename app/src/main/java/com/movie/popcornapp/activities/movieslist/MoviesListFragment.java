package com.movie.popcornapp.activities.movieslist;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.movie.popcornapp.R;
import com.movie.popcornapp.activities.searchmovie.SearchMovieViewModel;
import com.movie.popcornapp.databinding.FragmentMoviesListBinding;
import com.movie.popcornapp.infrastructure.factories.ViewModelFactory;
import com.movie.popcornapp.models.API.response.MovieResponse;
import com.movie.popcornapp.models.API.response.SearchMoviesResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author george.radu on 2019-07-08.
 */
public class MoviesListFragment extends Fragment {
    /**
     * OnSearchMovieFragmentInteractionListener
     */
    public interface OnMoviesListFragmentInteractionListener {
        void onGoToDetailsList(String searchText, MovieResponse movieResponse);
    }

    //region Properties
    private OnMoviesListFragmentInteractionListener listener;
    private FragmentMoviesListBinding binding;
    private MoviesListViewModel viewModel;
    private MoviesListAdapter moviesAdapter;
    //endregion

    //region Lifecycle
    public MoviesListFragment() {
    }

    static MoviesListFragment newInstance(String searchText, List<MovieResponse> loadedMovies) {
        MoviesListFragment fragment = new MoviesListFragment();
        Bundle args = new Bundle();
        args.putString(MoviesListActivity.MOVIES_LIST_SEARCH_TEXT_KEY, searchText);
        args.putParcelableArrayList(MoviesListActivity.MOVIES_LIST_MOVIES_KEY, (ArrayList<? extends Parcelable>) loadedMovies);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies_list, null, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Setup movies adapter
        moviesAdapter = new MoviesListAdapter();
        binding.foundMoviesRecyclerView.setAdapter(moviesAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // get arguments
        List<MovieResponse> moviesList = null;
        String searchText = null;
        if (getArguments() != null) {
            moviesList = getArguments().getParcelableArrayList(MoviesListActivity.MOVIES_LIST_MOVIES_KEY);
            searchText = getArguments().getString(MoviesListActivity.MOVIES_LIST_SEARCH_TEXT_KEY);
        }

        //ViewModel
        MoviesListDataModel moviesListDataModel = new MoviesListDataModel(searchText, moviesList);
        viewModel = ViewModelProviders.of(this, new ViewModelFactory(null, moviesListDataModel)).get(MoviesListViewModel.class);
        binding.setViewModel(viewModel);

        // Setup observers
        setupObservers();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnMoviesListFragmentInteractionListener) {
            listener = (OnMoviesListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnMoviesListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        listener = null;
    }
    //endregion

    //region Observers
    private void setupObservers() {
        // adapter
        viewModel.movies.removeObservers(this);
        Observer<List<MoviesListItemViewModel>> loadRecyclerViewObserver = ((@Nullable List<MoviesListItemViewModel> movies) -> {
            if (movies != null) {
                moviesAdapter.submitList(movies);
            }
        });
        viewModel.movies.observe(this, loadRecyclerViewObserver);

//        // card selected
//        viewModel.selectedItem.removeObservers(this);
//        Observer<TransferFundsSelectCardDataModel> selectCardDataModelObserver = ((@Nullable TransferFundsSelectCardDataModel selectedCard) -> {
//            if (selectedCard != null) {
//                selectCardInteractionListener.onCardSelected(selectedCard);
//            }
//        });
//        viewModel.selectedItem.observe(this, selectCardDataModelObserver);
    }
    //endregion

}
