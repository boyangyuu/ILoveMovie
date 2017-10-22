package com.example.yuboyang.ilovemovie1.listing;

import com.example.yuboyang.ilovemovie1.Movie;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yuboyang on 10/9/17.
 */

public class MoviesListingPresenterImpl implements MoviesListingPresenter {
    private MoviesListingView view;

    private MoviesListingInteractor interceptor;
    private CompositeDisposable compositeDisposable;

    public MoviesListingPresenterImpl(MoviesListingInteractor interactor) {
        this.interceptor = interactor;
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void setView(MoviesListingView view) {
        this.view = view;
        displayMovies();
    }

    @Override
    public void displayMovies() {
        showLoading();
        Disposable disposable = interceptor.fetchMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onMovieFetchSuccess, this::onMoviesFetchFailed);


        compositeDisposable.add(disposable);
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

    @Override
    public void onDestroy() {
        compositeDisposable.dispose();
    }
}
