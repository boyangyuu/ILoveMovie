package com.example.yuboyang.ilovemovie1;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yuboyang on 10/15/17.
 */


@Module
@Singleton
public class AppModule {
    private Context context;

    AppModule(Application context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context context() {
        return context;
    }
}
