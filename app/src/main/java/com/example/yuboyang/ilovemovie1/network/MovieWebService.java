package com.example.yuboyang.ilovemovie1.network;

import com.example.yuboyang.ilovemovie1.MoviesWrapper;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by yuboyang on 10/8/17.
 */

public interface MovieWebService {

    @GET("3/discover/movie?language=en&sort_by=popularity.desc")
    Observable<MoviesWrapper> popularMovies();

    @GET("3/discover/movie?vote_count.gte=500&language=en&sort_by=vote_average.desc")
    Observable<MoviesWrapper> highestRatedMovies();

    @GET("3/movie/{movieId}/videos")
    Observable<MoviesWrapper> trailers(@Path("movieId") String movieId);

    @GET("3/movie/{movieId}/reviews")
    Observable<MoviesWrapper> reviews(@Path("movieId") String movieId);

}
