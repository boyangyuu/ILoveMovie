package com.example.yuboyang.ilovemovie1;

import com.example.yuboyang.ilovemovie1.details.DetailModule;
import com.example.yuboyang.ilovemovie1.details.MovieDetailsFragment;
import com.example.yuboyang.ilovemovie1.listing.ListModule;
import com.example.yuboyang.ilovemovie1.listing.MoviesListFragment;
import com.example.yuboyang.ilovemovie1.listing.sort.SortingDialogFragment;
import com.example.yuboyang.ilovemovie1.network.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by yuboyang on 10/9/17.
 */
@Singleton
@Component(modules = {AppModule.class, DetailModule.class, ListModule.class, NetworkModule.class})
public interface AppComponent {
    MoviesListFragment inject(MoviesListFragment activity);

    MovieDetailsFragment inject(MovieDetailsFragment movieDetailsFragment);

    SortingDialogFragment inject(SortingDialogFragment sortingDialogFragment);
}
