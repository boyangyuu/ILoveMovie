package com.example.yuboyang.ilovemovie1.listing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.yuboyang.ilovemovie1.Constants;
import com.example.yuboyang.ilovemovie1.Movie;
import com.example.yuboyang.ilovemovie1.R;
import com.example.yuboyang.ilovemovie1.details.MovieDetailsActivity;
import com.example.yuboyang.ilovemovie1.util.Api;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static junit.framework.Assert.assertTrue;

/**
 * Created by yuboyang on 10/9/17.
 */

class MoviesListingAdapter extends RecyclerView.Adapter<MoviesListingAdapter.ViewHolder> {

    private List<Movie> movies;
    private final MoviesListingFragment moviesListingFragment;

    public MoviesListingAdapter(MoviesListingFragment moviesListingFragment) {
        this.moviesListingFragment = moviesListingFragment;
        this.movies = new ArrayList<>();
    }

    public void setMovies(List<Movie> movies) {
        this.movies.clear();
        this.movies = movies;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Movie movie;

        @Bind(R.id.item_movie_name)
        TextView tv_item_movie_name;

        @Bind(R.id.item_movie_poster)
        ImageView iv_item_movie_poster;

        @Bind(R.id.item_title_background)
        View v_item_title_background;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MoviesListingAdapter.this.moviesListingFragment.getContext(), MovieDetailsActivity.class);
            Bundle extras = new Bundle();
            assertTrue(movie != null);
            extras.putParcelable(Constants.MOVIE, movie);
            intent.putExtras(extras);
            MoviesListingAdapter.this.moviesListingFragment.startActivity(intent);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.movie_grid_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(holder);
        holder.movie = movies.get(position);
        holder.tv_item_movie_name.setText(holder.movie.getTitle());
        Glide.with(this.moviesListingFragment)
                .load(Api.getPosterPath(holder.movie.getPosterPath()))
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(holder.iv_item_movie_poster);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
