package com.example.yuboyang.ilovemovie1.listing;

import com.example.yuboyang.ilovemovie1.Movie;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by yuboyang on 10/8/17.
 */

public interface MoviesListingInteractor {
    Observable<List<Movie>> fetchMovies();
}
