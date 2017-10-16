package com.example.yuboyang.ilovemovie1.listing;

/**
 * Created by yuboyang on 10/9/17.
 */

public interface MoviesListingPresenter {
    void setView(MoviesListingView view);
    void displayMovies();
    void showLoading();
}
