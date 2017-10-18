package com.example.yuboyang.ilovemovie1.listing.sort;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yuboyang on 10/17/17.
 */

@Module
public class SortingModule {
    @Singleton
    @Provides
    public SortPreferanceStore sortPreferanceStore(Context context) {
        return new SortPreferanceStore(context);
    }
}
