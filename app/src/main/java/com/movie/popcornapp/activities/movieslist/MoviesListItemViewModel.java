package com.movie.popcornapp.activities.movieslist;

import androidx.lifecycle.ViewModel;

/**
 * @author george.radu on 2019-07-08.
 */
public class MoviesListItemViewModel extends ViewModel {
    private double averageRating;
    private int voteCount;
    private String overView;
    private MoviesListItemViewModelInterface delegate;
    private int id;
    private String url;
    private String backdropPath;
    private String title;
    private String releaseDate;
    private int year;

    MoviesListItemViewModel(int id, String url, String backdropPath, String title, String releaseDate, int year, double averageRating, int voteCount, String overView, MoviesListItemViewModelInterface delegate) {
        this.id = id;
        this.url = url;
        this.backdropPath = backdropPath;
        this.title = title;
        this.releaseDate = releaseDate;
        this.year = year;
        this.averageRating = averageRating;
        this.voteCount = voteCount;
        this.overView = overView;
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

    public double getAverageRatingAsDouble() {
        return averageRating;
    }

    public String getVoteCount() {
        return String.format("%,d", voteCount);
    }

    public int getVoteCountAsInt() {
        return voteCount;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getOverView() {
        return overView;
    }

    public String getBackdropPath() {
        return backdropPath;
    }
}
