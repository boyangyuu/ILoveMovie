package com.example.yuboyang.ilovemovie1.details;

import com.example.yuboyang.ilovemovie1.di.ActivityScoped;

import dagger.Subcomponent;

/**
 * Created by yuboyang on 10/18/17.
 */

@ActivityScoped
@Subcomponent(modules = {DetailsModule.class})
public interface DetailsComponent {
    MovieDetailsFragment inject(MovieDetailsFragment movieDetailsFragment);
}
