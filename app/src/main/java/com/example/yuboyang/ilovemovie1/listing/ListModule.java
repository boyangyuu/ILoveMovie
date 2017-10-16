package com.example.yuboyang.ilovemovie1.listing;

import android.content.Context;

import com.example.yuboyang.ilovemovie1.listing.sort.SortPreferance;
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
    @Singleton
    @Provides
    SortPreferance sortPreferance(Context context) {
        return new SortPreferance(context);
    }

    @Singleton
    @Provides
    MoviesListingInteractor moviesListingInteractor(MovieWebService webService, SortPreferance sortPreferance) {
        return new MoviesListingInteractorImpl(webService, sortPreferance);
    }

    @Singleton
    @Provides
    MoviesListingPresenter moviesListingPresenter(MoviesListingInteractor interactor) {
        return new MoviesListingPresenterImpl(interactor);
    }
}
