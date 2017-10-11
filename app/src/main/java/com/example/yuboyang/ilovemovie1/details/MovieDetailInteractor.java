package com.example.yuboyang.ilovemovie1.details;

import com.example.yuboyang.ilovemovie1.ReviewWrapper;
import com.example.yuboyang.ilovemovie1.VideoWrapper;

import io.reactivex.Observable;

/**
 * Created by yuboyang on 10/10/17.
 */

public interface MovieDetailInteractor {
    Observable<ReviewWrapper> fetchReviews(String movieId);
    Observable<VideoWrapper> fetchVideos(String movieId);
}
