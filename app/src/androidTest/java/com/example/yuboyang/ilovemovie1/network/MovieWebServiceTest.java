package com.example.yuboyang.ilovemovie1.network;


import com.example.yuboyang.ilovemovie1.Movie;

import org.junit.Test;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.TestScheduler;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

/**
 * Created by yuboyang on 10/8/17.
 */

@RunWith(RobolectricTestRunner.class)
public class MovieWebServiceTest {

    @Mock
    private List<Movie> movies;

    @Test
    public void shouldGetPopularMovies() {

    }
}
