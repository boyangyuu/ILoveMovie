package com.example.yuboyang.ilovemovie1.details;

import android.util.Log;

import com.example.yuboyang.ilovemovie1.ReviewWrapper;
import com.example.yuboyang.ilovemovie1.VideoWrapper;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yuboyang on 10/12/17.
 */

public class MovieDetailPresenterImpl implements MovieDetailPresenter {
    private static final String TAG = "MovieDetailPresenterImpl";
    private final MovieDetailInteractor interactor;
    private MovieDetailView view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MovieDetailPresenterImpl(MovieDetailInteractor interactor) {
        this.interactor = interactor;
    }


    @Override
    public void setView(MovieDetailView view) {
        this.view = view;
    }

    @Override
    public void displayReviews(String movieId) {
        this.interactor.fetchReviews(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onReviewsFetchSuccess, this::onReviewsFetchFailed);
    }

    private void onReviewsFetchSuccess(ReviewWrapper reviewWrapper) {
        Log.d(TAG, "onReviewsFetchSuccess() called with: reviewWrapper = [" + reviewWrapper + "]");
        this.view.showReviews(reviewWrapper.getReviews());
    }

    private void onReviewsFetchFailed(Throwable throwable) {
        Log.d(TAG, "onReviewsFetchFailed() called with: throwable = [" + throwable + "]");
    }


    @Override
    public void displayTrails(String movieId) {
        this.interactor.fetchVideos(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onTrailsFetchSuccess, this::onTrailsFetchFailed);
    }

    @Override
    public void destroy() {

    }

    private void onTrailsFetchSuccess(VideoWrapper videoWrapper) {
//        Log.d(TAG, "onTrailsFetchSuccess() called with: videoWrapper = [" + videoWrapper + "]");
        this.view.showTrailers(videoWrapper.getVideos());
    }

    private void onTrailsFetchFailed(Throwable throwable) {
//        Log.d(TAG, "onTrailsFetchFailed() called with: throwable = [" + throwable + "]");
    }
}
