package com.movie.popcornapp.activities.splashscreen;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Slide;
import android.view.Gravity;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.movie.popcornapp.R;
import com.movie.popcornapp.activities.searchmovie.SearchMovieActivity;

/**
 * @author george.radu on 2019-07-07.
 */
public class SplashScreenActivity extends AppCompatActivity {

    //region Lifecycle methods
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash); // we do not use here data binding intentionally, since we do not need any reference from xml
        delayAndNavigateToSearchMovieScreen();
    }
    //endregion

    // region Splash screen with delay and animation
    private void delayAndNavigateToSearchMovieScreen() {
        final int SPLASH_SCREEN_DELAY_IN_MILLIS = 1000;

        // we start a new UI thread for the users to see the UI shortly
        new Handler().postDelayed(() -> {
            startActivity(
                    new Intent(SplashScreenActivity.this, SearchMovieActivity.class),
                    ActivityOptions.makeSceneTransitionAnimation(SplashScreenActivity.this).toBundle() // enable animation on transition
            );
            overridePendingTransition(0, 0);
            finish(); // if the users hit back, we do not want them to see the splash screen again
        }, SPLASH_SCREEN_DELAY_IN_MILLIS);
    }
    //endregion
}
