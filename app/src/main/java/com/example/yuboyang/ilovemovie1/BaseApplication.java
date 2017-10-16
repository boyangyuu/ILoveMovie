package com.example.yuboyang.ilovemovie1;

import android.app.Application;
import com.example.yuboyang.ilovemovie1.listing.ListModule;
import com.example.yuboyang.ilovemovie1.network.NetworkModule;

/**
 * Created by yuboyang on 10/8/17.
 */

public class BaseApplication extends Application {
    BaseApplication() {

    }

    public AppComponent getAppComponent() {
//        return null;
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .listModule(new ListModule())
                .networkModule(new NetworkModule())
                .build();
    }
}
