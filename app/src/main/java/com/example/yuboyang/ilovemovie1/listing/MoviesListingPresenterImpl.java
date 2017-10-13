package com.example.yuboyang.ilovemovie1.listing;

import com.example.yuboyang.ilovemovie1.Movie;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.internal.schedulers.IoScheduler;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;

/**
 * Created by yuboyang on 10/9/17.
 */

public class MoviesListingPresenterImpl implements MoviesListingPresenter {
    private MoviesListingView view;

    private MoviesListingInteractor interceptor;

    public MoviesListingPresenterImpl(MoviesListingInteractor interactor) {
        this.interceptor = interactor;
        displayMovies();
    }

    @Override
    public void setView(MoviesListingView view) {
        this.view = view;
//        displayMovies();
    }

    @Override
    public void displayMovies() {
        showLoading();
        interceptor.fetchMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onMovieFetchSuccess, this::onMoviesFetchFailed);

    }

    private void onMoviesFetchFailed(Throwable throwable) {
        System.out.println("onMoviesFetchFailed");
    }

    private void onMovieFetchSuccess(List<Movie> movies) {
        this.view.showMovies(movies);
    }
    @Override
    public void showLoading() {
        // TODO: 10/9/17
    }
}
