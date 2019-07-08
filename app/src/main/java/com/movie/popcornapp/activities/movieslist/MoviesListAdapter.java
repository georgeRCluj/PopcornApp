package com.movie.popcornapp.activities.movieslist;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.movie.popcornapp.databinding.ItemMovieListBinding;

/**
 * @author george.radu on 2019-07-08.
 */

public class MoviesListAdapter extends ListAdapter<MoviesListItemViewModel, MoviesListAdapter.MovieItemListViewHolder> {
    MoviesListAdapter() {
        super((new MoviesListItemViewModelDiffCallback()));
    }

    @NonNull
    @Override
    public MovieItemListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int p1) {
        LayoutInflater layout = LayoutInflater.from(parent.getContext());
        ItemMovieListBinding binding = ItemMovieListBinding.inflate(layout, parent, false);
        return new MovieItemListViewHolder(binding);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @Override
    public void onBindViewHolder(@NonNull MovieItemListViewHolder holder, int position) {
        MoviesListItemViewModel viewModel = getItem(position);
        holder.getBinding().setViewModel(viewModel);
        holder.getBinding().executePendingBindings();
    }

    class MovieItemListViewHolder extends RecyclerView.ViewHolder {

        private final ItemMovieListBinding binding;

        final ItemMovieListBinding getBinding() {
            return this.binding;
        }

        MovieItemListViewHolder(@NonNull ItemMovieListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

class MoviesListItemViewModelDiffCallback extends DiffUtil.ItemCallback<MoviesListItemViewModel> {

    @Override
    public boolean areItemsTheSame(@NonNull MoviesListItemViewModel oldMovie, @NonNull MoviesListItemViewModel newMovie) {
        return oldMovie.getId() == (newMovie.getId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull MoviesListItemViewModel oldMovie, @NonNull MoviesListItemViewModel newMovie) {
        return oldMovie == newMovie;
    }
}



