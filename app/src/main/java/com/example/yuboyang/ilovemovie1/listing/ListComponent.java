package com.example.yuboyang.ilovemovie1.listing;

import android.app.Activity;

import com.example.yuboyang.ilovemovie1.network.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by yuboyang on 10/9/17.
 */
@Singleton
@Component(modules = {ListModule.class, NetworkModule.class})
public interface ListComponent {
    MoviesListingActivity inject(MoviesListingActivity activity);
}
