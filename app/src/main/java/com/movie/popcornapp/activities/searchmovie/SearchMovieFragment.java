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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.movie.popcornapp.R;
import com.movie.popcornapp.components.LoadingCommandAndMessage;
import com.movie.popcornapp.databinding.FragmentSearchMovieBinding;
import com.movie.popcornapp.extensions.CustomAlertDialog;
import com.movie.popcornapp.extensions.KeyboardUtils;
import com.movie.popcornapp.extensions.LoadingProgressDialog;
import com.movie.popcornapp.extensions.StringUtils;
import com.movie.popcornapp.infrastructure.factories.ViewModelFactory;
import com.movie.popcornapp.workers.searchmovies.SearchMoviesWorker;

import java.util.ArrayList;

/**
 * @author george.radu on 2019-07-08.
 */
public class SearchMovieFragment extends Fragment {

    //region Properties
    private FragmentSearchMovieBinding binding;
    private SearchMovieViewModel viewModel;
    private LoadingProgressDialog loadingProgressDialog;
    private CustomAlertDialog customAlertDialog;
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

        //Create Loading Progress Dialog
        loadingProgressDialog = new LoadingProgressDialog(getActivity());

        // Populate carousel list & load carousel on UI
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

        // Workers
        SearchMoviesWorker searchMoviesWorker = new SearchMoviesWorker(getString(R.string.no_internet_connection_dialog_message));

        // Manage Tasks Repository
        SearchMoviesTasksRepository searchMoviesTasksRepository = new SearchMoviesTasksRepository(searchMoviesWorker);

        //ViewModel
        SearchMovieDataModel searchMovieDataModel = new SearchMovieDataModel(
                getResources().getString(R.string.activity_search_movie_please_type_a_movie_title),
                getResources().getString(R.string.activity_search_movie_no_movie_found)
        );
        viewModel = ViewModelProviders.of(this, new ViewModelFactory(searchMoviesTasksRepository, searchMovieDataModel)).get(SearchMovieViewModel.class);
        binding.setViewModel(viewModel);

        //Setup observers
        setupObservers();
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

    //region Observers
    private void setupObservers() {

        // loading (progress dialogs)
        viewModel.loadingCommands.removeObservers(this);
        Observer<LoadingCommandAndMessage> loadingCommandAndMessageObserver = ((@Nullable LoadingCommandAndMessage loadingCommand) -> {
            if (loadingCommand != null) {
                switch (loadingCommand.command) {
                    case show:
                        loadingProgressDialog.show(getString(R.string.loading_progress_dialog_message_please_wait));
                        break;

                    case hide:
                        loadingProgressDialog.dismiss();
                        break;
                }
            }
        });
        viewModel.loadingCommands.observe(this, loadingCommandAndMessageObserver);

        // error dialogs
        viewModel.errorDialogCommand.removeObservers(this);
        Observer<String> displayErrorMessageObserver = ((@Nullable String displayErrorMessage) -> {
            if (!StringUtils.isEmpty(displayErrorMessage)) {
                if (customAlertDialog != null) {
                    customAlertDialog.dismiss();
                }
                customAlertDialog = new CustomAlertDialog(
                        getActivity(),
                        getString(R.string.dialog_title_warning),
                        android.R.attr.alertDialogIcon,
                        () -> customAlertDialog.dismiss()
                );
                customAlertDialog.showAlert(displayErrorMessage);
            }
        });
        viewModel.errorDialogCommand.observe(this, displayErrorMessageObserver);

        // validation
        viewModel.validationErrorDialogCommand.removeObservers(this);
        Observer<String> displayErrorMessageResourceIdObserver = ((@Nullable String displayErrorMessage) -> {
            if (displayErrorMessage != null) {
                if (customAlertDialog != null) {
                    customAlertDialog.dismiss();
                }
                customAlertDialog = new CustomAlertDialog(
                        getActivity(),
                        getString(R.string.dialog_title_warning),
                        android.R.attr.alertDialogIcon,
                        () -> customAlertDialog.dismiss()
                );
                customAlertDialog.showAlert(displayErrorMessage);
            }
        });
        viewModel.validationErrorDialogCommand.observe(this, displayErrorMessageResourceIdObserver);
    }
    //endregion
}
