package com.movie.popcornapp.activities.searchmovie;

import android.os.Bundle;
import android.view.Gravity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.movie.popcornapp.R;
import com.movie.popcornapp.databinding.ActivitySearchMovieBinding;
import com.movie.popcornapp.extensions.AnimationUtils;

/**
 * @author george.radu on 2019-07-07.
 */
public class SearchMovieActivity extends AppCompatActivity {
    private ActivitySearchMovieBinding dataBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AnimationUtils.setAnimation(this, Gravity.RIGHT, 400);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_search_movie);
    }
}
