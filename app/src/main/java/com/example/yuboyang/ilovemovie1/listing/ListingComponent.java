package com.example.yuboyang.ilovemovie1.listing;

import com.example.yuboyang.ilovemovie1.di.ActivityScoped;
import com.example.yuboyang.ilovemovie1.listing.sort.SortingDialogFragment;
import com.example.yuboyang.ilovemovie1.listing.sort.SortingModule;

import dagger.Subcomponent;

/**
 * Created by yuboyang on 10/17/17.
 */
@ActivityScoped
@Subcomponent(modules = {ListingModule.class})
public interface ListingComponent
{
    MoviesListingFragment inject(MoviesListingFragment fragment);
    SortingDialogFragment inject(SortingDialogFragment fragment);
}

