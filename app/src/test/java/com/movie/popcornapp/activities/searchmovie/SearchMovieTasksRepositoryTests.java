package com.movie.popcornapp.activities.searchmovie;

import com.movie.popcornapp.components.ResponseCallback;
import com.movie.popcornapp.models.API.response.MovieResponse;
import com.movie.popcornapp.models.API.response.SearchMoviesResponse;
import com.movie.popcornapp.workers.searchmovies.SearchMoviesWorkerInterface;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * @author george.radu on 2019-07-09.
 */
public class SearchMovieTasksRepositoryTests {

    //region Properties
    private SearchMoviesWorkerInterface searchMoviesWorker = mock(SearchMoviesWorkerInterface.class);
    private SearchMoviesTasksRepository tasksRepository = new SearchMoviesTasksRepository(searchMoviesWorker);
    //endregion;

    //region Get movies tests
    @Test
    public void testSearchMoviesTasksRepository_getMovies_unSuccessful() {
        // Response error
        final String RESPONSE_ERROR = "Load movies failed";

        // Set movie to search
        final String MOVIE_TO_SEARCH = "titanic";

        // Call
        tasksRepository.getMoviesList(MOVIE_TO_SEARCH, (wasSuccessful, message, data) -> {
            Assert.assertFalse(wasSuccessful);
            Assert.assertEquals(message, RESPONSE_ERROR);
        });

        // Create and get captor
        ArgumentCaptor<ResponseCallback<SearchMoviesResponse>> captor = ArgumentCaptor.forClass(ResponseCallback.class);
        verify(searchMoviesWorker).searchMovies(eq(MOVIE_TO_SEARCH), captor.capture());

        // Create new response and set
        ResponseCallback<SearchMoviesResponse> callback = captor.getValue();
        callback.response(false, RESPONSE_ERROR, null);
    }

    @Test
    public void testSearchMoviesTasksRepository_getMovies_successful() {
        // Set movie to search
        final String MOVIE_TO_SEARCH = "titanic";

        // Mock api call response
        SearchMoviesResponse mockedMovieResponse = mock(SearchMoviesResponse.class);

        // Call
        tasksRepository.getMoviesList(MOVIE_TO_SEARCH, (wasSuccessful, message, data) -> {
            Assert.assertTrue(wasSuccessful);
            Assert.assertNull(message);
            Assert.assertEquals(data, mockedMovieResponse);
        });

        // Create and get captor
        ArgumentCaptor<ResponseCallback<SearchMoviesResponse>> captor = ArgumentCaptor.forClass(ResponseCallback.class);
        verify(searchMoviesWorker).searchMovies(eq(MOVIE_TO_SEARCH), captor.capture());

        // Create new response and set
        ResponseCallback<SearchMoviesResponse> callback = captor.getValue();
        callback.response(true, null, mockedMovieResponse);
    }
    //endregion
}
