package com.movie.popcornapp.activities.movieslist;

import androidx.lifecycle.ViewModel;

/**
 * @author george.radu on 2019-07-08.
 */
public class MoviesListItemViewModel extends ViewModel {
    private MoviesListItemViewModelInterface delegate;
    private int id;
    private String url;
    private final String title;
    private final int year;

    MoviesListItemViewModel(int id, String url, String title, int year, MoviesListItemViewModelInterface delegate) {
        this.id = id;
        this.url = url;
        this.title = title;
        this.year = year;
        this.delegate = delegate;
    }

    public void itemSelected() {
        delegate.itemSelected(this);
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public int getId() {
        return id;
    }
}
