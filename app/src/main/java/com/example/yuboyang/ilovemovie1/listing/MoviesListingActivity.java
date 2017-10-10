package com.example.yuboyang.ilovemovie1.listing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yuboyang.ilovemovie1.BaseApplication;
import com.example.yuboyang.ilovemovie1.Movie;
import com.example.yuboyang.ilovemovie1.R;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MoviesListingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    private void onTest(Integer i) {
        System.out.println("yby" + i);
    }

    private void onMovieFetchFailed(Throwable throwable) {
        System.out.println("error");
    }

    private void onMovieFetchSuccess(List<Movie> movies) {
        System.out.println(movies);
    }

}
