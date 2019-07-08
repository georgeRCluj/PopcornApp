package com.movie.popcornapp.extensions;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.movie.popcornapp.BuildConfig;
import com.movie.popcornapp.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * @author george.radu on 2019-07-08.
 */
public class ImageViewBindingExtensions {

    @BindingAdapter("imageUrlLoad")
    public static void loadImageUrl(ImageView imageView, String url) {
        if (url != null && !url.isEmpty()) {
        String finalUrl = BuildConfig.TheMovieDBPosteW185 + url;
            Picasso.get()
                    .load(finalUrl)
                    .into(imageView);
        }
    }
}
