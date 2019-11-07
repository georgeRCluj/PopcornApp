package com.movie.popcornapp.activities.moviedetails

import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.movie.popcornapp.R
import com.movie.popcornapp.activities.movieslist.MoviesListActivity
import com.movie.popcornapp.databinding.ActivityMovieDetailsBinding
import com.movie.popcornapp.extensions.ActivityUtils
import com.movie.popcornapp.extensions.AnimationUtils
import com.movie.popcornapp.models.API.response.MovieResponse
import java.util.ArrayList

class MovieDetailsActivity : AppCompatActivity() {

    //region Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // set layout & toolbar
        AnimationUtils.setAnimation(this, Gravity.RIGHT, 400)
        val binding = DataBindingUtil.setContentView<ActivityMovieDetailsBinding>(this, R.layout.activity_movie_details).apply {
            setupToolbar(this)
        }

        // Retrieve movie from previous activity. Start first fragment
        intent.getParcelableArrayListExtra<MovieResponse>(MoviesListActivity.MOVIES_LIST_GO_TO_MOVIE_DETAILS_KEY).apply {
            findOrCreateViewFragment(binding, this)
        }
    }
    //endregion

    //region Setup
    private fun setupToolbar(binding: ActivityMovieDetailsBinding) {
        setSupportActionBar(binding.toolbarMovieDetails)
        binding.backArrow.setOnClickListener { finish() }
        supportActionBar?.also {
            it.setDisplayShowTitleEnabled(false)
            it.setHomeButtonEnabled(false)
        }
    }

    private fun findOrCreateViewFragment(binding: ActivityMovieDetailsBinding, selectedMovie: ArrayList<MovieResponse>) {
        val movieDetailsFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_movie_details) as MovieDetailsFragment?
        if (movieDetailsFragment == null) {
            ActivityUtils.addFragmentToActivity(
                    supportFragmentManager,
                    MovieDetailsFragment.newInstance(selectedMovie),
                    binding.fragmentContainerMovieDetails.id,
                    false
            )
        }
    }
    //endregion
}