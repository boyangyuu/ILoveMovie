package com.example.yuboyang.ilovemovie1.listing;

import com.example.yuboyang.ilovemovie1.Movie;
import com.example.yuboyang.ilovemovie1.network.MovieWebService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by yuboyang on 10/8/17.
 */

public class MoviesListingInteractorImpl implements MoviesListingInteractor {


    MovieWebService movieWebService;

    public MoviesListingInteractorImpl(MovieWebService webService) {
        this.movieWebService = webService;
    }

    @Override
    public Observable<List<Movie>> fetchMovies() {
        int selectedOption = 1;
        return this.movieWebService
                .popularMovies()
                .map(moviesWrapper -> moviesWrapper.getMovies());
    }
}
