package com.movie.popcornapp.activities.searchmovie;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.movie.popcornapp.activities.searchmovie.SearchMovieDataModel;
import com.movie.popcornapp.activities.searchmovie.SearchMovieTasksRepositoryInterface;
import com.movie.popcornapp.activities.searchmovie.SearchMovieViewModel;
import com.movie.popcornapp.components.LoadingCommandAndMessage;
import com.movie.popcornapp.components.ResponseCallback;
import com.movie.popcornapp.models.API.response.MovieResponse;
import com.movie.popcornapp.models.API.response.SearchMoviesResponse;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * @author george.radu on 2019-07-09.
 */
public class SearchMovieViewModelTests {

    //region Fields / Properties
    private final String VALIDATION_ERROR_MESSAGE = "Please type a movie title";
    private final String EMPTY_LIST_ERROR_MESSAGE = "No movie found with this title";
    private SearchMovieDataModel searchMovieDataModel = new SearchMovieDataModel(
            VALIDATION_ERROR_MESSAGE,
            EMPTY_LIST_ERROR_MESSAGE
    );
    private SearchMovieTasksRepositoryInterface tasksRepositoryInterface = mock(SearchMovieTasksRepositoryInterface.class);
    private SearchMovieViewModel searchMovieViewModel = new SearchMovieViewModel(tasksRepositoryInterface, searchMovieDataModel);
    //endregion

    //region Rules
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    //endregion;

    //region Validation commands tests
    @Test
    public void testSearchMovieViewModel_movieEditTextIsNull() {
        // Set movieEditText
        searchMovieViewModel.movieEditText = null;

        // Call search movie
        searchMovieViewModel.searchForMovie();

        // Check validation command
        Assert.assertEquals(searchMovieViewModel.validationErrorDialogCommand.getValue(), VALIDATION_ERROR_MESSAGE);

        // Check that loading command was not triggered, practically the method returned after validation error
        Assert.assertNull(searchMovieViewModel.loadingCommands.getValue());
    }

    @Test
    public void testSearchMovieViewModel_movieEditTextIsEmpty() {
        // Set movieEditText - we left intentionally some empty spaces there
        searchMovieViewModel.movieEditText.set("   ");

        // Call search movie
        searchMovieViewModel.searchForMovie();

        // Check navigation command
        Assert.assertEquals(searchMovieViewModel.validationErrorDialogCommand.getValue(), VALIDATION_ERROR_MESSAGE);

        // Check that loading command was not triggered, practically the method returned after validation error
        Assert.assertNull(searchMovieViewModel.loadingCommands.getValue());
    }

    @Test
    public void testSearchMovieViewModel_movieEditTextIsCorrectlyFilled() {
        // Set movieEditText
        searchMovieViewModel.movieEditText.set("titanic");

        // Call search movie
        searchMovieViewModel.searchForMovie();

        // Check navigation command - it should be null, since the search is valid
        Assert.assertNull(searchMovieViewModel.validationErrorDialogCommand.getValue());

        // Check that loading command was not triggered, practically we passed the validation tests
        Assert.assertNotNull(searchMovieViewModel.loadingCommands);
    }
    //endregion

    //region Loading command tests (check show and hide progress dialog)
    @Test
    public void testSearchMovieViewModel_loadingCommandIsTriggeredToShowProgressDialog() {
        // Set movieEditText
        searchMovieViewModel.movieEditText.set("titanic");

        // Call search movie
        searchMovieViewModel.searchForMovie();

        // Check loading command
        Assert.assertEquals(searchMovieViewModel.loadingCommands.getValue().command, LoadingCommandAndMessage.LoadingCommand.show);
    }

    @Test
    public void testSearchMovieViewModel_loadingCommandIsTriggeredToHideProgressDialog() {
        // Set movie to search
        final String MOVIE_TO_SEARCH = "titanic";

        // Set movieEditText
        searchMovieViewModel.movieEditText.set(MOVIE_TO_SEARCH);

        // Call search movie
        searchMovieViewModel.searchForMovie();

        // Create and get captor
        ArgumentCaptor<ResponseCallback<SearchMoviesResponse>> captor = ArgumentCaptor.forClass(ResponseCallback.class);
        verify(tasksRepositoryInterface).getMoviesList(
                eq(MOVIE_TO_SEARCH),
                captor.capture()
        );

        // Create new response and set
        ResponseCallback<SearchMoviesResponse> callback = captor.getValue();
        callback.response(false, "Conection failed", null);

        // Check loading command
        Assert.assertEquals(searchMovieViewModel.loadingCommands.getValue().command, LoadingCommandAndMessage.LoadingCommand.hide);
    }
    //endregion

