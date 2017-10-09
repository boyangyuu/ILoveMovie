package com.example.yuboyang.ilovemovie1.network;

import com.example.yuboyang.ilovemovie1.BuildConfig;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


@Singleton
public class RequestInterceptor implements Interceptor {

    @Inject
    public RequestInterceptor() {

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();

        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
                .build();

        Request request = original.newBuilder().url(url).build();
        //// TODO: 10/9/17
        System.out.println("yby intercept");
        return chain.proceed(request);
    }
}
