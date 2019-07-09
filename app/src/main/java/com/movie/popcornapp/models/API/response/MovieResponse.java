package com.movie.popcornapp.models.API.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author george.radu on 2019-07-08.
 */
public class MovieResponse implements Parcelable {
    @SerializedName("id")
    private int id;

    @SerializedName("vote_average")
    private double voteAverage;

    @SerializedName("title")
    private String title;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("overview")
    private String overview;

    @SerializedName("vote_count")
    private int voteCount;

    @SerializedName("release_date")
    private String releaseDate;

    public MovieResponse(String title, String releaseDate, String posterPath, String backdropPath, double averageRating, int voteCount, String overview) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.voteAverage = averageRating;
        this.voteCount = voteCount;
        this.overview = overview;
    }

    //region Getters
    public double getAverageRating() {
        return voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public int getId() {
        return id;
    }

    public int getReleaseYear() {
        String dateComponents[] = releaseDate.split("-");
        return Integer.parseInt(dateComponents[0]);
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    //endregion

    //region Parcelable implementation
    protected MovieResponse(Parcel in) {
        id = in.readInt();
        voteAverage = in.readDouble();
        title = in.readString();
        posterPath = in.readString();
        backdropPath = in.readString();
        overview = in.readString();
        voteCount = in.readInt();
        releaseDate = in.readString();
    }

    public static final Creator<MovieResponse> CREATOR = new Creator<MovieResponse>() {
        @Override
        public MovieResponse createFromParcel(Parcel in) {
            return new MovieResponse(in);
        }

        @Override
        public MovieResponse[] newArray(int size) {
            return new MovieResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeDouble(voteAverage);
        dest.writeString(title);
        dest.writeString(posterPath);
        dest.writeString(backdropPath);
        dest.writeString(overview);
        dest.writeInt(voteCount);
        dest.writeString(releaseDate);
    }
    //endregion
}
