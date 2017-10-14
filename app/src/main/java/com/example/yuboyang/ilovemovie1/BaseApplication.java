package com.example.yuboyang.ilovemovie1;

import android.app.Application;

import com.example.yuboyang.ilovemovie1.listing.DaggerAppComponent;
import com.example.yuboyang.ilovemovie1.listing.AppComponent;
import com.example.yuboyang.ilovemovie1.listing.ListModule;
import com.example.yuboyang.ilovemovie1.network.NetworkModule;

/**
 * Created by yuboyang on 10/8/17.
 */

public class BaseApplication extends Application {
    public static AppComponent getAppComponent() {
        return DaggerAppComponent.builder()
                .listModule(new ListModule())
                .networkModule(new NetworkModule())
                .build();
    }
}
