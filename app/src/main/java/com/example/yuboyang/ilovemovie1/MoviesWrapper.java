package com.example.yuboyang.ilovemovie1;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yuboyang on 10/8/17.
 */

public class MoviesWrapper {
    @SerializedName("results")
    private List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movieList) {
        this.movies = movieList;
    }
}
