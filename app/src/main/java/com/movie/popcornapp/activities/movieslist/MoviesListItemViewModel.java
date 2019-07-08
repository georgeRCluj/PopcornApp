package com.movie.popcornapp.activities.movieslist;

import androidx.lifecycle.ViewModel;

/**
 * @author george.radu on 2019-07-08.
 */
public class MoviesListItemViewModel extends ViewModel {
    private double averageRating;
    private int voteCount;
    private MoviesListItemViewModelInterface delegate;
    private int id;
    private String url;
    private String title;
    private int year;

    MoviesListItemViewModel(int id, String url, String title, int year, double averageRating, int voteCount, MoviesListItemViewModelInterface delegate) {
        this.id = id;
        this.url = url;
        this.title = title;
        this.year = year;
        this.averageRating = averageRating;
        this.voteCount = voteCount;
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

    public String getYear() {
        return String.valueOf(year);
    }

    public int getId() {
        return id;
    }

    public String getAverageRating() {
        return String.valueOf(averageRating);
    }

    public String getVoteCount() {
        return String.format("%,d", voteCount);
    }
}
