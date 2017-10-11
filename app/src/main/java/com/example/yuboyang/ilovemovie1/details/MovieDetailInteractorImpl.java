package com.example.yuboyang.ilovemovie1.details;

import com.example.yuboyang.ilovemovie1.ReviewWrapper;
import com.example.yuboyang.ilovemovie1.VideoWrapper;
import com.example.yuboyang.ilovemovie1.network.MovieWebService;

import io.reactivex.Observable;

/**
 * Created by yuboyang on 10/10/17.
 */

public class MovieDetailInteractorImpl implements MovieDetailInteractor{

    private final MovieWebService webService;

    public MovieDetailInteractorImpl(MovieWebService webService) {
        this.webService = webService;
    }

    @Override
    public Observable<ReviewWrapper> fetchReviews(String movieId) {
        return this.webService.reviews(movieId);
    }

    @Override
    public Observable<VideoWrapper> fetchVideos(String movieId) {
        return this.webService.trailers(movieId);
    }
}
