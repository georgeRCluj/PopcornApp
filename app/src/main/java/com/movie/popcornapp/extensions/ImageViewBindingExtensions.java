package com.movie.popcornapp.extensions;

import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.movie.popcornapp.BuildConfig;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * @author george.radu on 2019-07-08.
 */
public class ImageViewBindingExtensions {

    @BindingAdapter("imageUrlLoad185")
    public static void loadImageUrl185(ImageView imageView, String url) {
        if (url != null && !url.isEmpty()) {
        String finalUrl = BuildConfig.TheMovieDBPosterW185 + url;
            Picasso.get()
                    .load(finalUrl)
                    .into(imageView);
        }
    }

    @BindingAdapter("imageUrlLoad500")
    public static void loadImageUrl500(ImageView imageView, String url) {
        if (url != null && !url.isEmpty()) {
            String finalUrl = BuildConfig.TheMovieDBPosterW500 + url;
            Picasso.get()
                    .load(finalUrl)
                    .into(imageView, new Callback() {
                        @Override
                        public void onSuccess() {
                        }

                        @Override
                        public void onError(Exception e) {
                            Log.d("Picasso_error", "onError: " + e);
                        }
                    });
        }
    }
}
