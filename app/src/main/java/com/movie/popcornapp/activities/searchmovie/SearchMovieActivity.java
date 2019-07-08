package com.movie.popcornapp.activities.searchmovie;

import android.os.Bundle;
import android.view.Gravity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.movie.popcornapp.R;
import com.movie.popcornapp.databinding.ActivitySearchMovieBinding;
import com.movie.popcornapp.extensions.ActivityUtils;
import com.movie.popcornapp.extensions.AnimationUtils;

/**
 * @author george.radu on 2019-07-07.
 */
public class SearchMovieActivity extends AppCompatActivity {

    //region Lifecycle
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set layout
        AnimationUtils.setAnimation(this, Gravity.RIGHT, 400);
        ActivitySearchMovieBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_search_movie);

        //Initial View (fragment)
        findOrCreateViewFragment(binding);
    }
    //endregion

    //region Setup
    private void findOrCreateViewFragment(ActivitySearchMovieBinding binding) {
        SearchMovieFragment searchMovieFragment = (SearchMovieFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container_search_movie);
        if (searchMovieFragment == null) {
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(),
                    SearchMovieFragment.newInstance(),
                    binding.fragmentContainerSearchMovie.getId(),
                    false
            );
        }
    }
    //endregion
}