    //region Error dialog command tests - unsuccessful and successful but empty
    @Test
    public void testSearchMovieViewModel_moviesFetchFailure() {
        // Set movie to search
        final String MOVIE_TO_SEARCH = "titanic";
        final String ERROR_MESSAGE = "connection timeout";

        // Set movieEditText
        searchMovieViewModel.movieEditText.set(MOVIE_TO_SEARCH);

        // Call search movie
        searchMovieViewModel.searchForMovie();

        // Create and get captor
        ArgumentCaptor<ResponseCallback<SearchMoviesResponse>> captor = ArgumentCaptor.forClass(ResponseCallback.class);
        verify(tasksRepositoryInterface).getMoviesList(
                eq(MOVIE_TO_SEARCH),
                captor.capture()
        );

        // Create new response and set
        ResponseCallback<SearchMoviesResponse> callback = captor.getValue();
        callback.response(false, ERROR_MESSAGE, null);

        // Check loading command
        Assert.assertEquals(searchMovieViewModel.errorDialogCommand.getValue(), ERROR_MESSAGE);
    }

    @Test
    public void testSearchMovieViewModel_moviesFetchSuccessEmptyList() {
        // Set movie to search
        final String MOVIE_TO_SEARCH = "titanic";

        // Set movieEditText
        searchMovieViewModel.movieEditText.set(MOVIE_TO_SEARCH);

        // Call search movie
        searchMovieViewModel.searchForMovie();

        // Create and get captor
        ArgumentCaptor<ResponseCallback<SearchMoviesResponse>> captor = ArgumentCaptor.forClass(ResponseCallback.class);
        verify(tasksRepositoryInterface).getMoviesList(
                eq(MOVIE_TO_SEARCH),
                captor.capture()
        );

        // Create new response and set
        ArrayList<MovieResponse> moviesResponse = new ArrayList<>(); // empty list
        SearchMoviesResponse searchMoviesResponse = new SearchMoviesResponse(moviesResponse);

        Assert.assertTrue(searchMoviesResponse.getMoviesResponse().size() == 0);

        ResponseCallback<SearchMoviesResponse> callback = captor.getValue();
        callback.response(true, "error", searchMoviesResponse);

        // Check that the error dialog command received the dataModel error message,
        // no matter that the message received in callback is different
        Assert.assertEquals(searchMovieViewModel.errorDialogCommand.getValue(), searchMovieDataModel.getNoMovieFoundErrorMessage());
    }
    //endregion

    //region Navigation command
    @Test
    public void testSearchMovieViewModel_moviesFetchSuccessNonEmptyList() {
        // Set movie to search
        final String MOVIE_TO_SEARCH = "titanic";

        // Set movieEditText
        searchMovieViewModel.movieEditText.set(MOVIE_TO_SEARCH);

        // Call search movie
        searchMovieViewModel.searchForMovie();

        // Create and get captor
        ArgumentCaptor<ResponseCallback<SearchMoviesResponse>> captor = ArgumentCaptor.forClass(ResponseCallback.class);
        verify(tasksRepositoryInterface).getMoviesList(
                eq(MOVIE_TO_SEARCH),
                captor.capture()
        );

        // Create new response and set
        MovieResponse mockedMovieResponse = mock(MovieResponse.class);
        ArrayList<MovieResponse> moviesResponse = new ArrayList<>();
        moviesResponse.add(mockedMovieResponse);
        SearchMoviesResponse searchMoviesResponse = new SearchMoviesResponse(moviesResponse);

        Assert.assertTrue(searchMoviesResponse.getMoviesResponse().size() > 0);

        ResponseCallback<SearchMoviesResponse> callback = captor.getValue();
        callback.response(true, null, searchMoviesResponse);

        // Check that navigation command is correct (command, search text and movie response)
        Assert.assertEquals(searchMovieViewModel.navigationCommands.getValue().command, SearchMovieViewModel.NavigationCommand.navigateToMovies);
        Assert.assertEquals(searchMovieViewModel.navigationCommands.getValue().searchText, MOVIE_TO_SEARCH.trim());
        Assert.assertEquals(searchMovieViewModel.navigationCommands.getValue().movieResponse, searchMoviesResponse);
    }
    //endregion

}
