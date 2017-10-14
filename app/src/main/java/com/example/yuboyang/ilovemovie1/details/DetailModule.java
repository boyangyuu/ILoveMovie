package com.example.yuboyang.ilovemovie1.details;

import com.example.yuboyang.ilovemovie1.network.MovieWebService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yuboyang on 10/12/17.
 */

@Singleton
@Module
public class DetailModule {
    @Provides
    @Singleton
    MovieDetailInteractor interactor(MovieWebService service) {
        return new MovieDetailInteractorImpl(service);
    }

    @Provides
    @Singleton
    MovieDetailPresenter movieDetailPresenter(MovieDetailInteractor interactor) {
        return new MovieDetailPresenterImpl(interactor);
    }
}
