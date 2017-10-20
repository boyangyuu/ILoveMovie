package com.example.yuboyang.ilovemovie1.details;

/**
 * Created by yuboyang on 10/10/17.
 */

public interface MovieDetailPresenter {
    void setView(MovieDetailView view);
    void displayReviews(String movieId);
    void displayTrails(String movieId);

    void destroy();
}
