package com.example.yuboyang.ilovemovie1.listing;

import com.example.yuboyang.ilovemovie1.network.MovieWebService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yuboyang on 10/9/17.
 */
@Singleton
@Module
public class ListModule {
    @Provides
    MoviesListingInteractor moviesListingInteractor(MovieWebService webService) {
        return new MoviesListingInteractorImpl(webService);
    }

}
