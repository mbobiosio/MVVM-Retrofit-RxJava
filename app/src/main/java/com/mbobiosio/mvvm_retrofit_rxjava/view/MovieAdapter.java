package com.mbobiosio.mvvm_retrofit_rxjava.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mbobiosio.mvvm_retrofit_rxjava.R;
import com.mbobiosio.mvvm_retrofit_rxjava.databinding.MovieItemBinding;
import com.mbobiosio.mvvm_retrofit_rxjava.model.entity.Movie;
import com.mbobiosio.mvvm_retrofit_rxjava.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mbuodile Obiosio on Oct 28,2019
 * https://twitter.com/cazewonder
 * Nigeria.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.BindingHolder> {
    private List<Movie> movies;

    public MovieAdapter() {
        movies = new ArrayList<>();
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MovieItemBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.movie_item, parent, false);
        return new BindingHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        MovieViewModel movieViewModel = new MovieViewModel(movies.get(position));
        holder.itemBinding.setViewModel(movieViewModel);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void addItem(Movie movie) {
        movies.add(movie);
        notifyItemInserted(movies.size() - 1);
    }

    public void clearItems() {
        movies.clear();
        notifyDataSetChanged();
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {
        private MovieItemBinding itemBinding;

        public BindingHolder(MovieItemBinding itemBinding) {
            super(itemBinding.cardView);
            this.itemBinding = itemBinding;
        }
    }
}