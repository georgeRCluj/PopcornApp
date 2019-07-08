package com.movie.popcornapp.models.API.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * @author george.radu on 2019-07-08.
 */
public class SearchMoviesResponse implements Parcelable {
    @SerializedName("page")
    private int page;

    @SerializedName("total_results")
    private int totalResults;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("results")
    private ArrayList<MovieResponse> moviesResponse;

    //region Getters
    public int getPage() {
        return page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public ArrayList<MovieResponse> getMoviesResponse() {
        return moviesResponse;
    }
    //endregion

    //region Parcelable interface
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(page);
        dest.writeInt(totalResults);
        dest.writeInt(totalPages);
        dest.writeList(moviesResponse);
    }

    protected SearchMoviesResponse(Parcel in) {
        page = in.readInt();
        totalResults = in.readInt();
        totalPages = in.readInt();
        in.readList(moviesResponse, SearchMoviesResponse.class.getClassLoader());
    }

    public static final Creator<SearchMoviesResponse> CREATOR = new Creator<SearchMoviesResponse>() {
        @Override
        public SearchMoviesResponse createFromParcel(Parcel in) {
            return new SearchMoviesResponse(in);
        }

        @Override
        public SearchMoviesResponse[] newArray(int size) {
            return new SearchMoviesResponse[size];
        }
    };
    //endregion
}
