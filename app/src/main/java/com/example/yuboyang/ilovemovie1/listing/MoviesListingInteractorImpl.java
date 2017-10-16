package com.example.yuboyang.ilovemovie1.listing;

import android.util.Log;

import com.example.yuboyang.ilovemovie1.Movie;
import com.example.yuboyang.ilovemovie1.listing.sort.SortPreferance;
import com.example.yuboyang.ilovemovie1.listing.sort.SortType;
import com.example.yuboyang.ilovemovie1.network.MovieWebService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

import static android.content.ContentValues.TAG;

/**
 * Created by yuboyang on 10/8/17.
 */

public class MoviesListingInteractorImpl implements MoviesListingInteractor {


    private final SortPreferance sortPreferance;
    MovieWebService movieWebService;

    public MoviesListingInteractorImpl(MovieWebService webService, SortPreferance sortPreferance) {
        this.movieWebService = webService;
        this.sortPreferance = sortPreferance;
    }

    @Override
    public Observable<List<Movie>> fetchMovies() {
        int sortType = sortPreferance.getSelectedOption();
        if (sortType == SortType.MOST_POPULAR.getValue()) {
            return this.movieWebService
                    .popularMovies()
                    .map(moviesWrapper -> moviesWrapper.getMovies());
        } else if (sortType == SortType.HIGHEST_RATED.getValue()) {
            return this.movieWebService
                    .highestRatedMovies()
                    .map(moviesWrapper -> moviesWrapper.getMovies());
        } else if (sortType == SortType.FAVORITES.getValue()) {
            Log.i(TAG, "fetchMovies: Favorites");
        } else {

        }
        return null;
    }
}
