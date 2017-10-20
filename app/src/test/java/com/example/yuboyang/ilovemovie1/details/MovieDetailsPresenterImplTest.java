package com.example.yuboyang.ilovemovie1.details;

import com.example.yuboyang.ilovemovie1.Movie;
import com.example.yuboyang.ilovemovie1.ReviewWrapper;
import com.example.yuboyang.ilovemovie1.VideoWrapper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import java.net.SocketTimeoutException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * @author BoyangYu
 */
@RunWith(RobolectricTestRunner.class)
public class MovieDetailsPresenterImplTest {
    @Mock
    private MovieDetailView view;
    @Mock
    private MovieDetailInteractor movieDetailsInteractor;
//    @Mock
//    private FavoritesInteractor favoritesInteractor;
    @Mock
    Movie movie;
    @Mock
    VideoWrapper videoWrapper;
    //List<Video> videos;
    @Mock
    ReviewWrapper reviewWrapper;
//    List<Review> reviews;

    private MovieDetailPresenterImpl movieDetailsPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        movieDetailsPresenter = new MovieDetailPresenterImpl(movieDetailsInteractor);
        movieDetailsPresenter.setView(view);
    }

    @After
    public void teardown() {
        movieDetailsPresenter.destroy();
    }
//
//    @Test
//    public void shouldUnfavoriteIfFavoriteTapped() {
//        when(movie.getId()).thenReturn("12345");
//        when(favoritesInteractor.isFavorite(movie.getId())).thenReturn(true);
//        movieDetailsPresenter.onFavoriteClick(movie);
//        verify(view).showUnFavorited();
//    }
//
//    @Test
//    public void shouldFavoriteIfUnfavoriteTapped() {
//        when(movie.getId()).thenReturn("12345");
//        when(favoritesInteractor.isFavorite(movie.getId())).thenReturn(false);
//
//        movieDetailsPresenter.onFavoriteClick(movie);
//
//        verify(view).showFavorited();
//    }

    @Test
    public void shouldBeAbleToShowTrailers() {
        //
        TestScheduler testScheduler1 = new TestScheduler();
        Observable<VideoWrapper> response1 = Observable.just(videoWrapper)
                .subscribeOn(testScheduler1)
                .observeOn(AndroidSchedulers.mainThread());
        TestObserver<VideoWrapper> observer = new TestObserver<>();
        response1.subscribe(observer);

        when(movieDetailsInteractor.fetchVideos(anyString())).thenReturn(response1);
        movieDetailsPresenter.displayTrails(movie.getId());
        testScheduler1.triggerActions(); // 为什么在前面
        observer.assertNoErrors();
        observer.assertComplete();

        verify(view).showTrailers(videoWrapper.getVideos());



//        TestScheduler testScheduler = new TestScheduler();
//        TestObserver<List<Video>> testObserver = new TestObserver<>();
//        Observable<VideoWrapper> response = Observable.just(videoWrapper)
//                .subscribeOn(testScheduler)
//                .observeOn(AndroidSchedulers.mainThread());
//        when(movieDetailsInteractor.fetchVideos(anyString())).thenReturn(response);
//
//        movieDetailsPresenter.displayTrails(movie.getId());
//        testScheduler.triggerActions();
//        testObserver.assertNoErrors();
//        testObserver.assertComplete();
//        verify(view).showTrailers(videoWrapper.getVideos());


//        TestScheduler testScheduler = new TestScheduler();
//        TestObserver<List<Video>> testObserver = new TestObserver<>();
//        Observable<List<Video>> responseObservable = Observable.just(videos)
//                .subscribeOn(testScheduler)
//                .observeOn(AndroidSchedulers.mainThread());
//
//        responseObservable.subscribe(testObserver);
//        when(movieDetailsInteractor.getTrailers(anyString())).thenReturn(responseObservable);
//
//        movieDetailsPresenter.showTrailers(movie);
//        testScheduler.triggerActions();
//
//        testObserver.assertNoErrors();
//        testObserver.assertComplete();
//        verify(view).showTrailers(videos);
    }

    @Test
    public void shouldFailSilentlyWhenNoTrailers() throws Exception {
        when(movieDetailsInteractor.fetchVideos(anyString())).thenReturn(Observable.error(new SocketTimeoutException()));

        movieDetailsPresenter.displayTrails(movie.getId());

        verifyZeroInteractions(view);
    }

    @Test
    public void shouldBeAbleToShowReviews() {
        TestScheduler testScheduler = new TestScheduler();
        TestObserver<ReviewWrapper> testObserver = new TestObserver<>();
        Observable<ReviewWrapper> responseObservable = Observable.just(reviewWrapper)
                .subscribeOn(testScheduler)
                .observeOn(AndroidSchedulers.mainThread());

        responseObservable.subscribe(testObserver);

        when(movieDetailsInteractor.fetchReviews(anyString())).thenReturn(responseObservable);

        movieDetailsPresenter.displayReviews(movie.getId());
        testScheduler.triggerActions();

        testObserver.assertNoErrors();
        testObserver.assertComplete();
        verify(view).showReviews(reviewWrapper.getReviews());
    }


    @Test
    public void shouldFailSilentlyWhenNoReviews() throws Exception {
        when(movieDetailsInteractor.fetchReviews(anyString())).thenReturn(Observable.error(new SocketTimeoutException()));

        movieDetailsPresenter.displayReviews(movie.getId());

        verifyZeroInteractions(view);
    }

}