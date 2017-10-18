package com.example.yuboyang.ilovemovie1.network;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import com.bumptech.glide.request.Request;
import com.example.yuboyang.ilovemovie1.BuildConfig;

import java.util.concurrent.TimeUnit;

/**
 * Created by yuboyang on 10/8/17.
 */
@Module
public class NetworkModule {
    public static final int CONNECT_TIMEOUT_IN_MS = 30000;

    @Provides
    @Singleton
    Interceptor requestInterceptor(RequestInterceptor interceptor) {
        return interceptor;
    }


    @Provides
    @Singleton
    OkHttpClient okHttpClient(RequestInterceptor requestInterceptor) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new okhttp3.OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT_IN_MS, TimeUnit.MILLISECONDS)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(requestInterceptor)
                .build();
    }

    @Singleton
    @Provides
    Retrofit retrofit(OkHttpClient okHttpClient) {
        return new Retrofit
                .Builder()
                .baseUrl(BuildConfig.TMDB_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) // serialization and deserialization of objects
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // ??
                .client(okHttpClient)
                .build();
    }

    @Singleton
    @Provides
    MovieWebService movieWebService(Retrofit retrofit) {
        System.out.println("movieWebService yby");
        return retrofit.create(MovieWebService.class);
    }

}
