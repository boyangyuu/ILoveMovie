package com.example.yuboyang.ilovemovie1.listing;

import com.example.yuboyang.ilovemovie1.details.MovieDetailsActivity;
import com.example.yuboyang.ilovemovie1.details.MovieDetailsFragment;
import com.example.yuboyang.ilovemovie1.network.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by yuboyang on 10/9/17.
 */
@Singleton
@Component(modules = {ListModule.class, NetworkModule.class})
public interface AppComponent {
    MoviesListFragment inject(MoviesListFragment activity);

    MovieDetailsFragment inject(MovieDetailsFragment movieDetailsFragment);
}
