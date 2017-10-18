package com.example.yuboyang.ilovemovie1.listing;

import android.content.Context;

import com.example.yuboyang.ilovemovie1.di.ActivityScoped;
import com.example.yuboyang.ilovemovie1.listing.sort.SortPreferanceStore;
import com.example.yuboyang.ilovemovie1.network.MovieWebService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yuboyang on 10/9/17.
 */
@Module
public class ListingModule {
    @ActivityScoped
    @Provides
    MoviesListingInteractor moviesListingInteractor(MovieWebService webService, SortPreferanceStore sortPreferanceStore) {
        return new MoviesListingInteractorImpl(webService, sortPreferanceStore);
    }

    @ActivityScoped
    @Provides
    MoviesListingPresenter moviesListingPresenter(MoviesListingInteractor interactor) {
        return new MoviesListingPresenterImpl(interactor);
    }
}
