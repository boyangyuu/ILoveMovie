package com.example.yuboyang.ilovemovie1.network;

import com.example.yuboyang.ilovemovie1.MoviesWrapper;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by yuboyang on 10/8/17.
 */

public interface MovieWebService {

    @GET("3/discover/movie?language=en?sort_by=popularity.desc")
    Observable<MoviesWrapper> popularMovies();
}
