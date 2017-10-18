package com.example.yuboyang.ilovemovie1;

import com.example.yuboyang.ilovemovie1.details.DetailsComponent;
import com.example.yuboyang.ilovemovie1.details.DetailsModule;
import com.example.yuboyang.ilovemovie1.details.MovieDetailsFragment;
import com.example.yuboyang.ilovemovie1.listing.ListingComponent;
import com.example.yuboyang.ilovemovie1.listing.ListingModule;
import com.example.yuboyang.ilovemovie1.listing.MoviesListingFragment;
import com.example.yuboyang.ilovemovie1.listing.sort.SortPreferanceStore;
import com.example.yuboyang.ilovemovie1.listing.sort.SortingDialogFragment;
import com.example.yuboyang.ilovemovie1.listing.sort.SortingModule;
import com.example.yuboyang.ilovemovie1.network.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by yuboyang on 10/9/17.
 */
@Singleton
@Component(modules = {AppModule.class, SortingModule.class, NetworkModule.class})
public interface AppComponent {

    DetailsComponent plus(DetailsModule detailsModule);

    ListingComponent plus(ListingModule listingModule);
}
