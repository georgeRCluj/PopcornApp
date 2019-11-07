package com.movie.popcornapp.activities.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.movie.popcornapp.databinding.FragmentMovieDetailsBinding
import com.movie.popcornapp.infrastructure.factories.ViewModelFactory
import com.movie.popcornapp.models.API.response.MovieResponse
import java.util.ArrayList

class MovieDetailsFragment : Fragment() {

    //region Properties
    private var binding: FragmentMovieDetailsBinding? = null
    //endregion

    companion object {
        const val MOVIE_DETAILS_FRAGMENT_SELECTED_MOVIE_KEY = "movie_details_fragment_selected_movie_key"

        fun newInstance(selectedMovie: ArrayList<MovieResponse>): MovieDetailsFragment {
            val fragment = MovieDetailsFragment()
            val args = Bundle()
            args.putParcelableArrayList(MOVIE_DETAILS_FRAGMENT_SELECTED_MOVIE_KEY, selectedMovie)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentMovieDetailsBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // get arguments and inject in xml
        arguments?.getParcelableArrayList<MovieResponse>(MOVIE_DETAILS_FRAGMENT_SELECTED_MOVIE_KEY)!![0].also {
            ViewModelProviders.of(this, ViewModelFactory(null, it)).get(MovieDetailsViewModel::class.java).apply {
                binding?.viewModel = this
            }
        }
    }
    //endregion
}