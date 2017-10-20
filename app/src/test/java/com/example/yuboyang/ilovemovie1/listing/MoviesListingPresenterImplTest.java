package com.example.yuboyang.ilovemovie1.listing;

import com.example.yuboyang.ilovemovie1.Movie;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author BoyangYu
 */
@RunWith(RobolectricTestRunner.class)
public class MoviesListingPresenterImplTest {
    @Mock
    private MoviesListingInteractor interactor;
    @Mock
    private MoviesListingView view;
    @Mock
    Throwable throwable;
    @Mock
    private List<Movie> movies;

    private MoviesListingPresenterImpl presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new MoviesListingPresenterImpl(interactor);
    }

    @After
    public void teardown() {
        presenter.onDestroy();
    }

    @Test
    public void shouldBeAbleToDisplayMovies() {
        TestScheduler testScheduler = new TestScheduler();
        TestObserver<List<Movie>> testObserver = new TestObserver<>();
        Observable<List<Movie>> responseObservable = Observable.just(movies)
                .subscribeOn(testScheduler)
                .observeOn(testScheduler);

        responseObservable.subscribe(testObserver);
        when(interactor.fetchMovies()).thenReturn(responseObservable);

        presenter.setView(view);
        testScheduler.triggerActions();

        testObserver.assertNoErrors();
        testObserver.onComplete();
        verify(view).showMovies(movies);
    }


}