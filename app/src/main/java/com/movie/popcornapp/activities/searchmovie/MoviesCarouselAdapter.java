package com.movie.popcornapp.activities.searchmovie;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.movie.popcornapp.databinding.ItemCarouselBinding;

import java.util.ArrayList;

/**
 * @author george.radu on 2019-07-08.
 */
class MoviesCarouselAdapter extends RecyclerView.Adapter {
    private ArrayList<Integer> moviesList;

    //region Adapter constructors (local context and events list)
    MoviesCarouselAdapter(ArrayList<Integer> moviesList) {
        this.moviesList = moviesList;
    }
    //endregion

    //region Create, Bind view holder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemCarouselBinding binding = ItemCarouselBinding.inflate(layoutInflater, parent, false);
        return new MoviesCarouselViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MoviesCarouselViewHolder viewHolder = (MoviesCarouselViewHolder) holder;
        viewHolder.bind(moviesList.get(position));
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
    //endregion

    //region View Holder
    public class MoviesCarouselViewHolder extends RecyclerView.ViewHolder {
        private ItemCarouselBinding binding;

        public MoviesCarouselViewHolder(ItemCarouselBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bind(Integer currentImageResId) {
            binding.carouselItem.setImageResource(currentImageResId);
            binding.executePendingBindings();
        }
    }
}
//endregion
