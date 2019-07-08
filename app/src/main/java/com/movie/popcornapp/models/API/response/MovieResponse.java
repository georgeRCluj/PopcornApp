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

    @SerializedName("overview")
    private String overview;

    @SerializedName("vote_count")
    private int voteCount;

    //region Getters
    public double getVoteAverage() {
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
    //endregion

    //region Parcelable implementation
    protected MovieResponse(Parcel in) {
        voteAverage = in.readDouble();
        title = in.readString();
        posterPath = in.readString();
        overview = in.readString();
        voteCount = in.readInt();
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
        dest.writeDouble(voteAverage);
        dest.writeString(title);
        dest.writeString(posterPath);
        dest.writeString(overview);
        dest.writeInt(voteCount);
    }
    //endregion
}
