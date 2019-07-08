package com.movie.popcornapp.activities.searchmovie;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.movie.popcornapp.components.LoadingCommandAndMessage;

/**
 * @author george.radu on 2019-07-08.
 */
public class SearchMovieViewModel extends ViewModel {

    private SearchMovieTasksRepositoryInterface searchMovieTasksRepositoryInterface;
    private SearchMovieDataModel searchMovieDataModel;

    //region Observables
    public ObservableField<String> movieEditText = new ObservableField<>();
    //endregion;

    //region Dialogs
    public MutableLiveData<Boolean> successDialogCommand = new MutableLiveData<>();
    public MutableLiveData<String> errorDialogCommand = new MutableLiveData<>();
    public MutableLiveData<LoadingCommandAndMessage> loadingCommands = new MutableLiveData<>();
    public MutableLiveData<String> validationErrorDialogCommand = new MutableLiveData<>();
    //endregion;

    public SearchMovieViewModel(SearchMovieTasksRepositoryInterface searchMovieTasksRepositoryInterface, SearchMovieDataModel searchMovieDataModel) {
        this.searchMovieTasksRepositoryInterface = searchMovieTasksRepositoryInterface;
        this.searchMovieDataModel = searchMovieDataModel;
    }

    //region Actions
    public void searchForMovie() {
        // validation
        if (movieEditText == null || movieEditText.get() == null || movieEditText.get().trim().length() == 0) {
            String errorMessage = searchMovieDataModel.getValidationErrorMessage();
            if (errorMessage != null) {
                validationErrorDialogCommand.postValue(errorMessage);
            }
            return;
        }

        loadingCommands.postValue(new LoadingCommandAndMessage(LoadingCommandAndMessage.LoadingCommand.show, null));
        searchMovieTasksRepositoryInterface.getMoviesList(movieEditText.get(),
                (success, message, data) -> {
                    if (success && data != null) {
                        if (data.getMoviesResponse().size() > 0) {
                            successDialogCommand.postValue(true);
                        } else {
                            errorDialogCommand.postValue(searchMovieDataModel.getNoMovieFoundErrorMessage());
                        }
                    } else {
                        errorDialogCommand.postValue(message);
                    }
                    loadingCommands.postValue(new LoadingCommandAndMessage(LoadingCommandAndMessage.LoadingCommand.hide, null));
                });
    }
    //endregion
}
